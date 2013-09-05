package tres.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.json.JSONObject;

import tres.entity.BookMark;
import tres.entity.Reader;
import tres.inter.IService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class LoginAction extends ActionSupport {

	@Resource
	private IService service;

	private Reader reader;
	private BookMark bookMark;
	private String jsonData;
	private String[] ckx_bookMark;

	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

	public BookMark getBookMark() {
		return bookMark;
	}

	public void setBookMark(BookMark bookMark) {
		this.bookMark = bookMark;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public String[] getCkx_bookMark() {
		return ckx_bookMark;
	}

	public void setCkx_bookMark(String[] ckx_bookMark) {
		this.ckx_bookMark = ckx_bookMark;
	}

	public String register() {
		System.out.println("register");
		JSONObject jsonObject = new JSONObject();
		if (null != reader && !"".equals(reader.getUsername())
				&& !"".equals(reader.getPassword())) {
			Reader findReader = (Reader) service.findByConditionForOne(
					Reader.class, new String[] { "username" },
					new String[] { reader.getUsername() });
			if (findReader == null) {
				reader.setPoints(0);
				reader.setVotes(0);
				service.addT(reader);
				Map session = ActionContext.getContext().getSession();
				session.put("user", reader);
				jsonObject.put("message", "success");
			} else {
				jsonObject.put("message", "error");
			}

		}
		jsonData=jsonObject.toString();
		return "json";
	}

	public String login() {
		System.out.println("login");
		JSONObject jsonObject = new JSONObject();
		if (null != reader) {

			Reader readerFind = (Reader) service.findByConditionForOne(
					Reader.class, new String[] { "username" },
					new String[] { reader.getUsername() });
			if (null != readerFind
					&& readerFind.getPassword().equals(reader.getPassword())) {
				Map session = ActionContext.getContext().getSession();
				session.put("user", readerFind);
				jsonObject.put("message", "success");
			} else {
				jsonObject.put("message", "error");
			}

		} else {
			jsonObject.put("message", "error");
		}
		jsonData = jsonObject.toString();
		return "json";
	}

	public String logout() {
		JSONObject jsonObject = new JSONObject();
		Map session = ActionContext.getContext().getSession();
		session.remove("user");
		jsonObject.put("message", "success");
		jsonData = jsonObject.toString();
		return "json";
	}

	public String goUser() {
		Map session = ActionContext.getContext().getSession();
		reader = (Reader) session.get("user");
		if (null != reader) {
			reader = (Reader) service
					.findByConditionForOne(Reader.class, new String[] { "id" },
							new String[] { reader.getId() + "" });
			return "user";
		}
		return "error";
	}

	public String setBookMark() {
		System.out.println("bookMark->chapternumber="
				+ bookMark.getChapternumber());
		JSONObject jsonObject = new JSONObject();
		Map session = ActionContext.getContext().getSession();
		reader = (Reader) session.get("user");
		if (null != reader) {
			List<Reader> readers = service.findByCondition(Reader.class,
					new String[] { "username" },
					new String[] { reader.getUsername() }, 0, 0);
			if (null != readers && readers.size() > 0 && null != bookMark) {

				Reader readerFind = readers.get(0);
				for (BookMark mark : readerFind.getBookMarks()) {
					if (mark.getBookname().equals(bookMark.getBookname())) {
						if (null == bookMark.getBookchapter()
								|| "".equals(bookMark.getBookchapter())) {
							jsonObject.put("message", "1");// 1 ����Ѿ����ڴ���

						} else if (bookMark.getBookchapter().equals(
								mark.getBookchapter())) {
							jsonObject.put("message", "2");// 2 ���½��Ѵ���
						} else {
							mark.setChapternumber(bookMark.getChapternumber());
							mark.setBookchapter(bookMark.getBookchapter());
							jsonObject.put("message", "3");// 3 ������ǩ�ɹ�
							service.updateT(mark);
						}
						jsonData = jsonObject.toString();
						return "json";
					}
				}
				bookMark.setReader(readerFind);
				service.addT(bookMark);
				if (null != bookMark.getBookchapter()
						&& !"".equals(bookMark.getBookchapter())) {
					jsonObject.put("message", "3");// 3 ������ǩ�ɹ�
				} else {
					jsonObject.put("message", "success");
				}

			} else {
				jsonObject.put("message", "error");
			}
		} else {
			jsonObject.put("message", "error");
		}
		jsonData = jsonObject.toString();
		return "json";
	}

	public String deleteBookmark() {
		JSONObject jsonObject = new JSONObject();
		if (null != ckx_bookMark) {
			for (String bookname : ckx_bookMark) {
				BookMark mark = (BookMark) service.findByConditionForOne(
						BookMark.class, new String[] { "bookname" },
						new String[] { bookname });
				if (null != mark) {
					mark.setReader(null);
					service.updateT(mark);
					service.delT(mark.getId(), BookMark.class);

				}

			}
		}
		jsonObject.put("message", "success");
		jsonData = jsonObject.toString();
		return "json";
	}
}