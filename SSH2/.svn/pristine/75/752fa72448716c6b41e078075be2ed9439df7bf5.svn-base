package tres.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_detailtype")
public class BookDetailType {
	private int id;
	private String bookdetailtype;
	private BookType bookType;

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
	public String getBookdetailtype() {
		return bookdetailtype;
	}

	public void setBookdetailtype(String bookdetailtype) {
		this.bookdetailtype = bookdetailtype;
	}
	
	@ManyToOne(cascade={CascadeType.ALL})  
	@JoinColumn(name = "booktype_id")
	public BookType getBookType() {
		return bookType;
	}

	public void setBookType(BookType bookType) {
		this.bookType = bookType;
	}

	public BookDetailType(String bookdetailtype, BookType bookType) {
		this.bookdetailtype = bookdetailtype;
		this.bookType = bookType;
	}

	public BookDetailType() {
	}
}
