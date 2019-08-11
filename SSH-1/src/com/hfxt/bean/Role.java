package com.hfxt.bean;

import java.util.Set;

public class Role {

	private Integer id;
	private String rolename;
	private Set<Permission> set;

	public Set<Permission> getSet() {
		return set;
	}

	public void setSet(Set<Permission> set) {
		this.set = set;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

}
