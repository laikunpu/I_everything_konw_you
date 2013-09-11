package tres.entity;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "tb_chapter")
public class Chapter {
	private int id;
	private String novelname;
	private int novelnumber;
	private int chapternumber;
	private String chaptertitle;
	private byte[] chaptercontent;

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
	public String getNovelname() {
		return novelname;
	}

	public void setNovelname(String novelname) {
		this.novelname = novelname;
	}

	@Column
	public int getNovelnumber() {
		return novelnumber;
	}

	public void setNovelnumber(int novelnumber) {
		this.novelnumber = novelnumber;
	}

	@Column
	public int getChapternumber() {
		return chapternumber;
	}

	public void setChapternumber(int chapternumber) {
		this.chapternumber = chapternumber;
	}

	@Column
	public String getChaptertitle() {
		return chaptertitle;
	}

	public void setChaptertitle(String chaptertitle) {
		this.chaptertitle = chaptertitle;
	}

	@Lob
	@Basic(fetch = FetchType.LAZY)
	public byte[] getChaptercontent() {
		return chaptercontent;
	}

	public void setChaptercontent(byte[] chaptercontent) {
		this.chaptercontent = chaptercontent;
	}

	public Chapter(String novelname, int novelnumber, int chapternumber,
			String chaptertitle, byte[] chaptercontent) {
		this.novelname = novelname;
		this.novelnumber = novelnumber;
		this.chapternumber = chapternumber;
		this.chaptertitle = chaptertitle;
		this.chaptercontent = chaptercontent;
	}

	public Chapter() {
	}
}