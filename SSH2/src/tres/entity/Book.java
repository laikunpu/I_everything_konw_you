package tres.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tb_book")
public class Book {
	private int id;
	private String bookauthor;
	private String bookname;
	private String booktype;
	private String bookdetailtype;
	private int booknumber;
	private int bookclicked;
	private int bookrecommend;
	private int booktotalnumber;
	private String bookpath;
	private List<BookComment> bookComments = new ArrayList<BookComment>();

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "Id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getBookauthor() {
		return bookauthor;
	}

	public void setBookauthor(String bookauthor) {
		this.bookauthor = bookauthor;
	}

	@Column
	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	@Column
	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

	@Column
	public String getBookdetailtype() {
		return bookdetailtype;
	}

	public void setBookdetailtype(String bookdetailtype) {
		this.bookdetailtype = bookdetailtype;
	}

	@Column
	public int getBooknumber() {
		return booknumber;
	}

	public void setBooknumber(int booknumber) {
		this.booknumber = booknumber;
	}

	@Column
	public int getBookclicked() {
		return bookclicked;
	}

	public void setBookclicked(int bookclicked) {
		this.bookclicked = bookclicked;
	}

	@Column
	public int getBookrecommend() {
		return bookrecommend;
	}

	public void setBookrecommend(int bookrecommend) {
		this.bookrecommend = bookrecommend;
	}

	@Column
	public int getBooktotalnumber() {
		return booktotalnumber;
	}

	public void setBooktotalnumber(int booktotalnumber) {
		this.booktotalnumber = booktotalnumber;
	}

	@Column
	public String getBookpath() {
		return bookpath;
	}

	public void setBookpath(String bookpath) {
		this.bookpath = bookpath;
	}
	
	@OneToMany(mappedBy = "book", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public List<BookComment> getBookComments() {
		return bookComments;
	}

	public void setBookComments(List<BookComment> bookComments) {
		this.bookComments = bookComments;
	}

	public Book(String bookauthor, String bookname, String booktype,
			String bookdetailtype, int booknumber, int bookclicked,
			int bookrecommend, int booktotalnumber, String bookpath) {
		this.bookauthor = bookauthor;
		this.bookname = bookname;
		this.booktype = booktype;
		this.bookdetailtype = bookdetailtype;
		this.booknumber = booknumber;
		this.bookclicked = bookclicked;
		this.bookrecommend = bookrecommend;
		this.booktotalnumber = booktotalnumber;
		this.bookpath = bookpath;
	}

	public Book() {
	}
}
