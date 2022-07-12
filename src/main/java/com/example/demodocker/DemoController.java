package com.example.demodocker;

import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.UserProfile;
import com.example.demodocker.exceptions.ServiceException;
import com.example.demodocker.request.UserRequest;
import com.example.demodocker.response.UserResponse;
import com.example.demodocker.service.UserService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class DemoController {

	private UserService userService;

	private static Logger log = LoggerFactory.getLogger(DemoController.class);

	@Autowired
	public DemoController(UserService userService) {
		this.userService = userService;
	}

	// get details of all user
	@GetMapping("/getAllUsers")
	public @NonNull Iterable<UserProfile> getAllUser(){
		return userService.getAllUser();
	}

	//get user by id
	@GetMapping("/{id}")
	public UserProfile getUserById(@PathVariable("id") Integer id) throws ServiceException {
		return userService.getUserById(id);
	}

	//create user
	@PostMapping("/addUser")
	public UserResponse addUser(@RequestBody UserRequest user) {
		try {
			System.out.println("user");
			return userService.addUser(user);
		} catch (Exception ex) {
			log.error("Error", ex.getMessage());
			return null;
		}
	}

	// update user profile by id
	@PutMapping("/update/{id}")
	public UserProfile updateUser(@RequestBody UserProfile user,@PathVariable("id") Integer id)throws ServiceException {
		return userService.updateUser(user, id);
	}

	//assign course to user
	@PutMapping("/{id}/assign")
	public UserProfile assignCourseToUser(@RequestBody List<Courses> courseList, @PathVariable("id") Integer id)throws ServiceException {
		return userService.assignCourseToUser(courseList,id);
	}

	//deallocate course from a given user
	@PutMapping("/{id}/deallocate")
	public UserProfile deallocateCourse(@RequestBody List<Courses> courseList, @PathVariable("id") Integer id)throws ServiceException {
		return userService.deallocateCourse(courseList,id);
	}

	//delete course from cart
	@PutMapping("/{userId}/deleteFromCart")
	public UserProfile deleteFromCart(@RequestBody Courses course, @PathVariable("userId") Integer userId) throws ServiceException {
		return userService.deleteFromCart(course,userId);
	}

	//add course to cart
	@PutMapping("/{id}/addToCart")
	public UserProfile addToCart(@RequestBody Courses course, @PathVariable("id") Integer id)throws ServiceException {
		return userService.addToCart(course,id);
	}

	//get the user cart
	@GetMapping("/{id}/cartDetails")
	public List<Courses> getCartDetails(@PathVariable("id") Integer id) throws ServiceException {
		return userService.getCartDetails(id);
	}
}