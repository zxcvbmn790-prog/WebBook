package WebBookStore.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BookVO {
 private int isbn;
 private String bookname;
 private String author;
 private String publisher;
 private String image;
 private String price;
 private String category;
}