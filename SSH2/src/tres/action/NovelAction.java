package tres.action;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import net.sf.json.JSONObject;

import org.apache.struts2.ServletActionContext;

import tres.entity.Book;
import tres.entity.BookComment;
import tres.entity.BookType;
import tres.entity.Chapter;
import tres.entity.Pagination;
import tres.entity.Reader;
import tres.entity.SearchItem;
import tres.inter.IService;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("unchecked")
public class NovelAction extends ActionSupport {
	@Resource
	private IService service;

	private List<Book> books;// 排行榜小说集合
	private List<Book> hotkooks;// 热门小说集合
	private List<Book> recommendBooks;// 读者推荐小说集合
	private List<Chapter> chapters;
	private Book book; // 单本小说
	private Chapter chapter; // 一段章节
	private String content; // 章节内容
	private Pagination page;
	private List<BookType> bookTypes; // 小说类型集合
	private SearchItem searchItem; // 搜索信息
	private String message; // 无数据返回的信息
	private String jsonData;
	private BookComment bookComment;

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public List<Book> getHotkooks() {
		return hotkooks;
	}

	public void setHotkooks(List<Book> hotkooks) {
		this.hotkooks = hotkooks;
	}

	public List<Book> getRecommendBooks() {
		return recommendBooks;
	}

	public void setRecommendBooks(List<Book> recommendBooks) {
		this.recommendBooks = recommendBooks;
	}

	public List<Chapter> getChapters() {
		return chapters;
	}

	public void setChapters(List<Chapter> chapters) {
		this.chapters = chapters;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Chapter getChapter() {
		return chapter;
	}

	public void setChapter(Chapter chapter) {
		this.chapter = chapter;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Pagination getPage() {
		return page;
	}

	public void setPage(Pagination page) {
		this.page = page;
	}

	public List<BookType> getBookTypes() {
		return bookTypes;
	}

	public void setBookTypes(List<BookType> bookTypes) {
		this.bookTypes = bookTypes;
	}

	public SearchItem getSearchItem() {
		return searchItem;
	}

	public void setSearchItem(SearchItem searchItem) {
		this.searchItem = searchItem;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getJsonData() {
		return jsonData;
	}

	public void setJsonData(String jsonData) {
		this.jsonData = jsonData;
	}

	public BookComment getBookComment() {
		return bookComment;
	}

	public void setBookComment(BookComment bookComment) {
		this.bookComment = bookComment;
	}

	public String goIndex() {
		int currentPage = 0;
		int bookcount = service.getCount(Book.class, null, null);
		if (page != null) {
			currentPage = page.getCurrentPage();
		}
		books = service.getListByCondition(Book.class, "bookclicked", false,
				currentPage * 10, 10);
		hotkooks = books;// 排行榜排序未完成
		recommendBooks = service.getListByCondition(Book.class,
				"bookrecommend", false, currentPage * 10, 10);
		page = new Pagination(bookcount, currentPage, 10);
		System.out.println(page.toString());
		System.out.println("书的总数目:" + bookcount);
		bookTypes = service.findAllT(BookType.class);

		return "realIndex";
	}

	public String goChapter() {
		// System.out.println("book.bookname:" + book.getBookname());
		if (null != book && !"".equals(book.getBookname())) {
			chapters = service.findByCondition(Chapter.class,
					new String[] { "novelname" },
					new String[] { book.getBookname() }, 0, 0);
			book = (Book) service.findByCondition(Book.class,
					new String[] { "bookname" },
					new String[] { book.getBookname() }, 0, 0).get(0);
			if (null != book) {
				book.setBookclicked(book.getBookclicked() + 1);
				service.updateT(book);
			}
			bookTypes = service.findAllT(BookType.class);
			return "chapter";
		}
		return "error";
	}

	public String goDetail() {

		if (null != book && !"".equals(book.getBookname())) {

			bookTypes = service.findAllT(BookType.class);
			book = (Book) service.findByConditionForOne(Book.class,
					new String[] { "bookname" },
					new String[] { book.getBookname() });
			return "detail";
		}
		return "error";
	}

	public String goContent() {

		chapters = service.findByCondition(
				Chapter.class,
				new String[] { "novelname", "chapternumber" },
				new String[] { chapter.getNovelname(),
						String.valueOf(chapter.getChapternumber()) }, 0, 0);
		if (chapters != null && chapters.size() > 0) {
			chapter = chapters.get(0);
			try {
				content = new String(chapter.getChaptercontent(), "UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			bookTypes = service.findAllT(BookType.class);
			return "content";
		} else {

		}
		// System.out.println(content);
		return "error";
	}

	public String goList() {
		int currentPage = 0;
		int bookcount = 0;
		String[] fields = null;
		String[] values = null;
		// 此判断是根据传过来的条件返回List,分页数据
		if (book != null && !"".equals(book.getBooktype())) {
			bookcount = service.getCount(Book.class, "booktype",
					book.getBooktype());
			fields = new String[] { "booktype" };
			values = new String[] { book.getBooktype() };
			message = book.getBooktype() + "分类下暂无小说！！！";
		} else if (searchItem != null) {
			bookcount = service.getCount(Book.class, searchItem.getItem(),
					searchItem.getKeyword());
			fields = new String[] { searchItem.getItem() };
			values = new String[] { searchItem.getKeyword() };
			message = searchItem.getKeyword() + "   查询结果为空！！！";

		} else {
			bookcount = service.getCount(Book.class, null, null);
			message = "书库暂无小说！！！";
		}
		books = service.findByCondition(Book.class, fields, values,
				currentPage * 50, 50);
		if (books.size() > 0) {
			message = null;
		}

		bookTypes = service.findAllT(BookType.class);// 为top.jsp返回书的类型
		if (page != null) {
			currentPage = page.getCurrentPage();
		}
		page = new Pagination(bookcount, currentPage, 50);
		return "list";
	}

	public String goRecommend() {
		JSONObject jsonObject = new JSONObject();
		if (null != book && !"".equals(book.getBookname())) {
			List<Book> books = service.findByCondition(Book.class,
					new String[] { "bookname" },
					new String[] { book.getBookname() }, 0, 0);
			if (null != books && books.size() > 0) {
				book = books.get(0);
				book.setBookrecommend(book.getBookrecommend() + 1);
				service.updateT(book);
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

	public String comment() {
		System.out.println("comment");
		JSONObject jsonObject = new JSONObject();
		Book findBook = (Book) service.findByConditionForOne(Book.class,
				new String[] { "bookname" },
				new String[] { book.getBookname() });
		if (findBook != null && bookComment != null) {
			Map session = ActionContext.getContext().getSession();
			Reader reader = (Reader) session.get("user");
			if (null != reader) {
				bookComment.setReader_id(reader.getId());
				bookComment.setReadername(reader.getNickname());
			} else {
				bookComment.setReader_id(-1);
				bookComment.setReadername("游客");
			}
			bookComment.setBook(findBook);
			service.addT(bookComment);
			jsonObject.put("message", "success");
		} else {
			jsonObject.put("message", "error");
		}
		jsonData = jsonObject.toString();
		return "json";
	}

	
}
