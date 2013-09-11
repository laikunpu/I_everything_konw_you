package com.smith.http.webservice.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.hibernate.SessionFactory;

import com.smith.http.webservice.inter.IDao;
import com.smith.http.webservice.util.TNUtil;

/**
 * @author lai
 * 
 * @param <T>
 */
public class DaoImpl<T> implements IDao<T> {
	protected SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	@Override
	public void addT(T t) {
		this.sessionFactory.getCurrentSession().persist(t);
	}

	@Override
	public List<T> findT(int id, Class<T> c) {
		// TODO Auto-generated method stub
		if (id > 0) {
			String hql = "from " + TNUtil.getTableName(c) + " as t where t.id=:id";
			Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
			query.setInteger("id", id);
			List<T> list = query.list();
			return list;
		}
		return null;

	}

	@Override
	public boolean delT(int id, Class<T> c) {
		// TODO Auto-generated method stub
		List<T> list = findT(id, c);
		if (list != null && list.size() > 0) {
			this.sessionFactory.getCurrentSession().delete(list.get(0));
			return true;
		}
		return false;
	}

	@Override
	public void updateT(T t) {
		// TODO Auto-generated method stub
		this.sessionFactory.getCurrentSession().update(t);
	}

	@Override
	public List<T> findByCondition(Class<T> c, Map<String, String> map, int first, int max) {
		StringBuffer hql = new StringBuffer("from " + TNUtil.getTableName(c) + " as a ");
		Query query = null;
		List<T> list = null;
		if (null != map) {
			// StringBuffer hql = "from " + SSH2Uitil.getTableName(c) +
			// " as a where a."
			// + field + "=:" + field;
			hql.append(" where ");
			for (Object o : map.keySet()) {
				hql.append("a." + o.toString() + "=:" + o.toString() + " and ");
			}
			hql.delete(hql.length() - 5, hql.length());
			// System.out.println("findByCondition hql:   " + hql);
			query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());

			for (Object o : map.keySet()) {
				query.setString(o.toString(), map.get(o));
			}

		} else {
			query = this.sessionFactory.getCurrentSession().createQuery(hql.toString());

		}
		if (first >= 0 && max > 0) {
			query.setFirstResult(first);
			query.setMaxResults(max);
		}
		list = query.list();
		map = null;
		return list;

	}

	@Override
	public List<T> getListByCondition(Class<T> c, String field, boolean ascOrdesc, int first, int max) {
		// TODO Auto-generated method stub
		String Condition = "desc";
		if (ascOrdesc) {
			Condition = "asc";
		}
		String hql = "from " + TNUtil.getTableName(c) + " as a order by a." + field + " " + Condition;
		// String hql = "from " + SSH2Uitil.getTableName(c);
		Query query = this.sessionFactory.getCurrentSession().createQuery(hql);
		query.setFirstResult(first);
		query.setMaxResults(max);
		List<T> list = query.list();
		return list;
	}

	@Override
	public int getCount(Class<T> c, String field, String name) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from " + TNUtil.getTableName(c);
		Query query = null;
		if (null != field && null != name) {
			hql += " where " + field + "=:" + field;
			query = this.sessionFactory.getCurrentSession().createQuery(hql);
			query.setString(field, name);
		} else {
			query = this.sessionFactory.getCurrentSession().createQuery(hql);
		}
		// System.out.println("getCount  hql:" + hql);
		int count = Integer.parseInt(query.uniqueResult().toString());
		return count;
	}

}
