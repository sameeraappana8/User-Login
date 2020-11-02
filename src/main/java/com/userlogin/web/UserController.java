package com.userlogin.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.userlogin.entity.User;
import com.userlogin.exception.ResourceNotFoundException;
import com.userlogin.repository.UserRepository;
import com.userlogin.service.UserServiceImpl;

@RestController
public class UserController {
	
	@Autowired
	 private UserServiceImpl userService;
	
	@Autowired
	private UserRepository userRepository;
	
	// get all users API
	@GetMapping("/users")
	public List<User> getAllUser(){
		return userService.getAllUser();
//		return this.userRepository.findAll();
	}
	
	// get user by id
	@GetMapping("/users/{id}")
	public User getUserById(@PathVariable (value ="id") long userId) {
		return userService.getUserById(userId);
//		return this.userRepository.findById(userId)
//				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
	}
	
//	// create user
//	@PostMapping("/users")
//	public User createUser(@RequestBody User user) {
//		return this.userRepository.save(user);
//	}
	
	// update user
	@PutMapping("/users/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
		.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
		existingUser.setFirstName(user.getFirstName());
		existingUser.setLastName(user.getLastName());
		existingUser.setEmail(user.getEmail());
		return this.userRepository.save(existingUser);
	}
	
	// login user
	@PostMapping("/login")
	public User loginUser(@RequestParam String email, @RequestParam String password) {
		User existingUser = this.userRepository.findByEmail(email);
		
		if(existingUser == null) {
			throw new ResourceNotFoundException("Invalid Credentials");
		}
		
		if(existingUser != null) {
			if (existingUser.getPassword().equals(password)) {
				existingUser.setLastLoginDate(LocalDateTime.now());
			}
			else {
				throw new ResourceNotFoundException("Invalid Password");
			}
		}
		
		return this.userRepository.save(existingUser);
	}
	
	// delete user by id
	@DeleteMapping("/users/{id}")
	public ResponseEntity<User> deleteUser(@PathVariable("id") long userId) {
		User existingUser = this.userRepository.findById(userId)
				.orElseThrow(() -> new ResourceNotFoundException("User not found with id: " + userId));
			this.userRepository.delete(existingUser);
			return ResponseEntity.ok().build();
	}
	
}
