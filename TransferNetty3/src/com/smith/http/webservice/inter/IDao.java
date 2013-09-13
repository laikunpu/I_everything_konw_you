package com.smith.http.webservice.inter;

import java.util.List;
import java.util.Map;

/**
 * 
 * @author lai
 * 
 * @param <T>
 */
public interface IDao<T> {
	/**
	 * 
	 * @param t
	 *            添加t
	 */
	public void addT(T t);

	/**
	 * 
	 * @param id
	 * @param c
	 * @return 返回List
	 */
	public List<T> findT(int id, Class<T> c);

	/**
	 * 
	 * @param id
	 * @param c
	 * @return
	 */
	public boolean delT(int id, Class<T> c);

	/**
	 * 
	 * @param t
	 */
	public void updateT(T t);

	/**
	 * 
	 * @param c
	 * @param map
	 *            map=null将会查询所有
	 * @param first
	 *            first 查询开始位置
	 * @param max
	 *            max 最大查询条数 first必须>=0 && max 必须>0 否则返回全部
	 * @return
	 */
	public List<T> findByCondition(Class<T> c, Map<String, String> map, int first, int max);

	/**
	 * 
	 * @param c
	 * @param field
	 *            要查询的字段
	 * @param ascOrdesc
	 *            true:升序 false:降序
	 * @param first
	 *            起始位置
	 * @param max
	 *            条数
	 * @return
	 */
	public List<T> getListByCondition(Class<T> c, String field, boolean ascOrdesc, int first, int max);

	/**
	 * 根据字段查找指定的表中有多少条记录
	 * 
	 * @param c
	 *            实体类Class
	 * @param field
	 *            实体 为null则查询整张表 && 名字 为null则查询整张表
	 * @param name
	 *            // * 名字
	 * @return
	 */
	public int getCount(Class<T> c, String field, String name);

	public T findT(String name, Class<T> c);
}
