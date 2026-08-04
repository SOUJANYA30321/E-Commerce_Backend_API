package com.dcl.shop.controller;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dcl.shop.model.User;
import com.dcl.shop.service.UserService;
import com.dcl.shop.util.ResponseStructure;

import lombok.AllArgsConstructor;

@RestController
@AllArgsConstructor
public class UserController {
	private final UserService service;
	
	@PostMapping("/users")
	public ResponseEntity<ResponseStructure<User>> addUser(@RequestBody User user){
		User addedUser = service.addUser(user);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.CREATED.value());
		rs.setMessage("User object created successfully.");
		rs.setData(addedUser);
		
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.CREATED);
	}
	
	@GetMapping("/users")
	public ResponseEntity<ResponseStructure<List<User>>> displayAllUsers() {
		List<User> usersList = service.displayAllUsers();
		
		ResponseStructure<List<User>> rs = new ResponseStructure<List<User>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Users displayed successfully.");
		rs.setData(usersList);
		
		return new ResponseEntity<ResponseStructure<List<User>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> findUserById(@PathVariable("id") int userId) {
		User userFound = service.findUserById(userId);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("User found for the requested id: " + userId);
		rs.setData(userFound);
		
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.OK);
	}
	
	@PutMapping("/users/{id}") 
	public ResponseEntity<ResponseStructure<User>> updateUserById(@PathVariable("id") int userId, @RequestBody User updatedUser) {
		User userFound = service.updateUserById(userId, updatedUser);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("User modified successfully for the requested id: " + userId);
		rs.setData(userFound);
		
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.OK);
	}
	
	@DeleteMapping("/users/{id}")
	public ResponseEntity<ResponseStructure<User>> deleteUserById(@PathVariable("id") int userId) {
		User userFound = service.deleteUserById(userId);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("User deleted successfully for the requested id: " + userId);
		rs.setData(userFound);
		
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/email/{email}")
	public ResponseEntity<ResponseStructure<User>> findUserByEmail(@PathVariable("email") String email) {
		User userFound = service.findUserByEmail(email);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("User found for the requested email: " + email);
		rs.setData(userFound);
		
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/name/{name}")
	public ResponseEntity<ResponseStructure<User>> findUserByName(@PathVariable("name") String name) {
		User userFound = service.findUserByName(name);
		
		ResponseStructure<User> rs = new ResponseStructure<User>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("User found for the requested name: " + name);
		rs.setData(userFound);
		
		return new ResponseEntity<ResponseStructure<User>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/search")
	public ResponseEntity<ResponseStructure<List<User>>> searchUsers(@RequestParam String keyword) {
		List<User> usersFound = service.searchUser(keyword);
		
		ResponseStructure<List<User>> rs = new ResponseStructure<List<User>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Users displayed successfully.");
		rs.setData(usersFound);
		
		return new ResponseEntity<ResponseStructure<List<User>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/exists/{email}")
	public ResponseEntity<ResponseStructure<Boolean>> checkUserExistsByEmail(@PathVariable("email") String email) {
		Boolean userExists = service.checkUserExistsByEmail(email);
		
		ResponseStructure<Boolean> rs = new ResponseStructure<Boolean>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage(userExists? "User Exists" : "User does not exist");
		rs.setData(userExists);
		
		return new ResponseEntity<ResponseStructure<Boolean>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/sort/name")
	public ResponseEntity<ResponseStructure<List<User>>> sortUsersByName(){
		List<User> usersList = service.sortUsersByName();
		
		ResponseStructure<List<User>> rs = new ResponseStructure<List<User>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Users sorted by name in alphabetical order");
		rs.setData(usersList);
		
		return new ResponseEntity<ResponseStructure<List<User>>>(rs, HttpStatus.OK);
	}

	@GetMapping("/users/page")
	public ResponseEntity<ResponseStructure<Page<User>>> pageUsers(@RequestParam int page, @RequestParam int size) {
		Page<User> user = service.pageUsers(page, size);
		
		ResponseStructure<Page<User>> rs = new ResponseStructure<Page<User>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Users paged with page = " +page+ " and size = "+size);
		rs.setData(user);
		
		return new ResponseEntity<ResponseStructure<Page<User>>>(rs, HttpStatus.OK);
	}
	
	@GetMapping("/users/page-sort")
	public ResponseEntity<ResponseStructure<Page<User>>> pageSortUsers(@RequestParam int page, @RequestParam int size, @RequestParam String sortBy, @RequestParam String direction) {
		Page<User> usersPageSort = service.pageSortUsers(page, size, sortBy, direction);
		
		ResponseStructure<Page<User>> rs = new ResponseStructure<Page<User>>();
		rs.setStatusCode(HttpStatus.OK.value());
		rs.setMessage("Users paged with page = " +page+ " and size = "+size+ " and sorted users by = " +sortBy);
		rs.setData(usersPageSort);
		
		return new ResponseEntity<ResponseStructure<Page<User>>>(rs, HttpStatus.OK);
	}
}
