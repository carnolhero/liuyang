package com.hfxt.controller;

import com.hfxt.bean.JsonModle;
import com.hfxt.bean.Role;
import com.hfxt.service.UserService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class RoleAction extends ActionSupport implements ModelDriven<Role> {

	// ��Ӧ���󴫲���ajax
	private String data2;

	public String getData2() {
		return data2;
	}

	public void setData2(String data2) {
		this.data2 = data2;
	}

	// ����json�Ķ����װ
	private JsonModle m1;

	public JsonModle getM1() {
		return m1;
	}

	public void setM1(JsonModle m1) {
		this.m1 = m1;
	}

	// ����Roleģ�Ͷ���
	private Role role;

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	// ����service��
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
