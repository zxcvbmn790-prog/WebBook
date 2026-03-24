package WebBookStore.member;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {
    private int id;
    private String username;
    private String password;
    private String email;
    private String phone;
}