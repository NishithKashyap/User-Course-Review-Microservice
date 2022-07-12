package com.example.demodocker.converter;
import com.example.demodocker.entity.UserProfile;
import com.example.demodocker.request.UserRequest;
import com.example.demodocker.response.UserResponse;

public class UserConverter {
    public static UserProfile toEntity(UserRequest userRequest) {
        return new UserProfile(
                null,
                userRequest.getDisplayName(),
                userRequest.getFirstName(),
                userRequest.getLastName(),
                userRequest.getAboutYourself(),
                userRequest.getAreaOfInterest(),
                userRequest.getUserType(),
                userRequest.getExperience(),
                userRequest.getExpertise(),
                userRequest.getRole(),
                userRequest.getPicture()
        );
    }

    public static UserResponse toResponse(UserProfile user) {
        return new UserResponse(user.getId(), user.getDisplayName(), user.getFirstName(), user.getLastName(),
                user.getAboutYourself(), user.getUserType(), user.getAreasOfInterest(),  user.getExperience(),
                user.getExpertise(), user.getRole(), user.getPicture());
    }

}
