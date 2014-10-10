package com.mgang.utils;

import java.util.List;


import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
@SuppressWarnings("unchecked")
public class HibernateUtil {

	private static final SessionFactory sessionFactory;
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static ThreadLocal thread = new ThreadLocal();
	public static int pageCount;

	private HibernateUtil() {
	};

	static {
		//本地sessionFactory = new Configuration().configure("local.hibernate.cfg.xml").buildSessionFactory();
		sessionFactory = new Configuration().configure().buildSessionFactory();
	}

	public static Session openSession() {
		return sessionFactory.openSession();
	}

	@SuppressWarnings("unchecked")
	public static Session getCurrentSession() {
		Session session = (Session) thread.get();
		if (session == null) {
			session = sessionFactory.openSession();
			thread.set(session);
		}
		return session;
	}

	public static void executeUpdate(String hql, String paras[]) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();
			Query q = s.createQuery(hql);
			if (paras != null && paras.length != 0) {
				for (int i = 0; i < paras.length; i++) {
					q.setString(i, paras[i]);
				}
			}
			@SuppressWarnings("unused")
			int rows = q.executeUpdate();
			
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}

	}

	public static void save(Object obj) {
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();
			s.save(obj);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
	}

	@SuppressWarnings("unchecked")
	public static List executeQueryByPage(String hql, String paras[],
			int pageNow, int pageSize) {

		Session s = null;
		Transaction tx = null;
		List list = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();

			Query q = s.createQuery(hql);
			if (paras != null && paras.length != 0) {
				for (int i = 0; i < paras.length; i++) {
					q.setString(i, paras[i]);
				}
			}
			q.setFirstResult((pageNow - 1) * pageSize).setMaxResults(pageSize);
			list = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return list;
	}

	public static int pageCount(String hql, String paras[]) {
		Session s = null;
		Transaction tx = null;
		int pageCount = 0;
		try {
			s = HibernateUtil.openSession();
			;
			tx = s.beginTransaction();
			Query q = s.createQuery(hql);
			if (paras != null && paras.length != 0) {
				for (int i = 0; i < paras.length; i++) {
					q.setString(i, paras[i]);
				}
			}
			List<Long> list = q.list();
			if (list.size() == 1) {
				Long row = list.get(0);
				int rows = row.intValue();
				if (rows % 3 == 0) {
					pageCount = rows / 3;
				} else {
					pageCount = rows / 3 + 1;
				}
			}
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return pageCount;
	}

	// 统一锟侥诧拷询
	@SuppressWarnings("unchecked")
	public static List executeQuery(String hql, String paras[]) {
		List list = null;
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();
			Query q = s.createQuery(hql);
			if (paras != null && paras.length != 0) {
				for (int i = 0; i < paras.length; i++) {
					q.setString(i, paras[i]);
				}
			}
			list = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return list;
	}

	public static Object uniqueQuery(String hql, String paras[]) {
		Object obj = null;
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();
			Query q = s.createQuery(hql);
			if (paras != null && paras.length != 0) {
				for (int i = 0; i < paras.length; i++) {
					q.setString(i, paras[i]);
				}
			}
			obj = q.uniqueResult();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return obj;
	}

	public static List<Object> getOneColum(String hql, String paras[]) {
		List<Object> obj = null;
		Session s = null;
		Transaction tx = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();
			Query q = s.createQuery(hql);
			if (paras != null && paras.length != 0) {
				for (int i = 0; i < paras.length; i++) {
					q.setString(i, paras[i]);
				}
			}
			obj = q.list();
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return obj;
	}

	/**
	 * @author Administrator meigang
	 * @return load锟斤拷玫锟斤拷亩锟斤拷锟�
	 */
	public static Object findById(Class clazz, java.io.Serializable id) {
		Session s = null;
		Transaction tx = null;
		Object obj = null;
		try {
			s = HibernateUtil.openSession();
			tx = s.beginTransaction();
			obj = s.load(clazz, id);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			throw new RuntimeException(e.getMessage());
			// TODO: handle exception
		} finally {
			if (s != null && s.isOpen()) {
				s.close();
			}
		}
		return obj;
	}
}
