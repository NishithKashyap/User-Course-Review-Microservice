package com.example.demodocker.service;

import com.example.demodocker.converter.CourseConverter;
import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.Reviews;
import com.example.demodocker.exceptions.ServiceException;
import com.example.demodocker.repository.CourseRepository;
import com.example.demodocker.request.CourseRequest;
import com.example.demodocker.response.CourseResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CourseService {

    private CourseRepository courseRepository;
    private static Logger logger = LoggerFactory.getLogger(CourseService.class);

    @Autowired
    public CourseService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Courses> getAllCourse() {
        logger.debug("retrived all courses");
        return (List<Courses>) this.courseRepository.findAll();
    }

    public Iterable<Courses> sortCourseInAsc() {
        logger.debug("sort in ascending order of courses");
        return courseRepository.findAll(Sort.by("price").ascending());
    }

    // Get All Courses sorted desc
    public Iterable<Courses> sortCourseInDesc() {
        logger.debug("sort in descending order of courses");
        return courseRepository.findAll(Sort.by("price").descending());
    }

    // Get User ByID
    public Courses getCourseById(Integer courseId) throws ServiceException {
        Optional<Courses> course = courseRepository.findById(courseId);
        if (course.isEmpty()) {
            throw new ServiceException("Course with id " + courseId + " does not exist");
        }
        return course.get();
    }

    public CourseResponse addCourse(CourseRequest courseRequest) {
        Courses course = CourseConverter.toEntity(courseRequest);
        Courses result = courseRepository.save(course);
        return CourseConverter.toResponse(result);
    }

    public String deleteCourseById(Integer courseId) throws ServiceException {
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ServiceException("Course with id " + courseId + " does not exist"));
        courseRepository.delete(course);
        return "Course " + courseId + " deleted";
    }

    public Courses addReview(Reviews reviews, Integer courseId)throws ServiceException {
        Courses course = courseRepository.findById(courseId)
                .orElseThrow(() -> new ServiceException("Course with id " + courseId + " does not exist"));
        Reviews addfeedBack = new Reviews(reviews.getReview(),reviews.getRatings());
        course.getCourseFeedback().add(addfeedBack);
        courseRepository.save(course);
        course.getCourseFeedback().add(reviews);
        return  course;
    }

}
