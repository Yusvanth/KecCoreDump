package com.keccoredump.demo.entity;

import java.util.List;

import javax.persistence.*;


@Entity
@Table(name = "User")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@Column(unique = true)
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_details_id")
	private UserDetails userDetails;
	
	@ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(
            name = "role_mapper",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private List<Roles> userRoles;
	
	public User() {
		
	}

	public User(String email, String password, UserDetails userDetails, List<Roles> userRoles) {
		this.email = email;
		this.password = password;
		this.userDetails = userDetails;
		this.userRoles = userRoles;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserDetails getUserDetails() {
		return userDetails;
	}

	public void setUserDetails(UserDetails userDetails) {
		this.userDetails = userDetails;
	}

	public List<Roles> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(List<Roles> userRoles) {
		this.userRoles = userRoles;
	}

	
	
	
}