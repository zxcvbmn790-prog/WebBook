package WebBookStore.admin;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AdminVO {
    private int adminId;
    private String username;
    private String password;
    private String email;
}