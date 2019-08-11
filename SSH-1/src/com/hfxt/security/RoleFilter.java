package com.hfxt.security;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authz.AuthorizationFilter;

public class RoleFilter extends AuthorizationFilter {

	// �ڲ�ʵ��Ȩ����֤:
	@Override
	protected boolean isAccessAllowed(ServletRequest arg0,
			ServletResponse arg1, Object arg2) throws Exception {

		// 1.��ȡҪ��Ȩ��/��ɫ.
		// Ŀ����Դ����ʱ��Ҫ���ɫ����: arg2.
		String[] roles = (String[]) arg2;

		// Ŀ����ԴȨ�޵�ַ: request.getRequestUri()
		HttpServletRequest request = (HttpServletRequest) arg0;
		String uri = request.getRequestURI();

		// 2. ���Խ�shiro�ڲ�����Ľ�ɫ��Ȩ���������ϱȶ��ж�:
		Subject sb = SecurityUtils.getSubject();

		// sb.hasRoles( list ); ����ֱ���ж����������ÿ����ɫ�ıȶԽ��.
		// sb.hasRole(arg0); �ȶԵ�����ɫ.
		for (String role : roles) {
			if (sb.hasRole(role)) {
				return true;
			}
		}
		
		if(uri.contains("addUser")){
			return true;
		}
		
		// 3.����: ͨ��true/�ܾ�false ���.
		return false;
	}

}
