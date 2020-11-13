package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class User implements Serializable {
	
	@Id
	private int id;
	private String name;
	private String phnumber;
	private String emailId;
	
	@Builder
	public User(@JsonProperty("id") Integer id, @JsonProperty("name") String name, @JsonProperty("phnumber") String phnumber, @JsonProperty("emailId") String emailId) {
		this.id = id;
		this.name = name;
		this.phnumber = phnumber;
		this.emailId = emailId;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", phnumber=" + phnumber + ", emailId=" + emailId + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhnumber() {
		return phnumber;
	}
	public void setPhnumber(String phnumber) {
		this.phnumber = phnumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
}
