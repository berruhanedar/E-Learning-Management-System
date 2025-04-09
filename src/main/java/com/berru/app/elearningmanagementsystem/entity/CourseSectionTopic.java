package com.berru.app.elearningmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class CourseSectionTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private String description;

    private String orderNumber;

    private String videoFile;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "section_id")
    private CourseSection courseSection;
}
