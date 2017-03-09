package com.csc.mfs.model;

import java.util.Collection;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.ws.rs.DELETE;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.Transient;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	
	@Column(name = "email")
	@Email(message = "*Please provide a valid Email")
	@NotEmpty(message = "*Please provide an email")
	private String email;
	
	@Column(name = "password")
	@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	@Transient
	private String password;
	
	@Column(name = "name")
	@NotEmpty(message = "*Please provide your name")
	private String name;
	
	@Column(name = "last_name")
	@NotEmpty(message = "*Please provide your last name")
	private String lastName;
	
	@Column(name = "active", nullable=false)
	private int active;
	
	@JoinTable(name = "user_role", joinColumns = {
	        @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)}, inverseJoinColumns = {
	        @JoinColumn(name = "role_id", referencedColumnName = "id", nullable = false)})
	@ManyToMany
	private Collection<Role> roleCollection;
	
	@OneToMany(	cascade = {CascadeType.ALL}, mappedBy = "idUser")
	private Collection<Download> downloadCollection;
	@OneToMany(	cascade = {CascadeType.ALL}, mappedBy = "userId")
	private Collection<Files> filesCollection;
	
	@Column(name = "rank_id")
	private int rank_Id;
	
	
	
	public User() {}
	public User(int id, String email, String password, String name, String lastName, int active, int rank_id){
		this.id = id;
		this.email =email;
		this.password = password;
		this.name = name;
		this.lastName = lastName;
		this.active=active;
		this.rank_Id = rank_id;
	}
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public Collection<Role> getRoleCollection() {
		return roleCollection;
	}
	public void setRoleCollection(Collection<Role> roleCollection) {
		this.roleCollection = roleCollection;
	}
	public Collection<Download> getDownloadCollection() {
		return downloadCollection;
	}
	public void setDownloadCollection(Collection<Download> downloadCollection) {
		this.downloadCollection = downloadCollection;
	}
	public Collection<Files> getFilesCollection() {
		return filesCollection;
	}
	public void setFilesCollection(Collection<Files> filesCollection) {
		this.filesCollection = filesCollection;
	}
	public int getRank_Id() {
		return rank_Id;
	}

	public void setRank_Id(int rank_Id) {
		this.rank_Id = rank_Id;
	}
	

}
