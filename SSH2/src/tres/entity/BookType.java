package tres.entity;

import java.util.HashSet;
import java.util.Set;

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
@Table(name = "tb_type")
public class BookType {
	private int id;
	private String booktype;
	private Set<BookDetailType> bookDetailTypes = new HashSet<BookDetailType>();  

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
	public String getBooktype() {
		return booktype;
	}

	public void setBooktype(String booktype) {
		this.booktype = booktype;
	}

	@OneToMany(mappedBy = "bookType", cascade = { CascadeType.ALL }, fetch = FetchType.EAGER)
	public Set<BookDetailType> getBookDetailTypes() {
		return bookDetailTypes;
	}

	public void setBookDetailTypes(Set<BookDetailType> bookDetailTypes) {
		this.bookDetailTypes = bookDetailTypes;
	}

	public BookType(String booktype) {
		this.booktype = booktype;
	}

	public BookType() {
	}

}
