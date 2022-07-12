package com.example.demodocker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table
public class Reviews {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String review;
    private Integer ratings;

    @ManyToOne
    @JsonIgnore
    private Courses courseReviewed;

    public Reviews(){

    }
    public Reviews(String review, Integer ratings) {
        this.review = review;
        this.ratings = ratings;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer rid) {
        this.id = rid;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public Integer getRatings() {
        return ratings;
    }

    public void setRatings(Integer ratings) {
        this.ratings = ratings;
    }

    public Courses getCourseReviewed() {
        return courseReviewed;
    }

    public void setCourseReviewed(Courses courseReviewed) {
        this.courseReviewed = courseReviewed;
    }
}
