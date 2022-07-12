package com.example.demodocker.repository;

import com.example.demodocker.entity.Courses;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CartRepository extends JpaRepository<Courses,Integer> {
}
