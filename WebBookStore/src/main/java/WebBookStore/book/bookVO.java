package WebBookStore.book;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

// Lombok 사용
@Data
@NoArgsConstructor
@AllArgsConstructor
public class bookVO {
    private int id;             // 책 ID
    private String title;       // 책 제목
    private String author;      // 저자
    private String publisher;   // 출판사
    private double price;       // 가격
    private int stock;          // 재고
}