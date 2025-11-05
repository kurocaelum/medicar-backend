package com.kurocaelum.medicar.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.kurocaelum.medicar.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
	UserDetails findByUsername(String username);
	Optional<User> findUserByUsername(String username);
}
