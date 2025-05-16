package com.berru.app.elearningmanagementsystem.controller;

import com.berru.app.elearningmanagementsystem.dto.*;
import com.berru.app.elearningmanagementsystem.facade.UserFacade;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("api/user")
@CrossOrigin(origins = "http://localhost:8081")
public class UserController {

    @Autowired
    private UserFacade userFacade;

    @PostMapping("/admin/register")
    @Operation(summary = "Api to register Admin")
    private ResponseEntity<CommonApiResponse> registerAdmin(@RequestBody RegisterUserRequestDto request) {
        return userFacade.registerAdmin(request);
    }

    @PostMapping("register")
    @Operation(summary = "Api to register customer or seller user")
    public ResponseEntity<CommonApiResponse> registerUser(@RequestBody RegisterUserRequestDto request) {
        return this.userFacade.registerUser(request);
    }

    @PutMapping("mentor/detail/update")
    @Operation(summary = "Api to update the mentor detail")
    public ResponseEntity<CommonApiResponse> addMentorDetail(NewMentorDetailRequestDto request) {
        return this.userFacade.addMentorDetail(request);
    }

    @PostMapping("login")
    @Operation(summary = "Api to login any User")
    public ResponseEntity<UserLoginResponse> login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        return userFacade.login(userLoginRequest);
    }

    @GetMapping("/fetch/role-wise")
    @Operation(summary = "Api to get Users By Role")
    public ResponseEntity<UserResponseDto> fetchAllUsersByRole(@RequestParam("role") String role)
            throws JsonProcessingException {
        return userFacade.getUsersByRole(role);
    }

    @DeleteMapping("/mentor/delete")
    @Operation(summary = "Api to delete the mentor and all it's course")
    public ResponseEntity<CommonApiResponse> deleteMentor(@RequestParam("mentorId") Integer mentorId) {
        return userFacade.deleteMentor(mentorId);
    }

    @GetMapping("/fetch/user-id")
    @Operation(summary = "Api to get User Detail By User Id")
    public ResponseEntity<UserResponseDto> fetchUserById(@RequestParam("userId") int userId) {
        return userFacade.getUserById(userId);
    }

    @GetMapping(value = "/{userImageName}", produces = "image/*")
    public void fetchTourImage(@PathVariable("userImageName") String userImageName, HttpServletResponse resp) {
        this.userFacade.fetchUserImage(userImageName, resp);
    }
}