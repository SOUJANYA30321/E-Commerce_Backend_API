package com.dcl.shop.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.dcl.shop.model.User;

public interface UserService {

	User addUser(User user);

	List<User> displayAllUsers();

	User findUserById(int userId);

	User updateUserById(int userId, User updatedUser);

	User deleteUserById(int userId);

	User findUserByEmail(String email);

	User findUserByName(String name);

	List<User> searchUser(String keyword);

	Boolean checkUserExistsByEmail(String email);

	List<User> sortUsersByName();

	Page<User> pageUsers(int page, int size);

	Page<User> pageSortUsers(int page, int size, String sortBy, String direction);

}
