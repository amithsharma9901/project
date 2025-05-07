package com.community.user.repository;

import com.community.user.entity.User;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

	  Optional<User> findByEmail(String email);
	    boolean existsByEmail(String email);

}