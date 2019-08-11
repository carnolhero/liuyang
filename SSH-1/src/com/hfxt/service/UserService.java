package com.hfxt.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

import com.hfxt.bean.Role;
import com.hfxt.bean.User;

public interface UserService {

	public int addUser(User user);

	public boolean delUser(int id);

	public boolean updateUser(User user);

	public User queryUserByName(String username);

	public List<Role> queryAllRole();

	public User queryUserById(Integer userid);

	public Role queryRole(String roleid);

	public List userList(String username, Integer total, Integer limit, Integer page);

	public Integer getCount(String username);

}
