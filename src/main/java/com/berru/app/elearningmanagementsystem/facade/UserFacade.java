package com.berru.app.elearningmanagementsystem.facade;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.berru.app.elearningmanagementsystem.dto.*;
import com.berru.app.elearningmanagementsystem.entity.Address;
import com.berru.app.elearningmanagementsystem.entity.Course;
import com.berru.app.elearningmanagementsystem.entity.MentorDetail;
import com.berru.app.elearningmanagementsystem.entity.User;
import com.berru.app.elearningmanagementsystem.enums.status.ActiveStatus;
import com.berru.app.elearningmanagementsystem.enums.role.UserRole;
import com.berru.app.elearningmanagementsystem.exception.UserSaveFailedException;
import com.berru.app.elearningmanagementsystem.mapper.MentorDetailMapper;
import com.berru.app.elearningmanagementsystem.mapper.UserMapper;
import com.berru.app.elearningmanagementsystem.service.*;
import com.berru.app.elearningmanagementsystem.utils.JwtTokenProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;


import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

@Component
@Transactional
public class UserFacade {private final Logger LOG = LoggerFactory.getLogger(UserFacade.class);

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private StorageService storageService;

    @Autowired
    private MentorDetailService mentorDetailService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private MentorDetailMapper mentorDetailMapper;

    @Autowired
    private UserMapper userMapper;

    public ResponseEntity<CommonApiResponse> registerAdmin(RegisterUserRequestDto registerRequest) {

        LOG.info("Request received to register a new admin");

        CommonApiResponse response = new CommonApiResponse();

        if (registerRequest == null) {
            response.setResponseMessage("Register request cannot be null");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        if (registerRequest.getEmailId() == null || registerRequest.getPassword() == null) {
            response.setResponseMessage("Email and password are required");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        User existingUser = this.userService.getUserByEmailAndStatus(registerRequest.getEmailId(),
                ActiveStatus.ACTIVE.value());

        if (existingUser != null) {
            response.setResponseMessage("A user is already registered with this email");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toEntity(registerRequest);

        user.setRole(UserRole.ROLE_ADMIN.value());
        user.setPassword(passwordEncoder.encode(registerRequest.getPassword()));
        user.setStatus(ActiveStatus.ACTIVE.value());

        existingUser = this.userService.addUser(user);

        if (existingUser == null) {
            response.setResponseMessage("Admin registration failed");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        response.setResponseMessage("Admin registered successfully");
        response.setSuccess(true);

        LOG.info("Admin registration successful, response sent");

        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> registerUser(RegisterUserRequestDto request) {

        LOG.info("Received request to register a new user");

        CommonApiResponse response = new CommonApiResponse();

        if (request == null) {
            response.setResponseMessage("Register request cannot be null");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        User existingUser = this.userService.getUserByEmailAndStatus(request.getEmailId(), ActiveStatus.ACTIVE.value());

        if (existingUser != null) {
            response.setResponseMessage("A user with this email is already registered.");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        if (request.getRole() == null) {
            response.setResponseMessage("User role must be provided.");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        User user = userMapper.toEntity(request);

        String encodedPassword = passwordEncoder.encode(user.getPassword());

        user.setAmount(BigDecimal.ZERO);
        user.setStatus(ActiveStatus.ACTIVE.value());
        user.setPassword(encodedPassword);

        Address address = new Address();
        address.setCity(request.getCity());
        address.setPostalcode(request.getPostalcode());
        address.setStreet(request.getStreet());

        Address savedAddress = this.addressService.addAddress(address);

        if (savedAddress == null) {
            throw new UserSaveFailedException("Registration failed due to a system error while saving the address.");
        }

        user.setAddress(savedAddress);
        existingUser = this.userService.addUser(user);

        if (existingUser == null) {
            throw new UserSaveFailedException("Registration failed due to a system error while saving the user.");
        }

        response.setResponseMessage("User registered successfully.");
        response.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<CommonApiResponse> addMentorDetail(NewMentorDetailRequestDto request) {

        LOG.info("Received request for adding the mentor detail");

        CommonApiResponse response = new CommonApiResponse();

        if (request == null) {
            response.setResponseMessage("Request body is missing.");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        if (request.getAge() == 0 || request.getBio() == null || request.getHighestQualification() == null
                || request.getMentorId() == 0 || request.getProfession() == null || request.getProfilePic() == null) {
            response.setResponseMessage("Missing input");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        User mentor = this.userService.getUserById(request.getMentorId());

        if (mentor == null || !mentor.getRole().equals(UserRole.ROLE_MENTOR.value())) {
            response.setResponseMessage("Mentor not found!!!");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        MentorDetail mentorDetail = mentorDetailMapper.toEntity(request); // MapStruct kullanıyorsan


        String profilePicName = this.storageService.store(request.getProfilePic());

        mentorDetail.setProfilePic(profilePicName);

        MentorDetail detail = mentorDetailService.addMentorDetail(mentorDetail);

        mentor.setMentorDetail(detail);

        this.userService.updateUser(mentor);

        response.setResponseMessage("Mentor profile updated successfully.");
        response.setSuccess(true);

        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }

    public ResponseEntity<UserLoginResponse> login(UserLoginRequest loginRequest) {

        LOG.info("Received request for User Login");

        UserLoginResponse response = new UserLoginResponse();

        if (loginRequest == null) {
            response.setResponseMessage("Missing input");
            response.setSuccess(false);

            return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
        }

        String jwtToken = null;
        User user = null;

        user = this.userService.getUserByEmailid(loginRequest.getEmailId());

        if (user == null) {
            response.setResponseMessage("No user found with the provided email address.");
            response.setSuccess(false);

            return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
        }

        List<GrantedAuthority> authorities = Arrays.asList(new SimpleGrantedAuthority(user.getRole()));

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmailId(),
                    loginRequest.getPassword(), authorities));
        } catch (Exception ex) {
            response.setResponseMessage("Invalid email or password.");
            response.setSuccess(false);
            return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
        }

        jwtToken = jwtTokenProvider.generateToken(loginRequest.getEmailId());

        if (!user.getStatus().equals(ActiveStatus.ACTIVE.value())) {
            response.setResponseMessage("User account is inactive.");
            response.setSuccess(false);

            return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
        }

        UserDto userDto = userMapper.toDto(user); ;

        // user is authenticated
        if (jwtToken != null) {
            response.setUser(userDto);
            response.setResponseMessage("Login successful.");
            response.setSuccess(true);
            response.setJwtToken(jwtToken);
            return new ResponseEntity<UserLoginResponse>(response, HttpStatus.OK);
        }

        else {
            response.setResponseMessage("Failed to login");
            response.setSuccess(false);
            return new ResponseEntity<UserLoginResponse>(response, HttpStatus.BAD_REQUEST);
        }

    }

    public ResponseEntity<UserResponseDto> getUsersByRole(String role) {

        UserResponseDto response = new UserResponseDto();

        if (role == null) {
            response.setResponseMessage("Missing role");
            response.setSuccess(false);
            return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<User> users = new ArrayList<>();

        users = this.userService.getUserByRoleAndStatus(role, ActiveStatus.ACTIVE.value());

        if (users.isEmpty()) {
            response.setResponseMessage("No Users Found");
            response.setSuccess(false);
        }

        List<UserDto> userDtos = new ArrayList<>();

        for (User user : users) {

            UserDto dto = userMapper.toDto(user);
            userDtos.add(dto);

        }

        response.setUsers(userDtos);
        response.setResponseMessage("User successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
    }

    public ResponseEntity<UserResponseDto> getUserById(int userId) {

        UserResponseDto response = new UserResponseDto();

        if (userId == 0) {
            response.setResponseMessage("Invalid Input");
            response.setSuccess(false);
            return new ResponseEntity<UserResponseDto>(response, HttpStatus.BAD_REQUEST);
        }

        List<User> users = new ArrayList<>();

        User user = this.userService.getUserById(userId);
        users.add(user);

        if (users.isEmpty()) {
            response.setResponseMessage("No Users Found");
            response.setSuccess(false);
            return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
        }

        List<UserDto> userDtos = new ArrayList<>();

        for (User u : users) {

            UserDto dto = userMapper.toDto(u);

            userDtos.add(dto);

        }

        response.setUsers(userDtos);
        response.setResponseMessage("User successfully fetched");
        response.setSuccess(true);

        return new ResponseEntity<UserResponseDto>(response, HttpStatus.OK);
    }

    public void fetchUserImage(String userImageName, HttpServletResponse resp) {
        Resource resource = storageService.load(userImageName);
        if (resource != null) {
            try (InputStream in = resource.getInputStream()) {
                ServletOutputStream out = resp.getOutputStream();
                FileCopyUtils.copy(in, out);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ResponseEntity<CommonApiResponse> deleteMentor(Integer mentorId) {

        LOG.info("Received request for deleting the Mentor");

        CommonApiResponse response = new CommonApiResponse();

        if (mentorId == null) {
            response.setResponseMessage("Bad request, Missing data");
            response.setSuccess(false);

            return new ResponseEntity<CommonApiResponse>(response, HttpStatus.BAD_REQUEST);
        }

        User user = null;
        user = this.userService.getUserById(mentorId);

        user.setStatus(ActiveStatus.DEACTIVATED.value());

        User updatedUser = this.userService.updateUser(user);

        if (updatedUser == null) {
            throw new UserSaveFailedException("Failed to delete mentor!!!");
        }

        List<Course> courses = this.courseService.getByMentor(updatedUser);

        for (Course course : courses) {
            course.setStatus(ActiveStatus.DEACTIVATED.value());
            Course updatedCourse = this.courseService.update(course);

            if (updatedCourse == null) {
                throw new UserSaveFailedException("Failed to delete mentor!!!");
            }

        }

        response.setResponseMessage("Mentor and all it's Courses deleted successful!!!");
        response.setSuccess(true);
        return new ResponseEntity<CommonApiResponse>(response, HttpStatus.OK);
    }
}