package board.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

// form에서 전송되는 Board이며
// 해당 객체는 화면을 표현할 때도 사용이 된다

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardForm{

String title;
String content;
String attachment;
String author;
}
