package com.example.demodocker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class UserProfile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    private String displayName;
    private String firstName;
    private String lastName;
    private String aboutYourself;
    private String areasOfInterest;
    private String userType;
    private String experience;
    private String expertise;
    private String role;
    private String picture;

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Courses> allocatedCourse = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Courses> cart = new ArrayList<>();

    public List<Courses> getCart() {
        return cart;
    }

    public void setCart(List<Courses> cart) {
        this.cart = cart;
    }

    public UserProfile() {

    }

    public UserProfile(Integer id, String displayName, String firstName, String lastName,String aboutYourself,String areasOfInterest,
                       String userType,String experience, String expertise,String role,String picture) {
        this.id = id;
        this.displayName = displayName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.aboutYourself=aboutYourself;
        this.areasOfInterest=areasOfInterest;
        this.experience=experience;
        this.userType=userType;
        this.expertise=expertise;
        this.role=role;
        this.picture = picture;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getAboutYourself() {
        return aboutYourself;
    }

    public void setAboutYourself(String aboutYourself) {
        this.aboutYourself = aboutYourself;
    }

    public String getAreasOfInterest() {
        return areasOfInterest;
    }

    public void setAreasOfInterest(String areasOfInterest) {
        this.areasOfInterest = areasOfInterest;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getExperience() {
        return experience;
    }

    public void setExperience(String experience) {
        this.experience = experience;
    }

    public String getExpertise() {
        return expertise;
    }

    public void setExpertise(String expertise) {
        this.expertise = expertise;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String img) {
        this.picture = img;
    }

    public List<Courses> getAllocatedCourse() {
        return allocatedCourse;
    }

    public void setAllocatedCourse(List<Courses> allocatedCourse) {
        this.allocatedCourse = allocatedCourse;
    }

    @Override
    public String toString() {
        return "UserProfile{" +
                "id=" + id +
                ", displayName='" + displayName + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", aboutYourself='" + aboutYourself + '\'' +
                ", areasOfInterest='" + areasOfInterest + '\'' +
                ", userType='" + userType + '\'' +
                ", experience='" + experience + '\'' +
                ", expertise='" + expertise + '\'' +
                ", role='" + role + '\'' +
                ", picture='" + picture + '\'' +
                ", allocatedCourse=" + allocatedCourse +
                ", cart=" + cart +
                '}';
    }
}
