package com.dcl.shop.model;

import java.util.List;

import com.dcl.shop.enums.UserRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false, unique = true)
	private String email;
	
	@Column(nullable = false)
	private String password;

	@Column(nullable = false)
	private long phone;

	@Column(nullable = false)
	private String address;

	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private UserRole role;

	@OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private Cart cart;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Order> orders;
	
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonIgnore
	private List<Review> reviews;

}
