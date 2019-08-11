package com.hfxt.controller;

import com.hfxt.bean.JsonModle;
import com.hfxt.bean.Role;
import com.hfxt.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends ActionSupport implements ModelDriven<Role> {

	// 响应对象传参至ajax
	private String data2;

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	// 创建json的对象封装
	private JsonModle m1;

	public JsonModle getM1() {
		return m1;
	}

	public void setM1(JsonModle m1) {
		this.m1 = m1;
	}

	// 创建Role模型对象
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	// 导入service层
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public Role getModel() {
		return role;
	}

}
