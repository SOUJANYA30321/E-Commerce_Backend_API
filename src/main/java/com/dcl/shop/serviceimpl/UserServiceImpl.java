package com.dcl.shop.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.dcl.shop.exceptions.UserNotFoundByUserIdException;
import com.dcl.shop.exceptions.UserNotFoundEmailException;
import com.dcl.shop.exceptions.UserNotFoundNameException;
import com.dcl.shop.exceptions.UsersNotFoundException;
import com.dcl.shop.model.User;
import com.dcl.shop.repository.UserRepository;
import com.dcl.shop.service.UserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService{
	private final UserRepository repository;

	
	/*  ADD USER  */
	@Override
	public User addUser(User user) {
		return repository.save(user);
	}

	
	/*  DISPLAY ALL USERS  */
	@Override
	public List<User> displayAllUsers() {
		List<User> existingUsersList = repository.findAll();
		
		if(existingUsersList.isEmpty()) {
			throw new UsersNotFoundException("NO USERS FOUND");
		} else {
			return existingUsersList;
		}
	}

	
	/*  FIND USER BY USER ID  */
	@Override
	public User findUserById(int userId) {
		Optional<User> optional = repository.findByUserId(userId);
		
		if(optional.isEmpty()) {
			throw new UserNotFoundByUserIdException("NO USER FOUND");
		} else {
			return optional.get();
		}
	}

	
	/*  UPDATE USER BY USER ID  */
	@Override
	public User updateUserById(int userId, User updatedUser) {
		Optional<User> optional = repository.findByUserId(userId);
		
		if(optional.isEmpty()) {
			throw new UserNotFoundByUserIdException("NO USER FOUND");
		} else {
			User existingUser = optional.get();

			existingUser.setName(updatedUser.getName());
			existingUser.setEmail(updatedUser.getEmail());
			existingUser.setPassword(updatedUser.getPassword());
			existingUser.setPhone(updatedUser.getPhone());
			existingUser.setAddress(updatedUser.getAddress());
			existingUser.setRole(updatedUser.getRole());

			return repository.save(existingUser);
		}
	}

	
	/*  DELETE USER BY USER ID  */
	@Override
	public User deleteUserById(int userId) {
		Optional<User> optional = repository.findByUserId(userId);
		
		if(optional.isEmpty()) {
			throw new UserNotFoundByUserIdException("NO USER FOUND");
		} else {
			User userFound = optional.get();
			repository.delete(userFound);
			return userFound;
		}
	}

	
	/*  FIND USER BY USER EMAIL  */
	@Override
	public User findUserByEmail(String email) {
		Optional<User> optional = repository.findByEmail(email);
		
		if(optional.isEmpty()) {
			throw new UserNotFoundEmailException("NO USER FOUND");
		} else {
			return optional.get();
		}
	}

	
	/*  FIND USER BY USER NAME  */
	@Override
	public User findUserByName(String name) {
		Optional<User> optional = repository.findByName(name);
		
		if(optional.isEmpty()) {
			throw new UserNotFoundNameException("NO USER FOUND");
		} else {
			return optional.get();
		}
	}

	
	/*  SEARCH USER  */
	@Override
	public List<User> searchUser(String keyword) {
		List<User> usersFound = repository.findByNameContainingIgnoreCase(keyword);
		
		if(usersFound.isEmpty()) {
			throw new UsersNotFoundException("NO USERS FOUND");
		} else {
			return usersFound;
		}
	}

	
	/*  CHECK USER EXISTS BY EMAIL  */
	@Override
	public Boolean checkUserExistsByEmail(String email) {
		return repository.existsByEmailIgnoreCase(email);
	}

	
	/*  SORT USERS BY USER NAME  */
	@Override
	public List<User> sortUsersByName() {
		List<User> usersList = repository.findAllByOrderByNameAsc();
		
		if(usersList.isEmpty()) {
			throw new UsersNotFoundException("NO USERS FOUND");
		} else {
			return usersList;
		}
	}

	
	/*  PAGE USERS  */
	@Override
	public Page<User> pageUsers(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		
		Page<User> userPage = repository.findAll(pageable);
		
		if(userPage.isEmpty()) {
			throw new UsersNotFoundException("NO USERS FOUND");
		} else {
			return userPage;
		}
	}

	
	/*  PAGE-SORT USERS  */
	@Override
	public Page<User> pageSortUsers(int page, int size, String sortBy, String direction) {
		Sort sort = direction.equalsIgnoreCase("asc")? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();
		
		Pageable pageable = PageRequest.of(page, size, sort);
		
		return repository.findAll(pageable);
	}

	
}
