package com.example.demodocker.repository;

import com.example.demodocker.entity.Courses;
import com.example.demodocker.entity.UserProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourseRepository extends JpaRepository<Courses,Integer> {
}