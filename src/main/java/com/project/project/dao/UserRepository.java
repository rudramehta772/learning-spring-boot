package com.project.project.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.project.project.models.Users;

public interface UserRepository extends CrudRepository<Users, Long> {
    Optional<Users> findByUsername(String username);
}
