package com.project.library_managment.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.library_managment.models.User;


public interface UserRepository extends JpaRepository<User, Long> {}