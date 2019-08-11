package com.hfxt.dao.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.classic.Session;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.hfxt.bean.Role;
import com.hfxt.bean.User;
import com.hfxt.dao.UserDao;

public class UserDaoImpl extends HibernateDaoSupport implements UserDao {

	// ���� : ���Եļ��� Ҫ����setSessionFactory() . ����getHibernateTemplate()����ֱ�ӵ�����ʹ��.
	// ����û��ע��ɹ�,���Ѿ���ȡ��null��ģ��.

	// ����ͨ��hibernateTemplateģ�������ʵ����ɾ�Ĳ�.

	@Override
	public int addUser(User user) {
		Serializable s1 = getHibernateTemplate().save(user);
		return Integer.valueOf(s1.toString());
	}

	// ɾ��
	@Override
	public boolean delUser(int id) {
		boolean flag = false;

		User user = new User();
		user.setId(id);

		try {
			getHibernateTemplate().delete(user);
			flag = true;
		} catch (Exception e) {
			System.out.println("�쳣Ϊ��" + e);
		}
		return flag;
	}

	// �޸�
	@Override
	public boolean updateUser(User user) {
		boolean flag = false;
		try {
			getHibernateTemplate().update(user.getUsername(), user);
			flag = true;
		} catch (Exception e) {
			System.out.println("�쳣Ϊ��" + e);
		}
		return flag;
	}

	// �����û�����ѯ
	@Override
	public User queryUserByName(String username) {
		List list = getHibernateTemplate().find(
				"from User u where u.username = ?", username);

		if (list != null && list.size() > 0) {
			User u1 = (User) list.get(0);
			return u1;
		}
		return null;
	}

	@Override
	public List<Role> queryAllRole() {
		List role = getHibernateTemplate().find("from Role");

		for (int i = 0; i < role.size(); i++) {
			Role ro = (Role) role.get(i);

			if (ro.getRolename().equals("admin")) {
				role.remove(role.get(i));
			}
		}
		return role;
	}

	@Override
	public User queryUserById(Integer userid) {
		User user = getHibernateTemplate().get(User.class, userid);
		return user;
	}

	@Override
	public Role queryRole(String roleid) {
		Integer id = 0;
		Role role = null;
		try {
			id = Integer.valueOf(roleid);
			role = getHibernateTemplate().get(Role.class, id);
		} catch (Exception e) {
			// TODO: handle exception
		}
		return role;
	}

	// ��ȡ�������ݿ�����

	@Override
	public Integer getCount(String username) {
		StringBuffer sql = new StringBuffer(
				"select count(*) from User where 1=1 ");
		if (username != null && !username.trim().equals("")) {
			sql.append("and username like '%" + username + "%'");
		}
		List list = getHibernateTemplate().find(sql.toString());
		Integer total = Integer.valueOf(list.get(0).toString());
		return total;
	}

	// pid2=limit curr=page
	@Override
	public List userList(String username, Integer total, Integer limit,
			Integer page) {
		Session session = null;
		Transaction tx = null;
		SessionFactory sessionFactory = null;
		List<User> list = null;

		try {
			sessionFactory = getHibernateTemplate().getSessionFactory();
			session = sessionFactory.openSession();
			tx = session.beginTransaction();

			StringBuffer sql = new StringBuffer("from User where 1=1 ");
			if (username != null && !username.trim().equals("")) {
				sql.append("and username like '%" + username + "%'");
			}

			Query query = session.createQuery(sql.toString());
			Integer start = (page - 1) * limit;
			query.setFirstResult(start);
			query.setMaxResults(limit);

			list = new ArrayList<User>(query.list());
			tx.commit();

		} catch (Exception e) {
			// TODO: handle exception
		} finally {
			session.close();
		}
		return list;
	}

}
