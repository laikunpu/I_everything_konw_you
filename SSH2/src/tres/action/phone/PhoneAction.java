package tres.action.phone;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import tres.action.phone.respEntity.RespBook;
import tres.action.phone.respEntity.RespBookList;
import tres.action.phone.respEntity.RespBookType;
import tres.entity.Book;
import tres.inter.IService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class PhoneAction extends ActionSupport {

	@Resource
	private IService service;

	private String jsonData;

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String choiceness() {
		System.out.println("PhoneAction->choiceness");

		// HttpServletRequest request = (HttpServletRequest) ActionContext
		// .getContext().get(ServletActionContext.HTTP_REQUEST);
		//
		// StringBuffer sb = new StringBuffer();
		// String s = null;
		// // 通过reader读取请求包含的数据
		// try {
		// request.setCharacterEncoding("UTF-8");
		// BufferedReader br = new BufferedReader(new InputStreamReader(
		// request.getInputStream()));
		//
		// while ((s = br.readLine()) != null) {
		// // 将读取到的数据存放到缓存区里面
		// sb.append(s);
		// }
		//
		// String json = URLDecoder.decode(sb.toString(), "UTF-8");
		// System.out.println("ContentType=" + request.getContentType());
		// System.out.println("json=" + json);
		// jsonObject = JSONObject.fromObject(json);
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		RespBookList bookList_json = new RespBookList();
		bookList_json.setRespBooks(new LinkedList<RespBook>());
		List<Book> books = service.getListByCondition(Book.class,
				"bookclicked", false, 0, 12);
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			RespBook book_json = new RespBook();
			book_json.setBookName(book.getBookname());
			book_json.setDowloadUrl(book.getBookpath());
			bookList_json.getRespBooks().add(book_json);
		}
		Gson gson = new Gson();
		jsonData = gson.toJson(bookList_json);
		System.out.println("choiceness   jsonData=" + jsonData);
		return "json";
	}

	public String classify() {
		System.out.println("PhoneAction->classify");
		RespBookType respBookType = (RespBookType) getFromJson(RespBookType.class);
		RespBookList bookList_json = new RespBookList();
		bookList_json.setRespBooks(new LinkedList<RespBook>());
		List<Book> books = service.findByCondition(Book.class,
				new String[] { "booktype" },
				new String[] { respBookType.getType() }, 0, 12);
		for (int i = 0; i < books.size(); i++) {
			Book book = books.get(i);
			RespBook book_json = new RespBook();
			book_json.setBookName(book.getBookname());
			book_json.setDowloadUrl(book.getBookpath());
			bookList_json.getRespBooks().add(book_json);
		}
		Gson gson = new Gson();
		jsonData = gson.toJson(bookList_json);
		System.out.println("classify  jsonData=" + jsonData);
		return "json";
	}

	private <T> Object getFromJson(Class<T> c) {
		HttpServletRequest request = (HttpServletRequest) ActionContext
				.getContext().get(ServletActionContext.HTTP_REQUEST);

		StringBuffer sb = new StringBuffer();
		String s = null;
		// 通过reader读取请求包含的数据
		try {
			request.setCharacterEncoding("UTF-8");
			BufferedReader br = new BufferedReader(new InputStreamReader(
					request.getInputStream()));

			while ((s = br.readLine()) != null) {
				// 将读取到的数据存放到缓存区里面
				sb.append(s);
			}

			String json = URLDecoder.decode(sb.toString(), "UTF-8");
			System.out.println("ContentType=" + request.getContentType());
			System.out.println("json=" + json);
			Gson gson = new Gson();
			return gson.fromJson(json, c);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
