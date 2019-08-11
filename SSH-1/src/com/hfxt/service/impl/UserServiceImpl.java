package com.hfxt.service.impl;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.shiro.authc.UnknownAccountException;

import com.hfxt.bean.Permission;
import com.hfxt.bean.Role;
import com.hfxt.bean.User;
import com.hfxt.dao.UserDao;
import com.hfxt.service.UserService;

public class UserServiceImpl implements UserService {

	public UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public int addUser(User user) {
		int result = this.userDao.addUser(user);
		return result;
	}

	@Override
	public boolean delUser(int id) {
		return this.userDao.delUser(id);
	}

	@Override
	public boolean updateUser(User user) {
		return this.userDao.updateUser(user);
	}

	// 根据账号查询用户:
	@Override
	public User queryUserByName(String username) {
		User u1 = this.userDao.queryUserByName(username);
		if (u1 == null) {
			throw new UnknownAccountException();
		}
		return u1;
	}

	@Override
	public List<Role> queryAllRole() {
		List<Role> roles = this.userDao.queryAllRole();
		return roles;
	}

	@Override
	public User queryUserById(Integer userid) {
		User user = this.userDao.queryUserById(userid);
		return user;
	}

	@Override
	public Role queryRole(String roleid) {
		Role role = this.userDao.queryRole(roleid);
		return role;
	}

	@Override
	public Integer getCount(String username) {
		Integer total = this.userDao.getCount(username);
		return total;
	}

	@Override
	public List userList(String username, Integer total, Integer limit,
			Integer page) {
		List list = this.userDao.userList(username,total,limit,page);
		return list;
	}

}
