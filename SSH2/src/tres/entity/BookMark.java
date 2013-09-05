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
@Table(name = "tb_bookmark")
public class BookMark {
	private int id;
	private String bookname;
	private String bookchapter;
	private int chapternumber;
	private Reader reader;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Column
	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	@Column
	public String getBookchapter() {
		return bookchapter;
	}

	public void setBookchapter(String bookchapter) {
		this.bookchapter = bookchapter;
	}

	@Column
	public int getChapternumber() {
		return chapternumber;
	}

	public void setChapternumber(int chapternumber) {
		this.chapternumber = chapternumber;
	}

	@ManyToOne(cascade = {}, fetch = FetchType.EAGER)
	@JoinColumn(name = "reader_id")
	public Reader getReader() {
		return reader;
	}

	public void setReader(Reader reader) {
		this.reader = reader;
	}

}
