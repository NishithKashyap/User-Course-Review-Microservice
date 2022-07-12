package com.example.demodocker.response;

import lombok.Data;
@Data
public class CourseResponse {
    private Integer id;
    private String name;
    private String author;
    private String description;
    private String domain;
    private Double price;
    private Integer duration;
    private String content;

    public CourseResponse(Integer courseId, String courseName, String courseCreator, String description, String domain, Double price, Integer duration, String content) {
        this.id = courseId;
        this.name = courseName;
        this.author = courseCreator;
        this.description = description;
        this.domain = domain;
        this.price = price;
        this.duration = duration;
        this.content = content;
    }
}
