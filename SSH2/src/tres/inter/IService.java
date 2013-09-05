package tres.inter;

import java.io.File;
import java.util.List;
import java.util.Map;

import tres.entity.Admin;

public interface IService<T> {
	public void addT(T t);

	public List<T> findT(int id, Class<T> c);

	public boolean delT(int id, Class<T> c);

	public void updateT(T t);

	public List<T> findAllT(Class<T> c);

	public boolean scanDirRecursion(File file);

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
	public List<T> getListByCondition(Class<T> c, String field,
			boolean ascOrdesc, int first, int max);

	/**
	 * 根据字段查找指定的表中有多少条记录
	 * 
	 * @param c
	 *            实体类Class
	 * @param field
	 *            实体 为null则查询整张表 && 名字 为null则查询整张表
	 * @param name
	 *            名字
	 * @return
	 */
	public int getCount(Class<T> c, String field, String name);

	/**
	 * 
	 * @param c
	 * @param fields
	 *            fields是要查询的实体字段数组
	 * @param values
	 *            values是值的数组 fields和values长度必须相等， 否则无条件查询。
	 * @param first
	 *            first 查询开始位置
	 * @param max
	 *            max 最大查询条数 first必须>=0 && max 必须>0 否则返回全部
	 * @return
	 */
	public List<T> findByCondition(Class<T> c, String[] fields,
			String[] values, int first, int max);

	/**
	 * 只返回第一个数据或null
	 * 
	 * @param c
	 * @param fields
	 *            fields是要查询的实体字段数组
	 * @param values
	 *            values是值的数组 fields和values长度必须相等， 否则无条件查询。
	 * 
	 * @return
	 */
	public T findByConditionForOne(Class<T> c, String[] fields, String[] values);

}
