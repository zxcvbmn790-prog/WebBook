package Book;

public class BookVO {
	
	private int isbn;         // 도서 번호
    private String bookname;  // 도서 제목
    private String author;    // 저자
    private String publisher; // 출판사
    private String image;     // 도서 이미지 경로
    private String price;	  // 가격

    // 기본 생성자
    public BookVO() {}
    
    public BookVO(int isbn, String bookname, String author, String publisher, String image) {
		this.isbn = isbn;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publisher;
		this.image = image;
	}

	public BookVO(int isbn, String bookname, String author, String publisher, String image, String price) {
		super();
		this.isbn = isbn;
		this.bookname = bookname;
		this.author = author;
		this.publisher = publisher;
		this.image = image;
		this.price = price;
	}

	public int getIsbn() {
		return isbn;
	}

	public void setIsbn(int isbn) {
		this.isbn = isbn;
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

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "BookVO [isbn=" + isbn + ", bookname=" + bookname + ", author=" + author + ", publisher=" + publisher
				+ ", image=" + image + ", price=" + price + "]";
	}
}