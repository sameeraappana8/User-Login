package com.userlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.userlogin.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
