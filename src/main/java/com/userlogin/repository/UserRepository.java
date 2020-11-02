package com.userlogin.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.userlogin.entity.User;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
	
//	@Query("SELECT u from User u WHERE (u.email = email AND u.password = password)")
//	public User findByEmailAndPassword(@Param("email") String email, @Param("password") String password);
	
}
