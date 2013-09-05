package tres.action.phone.respEntity;

import java.io.Serializable;

public class RespBook implements Serializable {
	private String bookName;
	private String dowloadUrl;
	private String bookPath;

	public String getBookName() {
		return bookName;
	}

	public void setBookName(String bookName) {
		this.bookName = bookName;
	}

	public String getDowloadUrl() {
		return dowloadUrl;
	}

	public void setDowloadUrl(String dowloadUrl) {
		this.dowloadUrl = dowloadUrl;
	}

	public String getBookPath() {
		return bookPath;
	}

	public void setBookPath(String bookPath) {
		this.bookPath = bookPath;
	}

	public RespBook(String bookName, String bookPath) {
		this.bookName = bookName;
		this.bookPath = bookPath;
	}

	public RespBook() {
	}
}
