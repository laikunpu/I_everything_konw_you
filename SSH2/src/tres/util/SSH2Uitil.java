package tres.util;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SSH2Uitil {
	public static ExecutorService writeExecutor = null;

	/**
	 * 根据Class获取类的名字
	 * 
	 * @param c
	 * @return
	 */
	public static <T> String getTableName(Class<T> c) {
		String tb_name = c.toString();
		return tb_name = tb_name.substring(tb_name.lastIndexOf(".") + 1);
	}
}
