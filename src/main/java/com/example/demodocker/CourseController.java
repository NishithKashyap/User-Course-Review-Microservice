package com.example.demodocker;

import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.entity.UserProfile;
import com.example.demodocker.exceptions.ServiceException;
import com.example.demodocker.request.CourseRequest;
import com.example.demodocker.response.CourseResponse;
import com.example.demodocker.service.CourseService;
import lombok.NonNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")

public class CourseController {
    private CourseService courseService;

    private static Logger log = LoggerFactory.getLogger(CourseController.class);

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    //get all course details
    @GetMapping("/getAllCourses")
    public @NonNull Iterable<Courses> getAllCourse(){
        return courseService.getAllCourse();
    }

    //sort courses based on price
    @GetMapping("/sort")
    public @NonNull Iterable<Courses> sortCourseInAsc(){
        return courseService.sortCourseInAsc();
    }

    //sort courses based on price descending
    @GetMapping("/sortDesc")
    public @NonNull Iterable<Courses> sortCourseInDesc(){
        return courseService.sortCourseInDesc();
    }

    // get course detail by id
    @GetMapping("/getCourse/{courseId}")
    public Courses getCourseById(@PathVariable("courseId") Integer courseId) throws ServiceException {
        return courseService.getCourseById(courseId);
    }

    // add new course
    @PostMapping("/addCourse")
    public CourseResponse addCourse(@RequestBody CourseRequest course) {
        try {
            return courseService.addCourse(course);
        } catch (Exception ex) {
            log.error("Error", ex.getMessage());
            return null;
        }
    }

    //delete a course by id
    @DeleteMapping("/delete/{courseId}")
    public String deleteCourseById(@PathVariable("courseId") Integer courseId) throws ServiceException {
        return courseService.deleteCourseById(courseId);
    }

    //adding reviews
    @PutMapping("/{courseId}/addReview")
    public Courses addReview(@RequestBody Reviews reviews, @PathVariable("courseId") Integer courseId)throws ServiceException {
        return courseService.addReview(reviews,courseId);
    }

}
