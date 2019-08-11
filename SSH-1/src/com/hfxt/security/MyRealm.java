package com.hfxt.security;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.hfxt.bean.Role;
import com.hfxt.bean.User;
import com.hfxt.service.UserService;

//充当shiro认证组件:  realm使用.
public class MyRealm extends AuthorizingRealm {

	// 注入service:
	@Autowired
	private UserService userService;

	// zation:授权.
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

		// 前提: 获取当前登录用户的： 角色或权限.
		User u1 = (User) SecurityUtils.getSubject().getPrincipal();
		
		// 调用Service层根据用户ID查询角色/权限. 模拟一个角色:
		String role = u1.getRole().getRolename();
		
		// 缓存出当前登录用户的： 角色或权限. SimpleAuthorizationInfo
		SimpleAuthorizationInfo zation = new SimpleAuthorizationInfo();

		// 可以存储角色或权限:
		// zation.addObjectPermission(permission); //可以存储一个权限对象.
		// zation.addObjectPermissions(permissions); //可以存储一个权限集合.
		// zation.addRole(role); // 可以存储一个字符串角色.
		// zation.addRoles(roles); //可以存储一个集合的字符串角色.
		// zation.addStringPermission(permission); //可以存储一个权限字符串.
		// zation.addStringPermissions(permissions); //可以存储一个权限字符串集合.

		// 缓存角色身份:
		zation.addRole(role);

		return zation;
	}

	// cation : 认证.
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {

		// 1.获取账号和密码.
		UsernamePasswordToken token1 = (UsernamePasswordToken) arg0;
		String username = token1.getUsername();
		String password = new String(token1.getPassword());

		// 2.前提注入Service层: 首先根据账号查询用户，查询成功返回用户对象. 查询为null.返回异常给前端:
		// UnknowAccountExcepion.
		User u1 = this.userService.queryUserByName(username);
		ServletActionContext.getRequest().getSession().setAttribute("user", u1);

		// 3.比对密码: 借助SimpleAuthenticationInfo 实例完成比对密码. 和当成功后的身份缓存. 和当失败后的异常抛出.
		// 第一个参数: 当密码比对成功后,缓存的身份对象.
		// 第二个参数: 拿token密码与当前参数密码作比对.默认:无需加密比对.
		SimpleAuthenticationInfo cation = new SimpleAuthenticationInfo(u1,
				u1.getPassword(), getName());
		return cation;
	}

}
