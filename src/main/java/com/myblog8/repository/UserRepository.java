package com.myblog8.repository;

import com.myblog8.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

  Optional<User>  findByUsername(String username);     //Search querry
  Optional<User> findByEmail(String email); //Search querry
  Optional<User> findByUsernameOrEmail(String username,String email);

  Boolean existsByUsername(String username); //check wether this name exist or not
  Boolean existsByEmail(String email);  //check wether this email exist or not
}
