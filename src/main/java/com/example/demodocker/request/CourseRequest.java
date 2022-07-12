package com.example.demodocker.request;
import lombok.Data;

@Data
public class CourseRequest {
    private String name;
    private String author;
    private String description;
    private String domain;
    private Double price;
    private Integer duration;
    private String content;

    public CourseRequest(String courseName, String author, String description, String domain, Double price, Integer duration, String content) {
        this.name = courseName;
        this.author = author;
        this.description = description;
        this.domain = domain;
        this.price = price;
        this.duration = duration;
        this.content = content;
    }
}
