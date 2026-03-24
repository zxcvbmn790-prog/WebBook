package WebBookStore.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class OrderVO {
    private int orderId;
    private int memberId;
    private int bookId;
    private int quantity;
    private Date orderDate;
    private String status;
}