package WebBookStore.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderVO {
    private int orderId;
    private int memberId;
    private int bookId;
    private int quantity;
    private Date orderDate;
    private String status;
}