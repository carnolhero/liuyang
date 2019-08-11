package com.hfxt.dao;

import java.util.List;
import java.util.Map;

import com.hfxt.bean.Role;
import com.hfxt.bean.User;

public interface UserDao {

	// 新增
	public int addUser(User user);
	
	// 删除
	public boolean delUser(int id);

	// 修改
	public boolean updateUser(User user);

	// 根据用户名查用户
	public User queryUserByName(String username);

	public List<Role> queryAllRole();

	public User queryUserById(Integer userid);

	public Role queryRole(String roleid);

	public List userList(String username, Integer total, Integer limit, Integer page);

	public Integer getCount(String username);

}
