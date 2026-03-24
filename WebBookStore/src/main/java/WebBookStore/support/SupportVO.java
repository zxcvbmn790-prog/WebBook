package WebBookStore.support;

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
public class SupportVO {
    private int supportId;
    private int memberId;
    private String title;
    private String content;
    private Date createdDate;
    private String status; // 예: "답변대기", "완료"
}