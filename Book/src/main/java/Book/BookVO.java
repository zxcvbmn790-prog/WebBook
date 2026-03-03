package Book;

public class BookVO {
	int ibns;  // 책 아이디
	String bookname;  // 책 이름
	String author; // 저자
	String publisher; // 출판사
	String image; // 이미지

	public BookVO() {
		// TODO Auto-generated constructor stub
	}

	public BookVO(int ibns, String bookname, String author, String publisher, String image) {
		this.ibns = ibns;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publisher;
		this.image = image;
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
		return "AdminVO [ibns=" + ibns + ", bookname=" + bookname + ", author=" + author + ", publisher=" + publisher
				+ ", image=" + image + "]";
	}
	
	
	
}
