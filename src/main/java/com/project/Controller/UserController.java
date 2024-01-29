package com.project.Controller;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.Repository.UserRepository;
import com.project.exception.UserNotFoundException;
import com.project.model.User;

@RestController
@CrossOrigin("http://localhost:3000")
public class UserController {

	@Autowired
	private UserRepository userRepository;
	
	//To add data
	@PostMapping("/user")
	public User newUser(@RequestBody User newUser) {
		return userRepository.save(newUser);
	}
	
	//To get data
	@GetMapping("/users")
	public List<User>getAllUser(){
		return userRepository.findAll();
	}
	
	 @GetMapping("/user/{id}")
	    User getUserById(@PathVariable int  id) {
	        return userRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
	    }
	
	//To update the data of User
	@PutMapping("/user/{id}")
	public User updateUser(@RequestBody User newUser,@PathVariable int id) {
		return userRepository.findById(id).map(user->{
				user.setUsername(newUser.getUsername());
				 user.setName(newUser.getName());
                 user.setEmail(newUser.getEmail());
				return userRepository.save(user);
		
		}).orElseThrow(()->new UserNotFoundException(id));			
	}
	//Delete the User
	@DeleteMapping("/user/{id}")
	public String deleteUser(@PathVariable int id) {
		if(!userRepository.existsById(id)) {
			throw new UserNotFoundException(id);
		}
		userRepository.deleteById(id);
		return "User with id "+ id + " has been deleted.";
	}
}
