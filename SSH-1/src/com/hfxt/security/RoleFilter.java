package com.hfxt.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RoleFilter extends AuthorizationFilter {

	// 内部实现权限验证:
	@Override
	protected boolean isAccessAllowed(ServletRequest arg0,
			ServletResponse arg1, Object arg2) throws Exception {

		// 1.获取要求权限/角色.
		// 目标资源访问时的要求角色数组: arg2.
		String[] roles = (String[]) arg2;

		// 目标资源权限地址: request.getRequestUri()
		HttpServletRequest request = (HttpServletRequest) arg0;
		String uri = request.getRequestURI();

		// 2. 可以将shiro内部缓存的角色或权限做出以上比对判断:
		Subject sb = SecurityUtils.getSubject();

		// sb.hasRoles( list ); 可以直接判断真个数组中每个角色的比对结果.
		// sb.hasRole(arg0); 比对单个角色.
		for (String role : roles) {
			if (sb.hasRole(role)) {
				return true;
			}
		}
		
		if(uri.contains("addUser")){
			return true;
		}
		
		// 3.给出: 通过true/拒绝false 结果.
		return false;
	}

}
