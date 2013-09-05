package tres.entity;

public class Pagination {
	private int totalNum;// ������
	private int totalPage;// ��ҳ��
	private int startPage;// ��ʼҳ
	private int endPage;// ����ҳ
	private int currentPage;// ��ǰҳ
	private int showNum;// ��ʾҳ��

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getShowNum() {
		return showNum;
	}

	public void setShowNum(int showNum) {
		this.showNum = showNum;
	}

	public Pagination(int totalNum, int currentPage, int showNum) {
		this.totalNum = totalNum;
		this.currentPage = currentPage;
		this.showNum = showNum;
		initData(totalNum, currentPage, showNum);
	}

	public Pagination() {
	}

	private void initData(int totalNum, int currentPage, int showNum) {
		if (totalNum > 0) {
			int half = showNum / 2;

			totalPage = totalNum / showNum + 1;

			if (currentPage < 0) {
				currentPage = 0;
			} else if (currentPage > totalNum) {
				currentPage = totalNum;
			}
			startPage = currentPage - half + 2;
			if (startPage < 1) {
				startPage = 1;
			}
			endPage = currentPage + half + 1;
			if (endPage > totalPage || totalPage <= showNum) {
				endPage = totalPage;
			}
		}
	}

	@Override
	public String toString() {
		return "Pagination [totalNum=" + totalNum + ", startPage=" + startPage
				+ ", endPage=" + endPage + ", currentPage=" + currentPage
				+ ", showNum=" + showNum + "]";
	}

}
