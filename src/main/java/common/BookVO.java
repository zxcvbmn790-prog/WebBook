package common;

public class BookVO {
	int ibns;  // å ���̵�
	String id;
	String password;
	String bookname;  // å �̸�
	String author; // ����
	String publisher; // ���ǻ�
	String image; // �̹���

	public BookVO() {
		// TODO Auto-generated constructor stub
	}

	public BookVO(int ibns, String id, String password, String bookname, String author, String publisher, String image) {
		this.ibns = ibns;
		this.id = id;
		this.password = password;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publisher;
		this.image = image;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public int getIbns() {
		return ibns;
	}

	public void setIbns(int ibns) {
		this.ibns = ibns;
	}

	public String getBookname() {
		return bookname;
	}

	public void setBookname(String bookname) {
		this.bookname = bookname;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "BookVO [ibns=" + ibns + ", id=" + id + ", password=" + password + ", bookname=" + bookname + ", author="
				+ author + ", publisher=" + publisher + ", image=" + image + "]";
	}
}
