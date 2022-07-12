package com.example.demodocker.request;
import lombok.Data;

@Data
public class UserRequest {

    private String displayName;
    private String firstName;
    private String lastName;
    private String aboutYourself;
    private String userType;
    private String areaOfInterest;
    private String experience;
    private String expertise;
    private String role;
    private String picture;

    public UserRequest(String name, String firstName, String lastName, String bio, String userType,
                       String areasOfInterest, String experience, String domainExpertise, String role, String picture)
    {
        this.displayName = name;
        this.firstName = firstName;
        this.lastName = lastName;
        this.aboutYourself = bio;
        this.userType = userType;
        this.areaOfInterest = areasOfInterest;
        this.experience = experience;
        this.expertise = domainExpertise;
        this.role = role;
        this.picture = picture;
    }
}
