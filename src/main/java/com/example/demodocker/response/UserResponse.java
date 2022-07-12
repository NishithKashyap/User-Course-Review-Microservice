package com.example.demodocker.response;


import lombok.Data;
@Data
public class UserResponse {
    private Integer id;
    private String name;
    private String firstName;
    private String lastName;
    private String bio;
    private String userType;
    private String areasOfInterest;
    private String experience;
    private String expertise;
    private String role;
    private String picture;

    public UserResponse(Integer id, String name, String firstName, String lastName, String bio, String userType,
                       String areasOfInterest, String experience, String domainExpertise, String role, String picture)
    {
        this.id = id;
        this.name = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.bio = bio;
        this.userType = userType;
        this.areasOfInterest = areasOfInterest;
        this.experience = experience;
        this.expertise = domainExpertise;
        this.role = role;
        this.picture = picture;
    }
}


