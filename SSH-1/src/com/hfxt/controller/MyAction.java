package com.hfxt.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.hfxt.bean.JsonModle;
import com.hfxt.bean.Role;
import com.hfxt.bean.User;
import com.hfxt.service.UserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class MyAction extends ActionSupport implements ModelDriven<User> {

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

	// �䵱����ջroot��: action������.
	private List data;

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	// ģ��������װ����:
	private User user = new User();

	// ������ʽʵ��service���ע��:
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User getModel() {
		return user;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String add() {

		try {
			List<Role> role = this.userService.queryAllRole();
			ActionContext.getContext().put("role", role);
		} catch (Exception e) {

		}
		return "add";
	}

	// ����
	public String addUser() {
		String roleid = ServletActionContext.getRequest().getParameter("user.role.id");

		Integer roleId = 0;
		try {
			roleId = Integer.valueOf(roleid);
		} catch (Exception e) {
			// TODO: handle exception
		}

		Role role = this.userService.queryRole(roleid);
		
		user.setRole(role);

		this.userService.addUser(user);

		return "addUser";
	}

	// ɾ��
	public String delUser() {
		String id = ServletActionContext.getRequest().getParameter("id");
		Integer userid = null;
		try {
			userid = Integer.valueOf(id);
		} catch (Exception e) {

		}
		this.userService.delUser(userid);

		return "delUser";
	}

	public String update() {
		String id = ServletActionContext.getRequest().getParameter("id");
		Integer userid = 0;
		try {
			userid = Integer.valueOf(id);
			user = this.userService.queryUserById(userid);
			List<Role> role = this.userService.queryAllRole();
			ActionContext.getContext().put("role", role);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "update";
	}

	public String updateUser() {
		String roleid = ServletActionContext.getRequest().getParameter("user.role.id");

		Integer roleId = 0;
		Role role = null;
		try {
			roleId = Integer.valueOf(roleid);
			role = this.userService.queryRole(roleid);
		} catch (Exception e) {
			System.out.println(e);
		}

		user.setRole(role);

		this.userService.updateUser(user);

		return "updateUser";
	}
	
	public String updatePassword() {
		User u1 = (User) ServletActionContext.getRequest().getSession().getAttribute("user");
		String password = ServletActionContext.getRequest().getParameter("password");
		
		try {
			u1.setPassword(password);
			this.userService.updateUser(u1);
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return "updatePassword";
	}

	// ��ʼ���˵�:
	public String initMenu() {
		// ��ѯ���ݿ⵱ǰ�û�������Ȩ��.
		data = new ArrayList();

		User u1 = (User) ServletActionContext.getRequest().getSession()
				.getAttribute("user");

		List list = new ArrayList(u1.getRole().getSet());

		data.addAll(list);

		return "responseJson";
	}

	public String userInfo() {
		String username = ServletActionContext.getRequest().getParameter(
				"username");

		user = this.userService.queryUserByName(username);

		return "userInfo";
	}

	// ��ѯ�û���
	public String queryUser() {
		// ��ȡ���ݵĲ�ѯ����
		String username = ServletActionContext.getRequest().getParameter("username");
		String pages = ServletActionContext.getRequest().getParameter("page");
		String limits = ServletActionContext.getRequest().getParameter("limit");
		Integer page = 0, limit = 0;
		try {
			page = Integer.valueOf(pages);
			limit = Integer.valueOf(limits);
		} catch (Exception e) {
			// TODO: handle exception
		}

		data = new ArrayList();

		Integer total = userService.getCount(username);

		data = userService.userList(username, total, limit, page);

		// ��Ӧ����װjson��ʽ. ע��: ����jsonModel���з�װ����.
		m1 = new JsonModle();
		m1.setTotal(total);
		m1.setData(data);

		return "responseJson2";
	}
	
	public String updatePwd(){
		
		return "updatePwd";
	}

	// ͨ��shiro���ʵ�ֵ�¼��֤
	public String login() {
		// ��¼��֤ϸ��: 1.��ȡ�˺ź�����. 2.����service��ѯ�˺ź����� ���ȶ�. 3.���ݽ�������ɹ�(��ݵĻ���)��ʧ�ܵ���Ӧ.
		// shiro��ܵ���֤����: 1.�˺ź�����Ļ�ȡ. 2.shiro�ڲ�ʵ����֤.�ͳɹ������ݵĻ���.
		// �����Ϣ: user���� ע��: ����tokenʵ�ֵ�¼��ݵķ�װ.
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(), user.getPassword());

		// ��ȡshiro�����Ķ���:
		Subject sb = SecurityUtils.getSubject();

		// �������ƣ�ʵ����֤
		try {
			sb.login(token);
		} catch (UnknownAccountException e1) {
			System.out.println("�˺Ų�����!!!");
			return LOGIN;
		} catch (AuthenticationException e) {
			System.out.println(e);
			System.out.println("�������!!!");
			return LOGIN;
		} catch (Exception e1) {
			System.out.println("������æ�����Ժ�����!!!");
			return LOGIN;
		}
		return SUCCESS;
	}

	// �˳�
	public String logout() {
		Subject sb = SecurityUtils.getSubject();
		sb.logout();
		return LOGIN;
	}

}
