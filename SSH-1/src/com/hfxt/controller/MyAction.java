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

	// 充当对象栈root中: action的属性.
	private List data;

	public List getData() {
		return data;
	}

	public void setData(List data) {
		this.data = data;
	}

	// 模型驱动封装属性:
	private User user = new User();

	// 配置形式实现service层的注入:
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

	// 新增
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

	// 删除
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

	// 初始化菜单:
	public String initMenu() {
		// 查询数据库当前用户的所有权限.
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

	// 查询用户名
	public String queryUser() {
		// 获取传递的查询数据
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

		// 响应：封装json格式. 注意: 定义jsonModel进行封装返回.
		m1 = new JsonModle();
		m1.setTotal(total);
		m1.setData(data);

		return "responseJson2";
	}
	
	public String updatePwd(){
		
		return "updatePwd";
	}

	// 通过shiro框架实现登录验证
	public String login() {
		// 登录认证细节: 1.获取账号和密码. 2.调用service查询账号和密码 作比对. 3.根据结果给出成功(身份的缓存)或失败的响应.
		// shiro框架的认证过程: 1.账号和密码的获取. 2.shiro内部实现认证.和成功后的身份的缓存.
		// 身份信息: user对象。 注意: 借助token实现登录身份的封装.
		UsernamePasswordToken token = new UsernamePasswordToken(
				user.getUsername(), user.getPassword());

		// 获取shiro上下文对象:
		Subject sb = SecurityUtils.getSubject();

		// 传递令牌，实现认证
		try {
			sb.login(token);
		} catch (UnknownAccountException e1) {
			System.out.println("账号不存在!!!");
			return LOGIN;
		} catch (AuthenticationException e) {
			System.out.println(e);
			System.out.println("密码错误!!!");
			return LOGIN;
		} catch (Exception e1) {
			System.out.println("服务器忙，请稍后重试!!!");
			return LOGIN;
		}
		return SUCCESS;
	}

	// 退出
	public String logout() {
		Subject sb = SecurityUtils.getSubject();
		sb.logout();
		return LOGIN;
	}

}
