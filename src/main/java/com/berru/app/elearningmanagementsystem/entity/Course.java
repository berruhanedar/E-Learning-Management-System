package com.berru.app.elearningmanagementsystem.entity;

import com.berru.app.elearningmanagementsystem.enums.CourseStatus;
import com.berru.app.elearningmanagementsystem.enums.CourseTypeStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private LocalDateTime createdAt;

    private String name;

    private String description;

    private String authorNote;

    private String prerequisite;

    private String notesFileName;

    private String specialNote;

    private String thumbnail;

    private int discountPercent;

    private BigDecimal fee;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSection> sections = new ArrayList<>();

    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Enumerated(EnumType.STRING)
    private CourseTypeStatus type;
}
