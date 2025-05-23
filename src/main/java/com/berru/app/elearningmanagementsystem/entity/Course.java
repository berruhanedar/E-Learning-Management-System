package com.berru.app.elearningmanagementsystem.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
@Data
@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "mentor_id")
    private User mentor;

    @ManyToOne(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinColumn(name = "category_id")
    private Category category;

    private String name;

    private String description;

    private String type;

    private BigDecimal fee;

    private String addedDateTime;

    private String notesFileName;

    private String thumbnail;

    private String status;

    private int discountInPercent;

    private String authorCourseNote;

    private String specialNote;

    private String prerequisite;

    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CourseSection> sections = new ArrayList<>();

}