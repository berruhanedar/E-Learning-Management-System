package com.berru.app.elearningmanagementsystem.service;

import java.util.List;

import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {
    List<String> loadAll();

    String store(MultipartFile file);

    Resource load(String fileName);

    void delete(String fileName);

    List<String> loadAllCourseVideo();

    String storeCourseVideo(MultipartFile file);

    Resource loadCourseVideo(String fileName);

    void deleteCourseVideo(String fileName);

    List<String> loadAllCourseNote();

    String storeCourseNote(MultipartFile file);

    Resource loadCourseNote(String fileName);

    void deleteCourseNote(String fileName);
}
