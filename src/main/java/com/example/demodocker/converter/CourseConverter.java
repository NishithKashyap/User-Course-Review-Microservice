package com.example.demodocker.converter;

import com.example.demodocker.entity.Courses;
import com.example.demodocker.request.CourseRequest;
import com.example.demodocker.response.CourseResponse;

public class CourseConverter {
    public static Courses toEntity(CourseRequest courseRequest) {
        return new Courses(
                null,
                courseRequest.getName(),
                courseRequest.getAuthor(),
                courseRequest.getDescription(),
                courseRequest.getDomain(),
                courseRequest.getPrice(),
                courseRequest.getDuration(),
                courseRequest.getContent()
        );
    }

    public static CourseResponse toResponse(Courses course) {
        return new CourseResponse(
                course.getCourseId(),
                course.getName(),
                course.getAuthor(),
                course.getDescription(),
                course.getDomain(),
                course.getPrice(),
                course.getDuration(),
                course.getContent());
    }

}
