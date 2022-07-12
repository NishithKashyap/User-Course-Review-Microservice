package com.example.demodocker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class Courses {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Integer courseId;
    @Column
    private String name;
    private String author;
    private String description;
    private String domain;
    private double price;
    private String content;
    private Integer duration;

    public Courses() {
    }

    @ManyToMany(fetch= FetchType.LAZY,mappedBy = "allocatedCourse")
    @JsonIgnore
    private List<UserProfile>  usersEnrolled = new ArrayList<>();

    public List<UserProfile> getUserCart() {
        return userCart;
    }

    public void setUserCart(List<UserProfile> userCart) {
        this.userCart = userCart;
    }

    @ManyToMany(fetch= FetchType.LAZY,mappedBy = "cart")
    @JsonIgnore
    private List<UserProfile> userCart = new ArrayList<>();

    @OneToMany(fetch= FetchType.LAZY,mappedBy = "courseReviewed")
    private  List<Reviews> courseFeedback = new ArrayList<>() ;

    public List<Reviews> getCourseFeedback() {
        return courseFeedback;
    }

    public void setCourseFeedback(List<Reviews> courseFeedback) {
        this.courseFeedback = courseFeedback;
    }

    public Courses(Integer courseId, String courseName, String courseCreator, String description, String domain, Double price, Integer duration, String content) {
        this.courseId = courseId;
        this.name = courseName;
        this.author = courseCreator;
        this.description = description;
        this.domain = domain;
        this.price = price;
        this.duration = duration;
        this.content = content;
    }

    public Integer getCourseId() {
        return courseId;
    }

    public void setCourseId(Integer courseId) {
        this.courseId = courseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String courseName) {
        this.name = courseName;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String courseCreator) {
        this.author = courseCreator;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public List<UserProfile> getUsersEnrolled() {
        return usersEnrolled;
    }

    public void setUsersEnrolled(List<UserProfile> usersEnrolled) {
        this.usersEnrolled = usersEnrolled;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
