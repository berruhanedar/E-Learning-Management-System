package com.berru.app.elearningmanagementsystem.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
public class CourseSectionTopic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String srNo;

    private String name;

    private String description;

    private String videoFileName;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "section_id")
    private CourseSection courseSection;

}