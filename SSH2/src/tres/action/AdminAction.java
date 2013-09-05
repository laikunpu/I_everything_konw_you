package tres.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import net.sf.json.JSONObject;
import tres.entity.Admin;
import tres.entity.ErrorMsg;
import tres.inter.IService;
import tres.util.SSH2Constant;
import tres.util.SSH2Uitil;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class AdminAction extends ActionSupport {
	@Resource
	private IService service;
	private Admin admin;
	private ErrorMsg errorMsg;
	private String jsonData;

	public Admin getAdmin() {
		return admin;
	}

	public void setAdmin(Admin admin) {
		this.admin = admin;
	}

	public ErrorMsg getErrorMsg() {
		return errorMsg;
	}

	public void setErrorMsg(ErrorMsg errorMsg) {
		this.errorMsg = errorMsg;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String login() {
		List<Admin> admins = service.findByCondition(Admin.class,
				new String[] { "username" },
				new String[] { admin.getUsername() }, 0, 0);
		Admin reAdmin = null;
		if (null != admins) {
			reAdmin = admins.get(0);
		}
		if (null != reAdmin) {
			if (reAdmin.getPassword().equals(admin.getPassword())) {
				if (reAdmin.getLevel() < 10) {
					System.err.println("权限值：" + reAdmin.getLevel());
					errorMsg = new ErrorMsg(0, "权限不足！！！");
					return "error";
				}

				return "manager";
			}
		}
		return "error";
	}

	public String scanAll() {
		System.out.println("scanAll()");
		SSH2Constant.STOPSACN = false;
		JSONObject jsonObject = new JSONObject();
		if (null != SSH2Uitil.writeExecutor
				&& !SSH2Uitil.writeExecutor.isTerminated()) {
			System.out.println("正在扫描中。。。");
			jsonObject.put("status", "正在扫描中！！！");
		} else {
			SSH2Uitil.writeExecutor = Executors.newFixedThreadPool(20);
			jsonObject.put("status", "开始扫描！！！");
			System.out.println("开始扫描");

			service.scanDirRecursion(new File("D:\\SSH2\\novel"));
			SSH2Uitil.writeExecutor.shutdown();
		}
		jsonData = jsonObject.toString();
		return "json";
	}

	public String stopScan() {
		JSONObject jsonObject = new JSONObject();

		if (null != SSH2Uitil.writeExecutor
				&& !SSH2Uitil.writeExecutor.isTerminated()) {
			SSH2Uitil.writeExecutor.shutdown();
			SSH2Constant.STOPSACN = true;
			jsonObject.put("status", "已发送停止扫描请求！！！");
		} else {
			jsonObject.put("status", "扫描已经停止！！！");

		}
		jsonData = jsonObject.toString();
		return "json";
	}
}
