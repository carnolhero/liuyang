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

//�䵱shiro��֤���:  realmʹ��.
public class MyRealm extends AuthorizingRealm {

	// ע��service:
	@Autowired
	private UserService userService;

	// zation:��Ȩ.
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection arg0) {

		// ǰ��: ��ȡ��ǰ��¼�û��ģ� ��ɫ��Ȩ��.
		User u1 = (User) SecurityUtils.getSubject().getPrincipal();
		
		// ����Service������û�ID��ѯ��ɫ/Ȩ��. ģ��һ����ɫ:
		String role = u1.getRole().getRolename();
		
		// �������ǰ��¼�û��ģ� ��ɫ��Ȩ��. SimpleAuthorizationInfo
		SimpleAuthorizationInfo zation = new SimpleAuthorizationInfo();

		// ���Դ洢��ɫ��Ȩ��:
		// zation.addObjectPermission(permission); //���Դ洢һ��Ȩ�޶���.
		// zation.addObjectPermissions(permissions); //���Դ洢һ��Ȩ�޼���.
		// zation.addRole(role); // ���Դ洢һ���ַ�����ɫ.
		// zation.addRoles(roles); //���Դ洢һ�����ϵ��ַ�����ɫ.
		// zation.addStringPermission(permission); //���Դ洢һ��Ȩ���ַ���.
		// zation.addStringPermissions(permissions); //���Դ洢һ��Ȩ���ַ�������.

		// �����ɫ���:
		zation.addRole(role);

		return zation;
	}

	// cation : ��֤.
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken arg0) throws AuthenticationException {

		// 1.��ȡ�˺ź�����.
		UsernamePasswordToken token1 = (UsernamePasswordToken) arg0;
		String username = token1.getUsername();
		String password = new String(token1.getPassword());

		// 2.ǰ��ע��Service��: ���ȸ����˺Ų�ѯ�û�����ѯ�ɹ������û�����. ��ѯΪnull.�����쳣��ǰ��:
		// UnknowAccountExcepion.
		User u1 = this.userService.queryUserByName(username);
		ServletActionContext.getRequest().getSession().setAttribute("user", u1);

		// 3.�ȶ�����: ����SimpleAuthenticationInfo ʵ����ɱȶ�����. �͵��ɹ������ݻ���. �͵�ʧ�ܺ���쳣�׳�.
		// ��һ������: ������ȶԳɹ���,�������ݶ���.
		// �ڶ�������: ��token�����뵱ǰ�����������ȶ�.Ĭ��:������ܱȶ�.
		SimpleAuthenticationInfo cation = new SimpleAuthenticationInfo(u1,
				u1.getPassword(), getName());
		return cation;
	}

}
