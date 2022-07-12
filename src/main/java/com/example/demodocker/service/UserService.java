package com.example.demodocker.service;

import com.example.demodocker.converter.UserConverter;
import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.UserProfile;
import com.example.demodocker.exceptions.ServiceException;
import com.example.demodocker.repository.UserRepository;
import com.example.demodocker.request.UserRequest;
import com.example.demodocker.response.UserResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class UserService {

    private UserRepository userRepository;
    final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // Get All User
    public Iterable<UserProfile> getAllUser() {
        return userRepository.findAll();
    }

    // Get User ByID
    public UserProfile getUserById(Integer id) throws ServiceException {
        Optional<UserProfile> user =  userRepository.findById(id);
        if(user.isEmpty()) {
            throw new ServiceException("User with id "+id+" not found");
        }
        return user.get();
    }

    // Creates User
    public UserResponse addUser(UserRequest userRequest) {
        System.out.println("service");
        UserProfile user = UserConverter.toEntity(userRequest);
        System.out.println(user);
        UserProfile result = userRepository.save(user);
        return UserConverter.toResponse(result);
    }

    //Update user details
    public UserProfile updateUser(UserProfile updatedUser, Integer id) throws ServiceException {
        UserProfile user =  userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id "+id+" not found"));

        user.setFirstName(updatedUser.getFirstName());
        user.setAboutYourself(updatedUser.getAboutYourself());
        user.setDisplayName(updatedUser.getDisplayName());
        user.setLastName(updatedUser.getLastName());
        user.setAreasOfInterest(updatedUser.getAreasOfInterest());
        user.setUserType(updatedUser.getUserType());
        user.setExpertise(updatedUser.getExpertise());
        user.setExperience(updatedUser.getExperience());
        user.setRole(updatedUser.getRole());
        user.setPicture(updatedUser.getPicture());
        return userRepository.save(user);
    }

    //Allocate Course to User
    public UserProfile assignCourseToUser(List<Courses> courseList, Integer id)throws ServiceException {
        UserProfile user =  userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id "+id+" not found"));
        System.out.println(user);
        System.out.println(courseList);
        user.getAllocatedCourse().addAll(courseList);
        return userRepository.save(user);
    }

    // Deallocate course to user
    public UserProfile deallocateCourse(List<Courses> courseList, Integer id)throws ServiceException {
        UserProfile user =  userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id "+id+" not found"));
        List<Courses> userCourseList = user.getAllocatedCourse();
        userCourseList.removeAll(courseList);
        user.setAllocatedCourse(userCourseList);
        System.out.println( user.getAllocatedCourse());
        return userRepository.save(user);
    }

    //Add Course to User Cart
    public UserProfile addToCart(Courses course, Integer id)throws ServiceException {
        UserProfile user =  userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id "+id+" not found"));

        user.getCart().add(course);
        return userRepository.save(user);
    }

    //Delete a course from user Cart
    public UserProfile deleteFromCart(Courses course,Integer uId)throws ServiceException {
        UserProfile user =  userRepository.findById(uId)
                .orElseThrow(() -> new ServiceException("User with id "+ uId +" not found"));

        user.getCart().remove(course);
//        List<Courses> cart = user.getCart();
//        cart.remove(course);
//        System.out.println(cart);
        return userRepository.save(user);
//        return null;
    }

    //Get Cart details of user
    public List<Courses> getCartDetails(Integer id) throws ServiceException {
        UserProfile user =  userRepository.findById(id)
                .orElseThrow(() -> new ServiceException("User with id "+id+" not found"));
        return user.getCart();
    }
}
