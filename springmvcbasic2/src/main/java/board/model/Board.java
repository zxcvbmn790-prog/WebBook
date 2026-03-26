package board.model;

import java.sql.Date;
import java.sql.Timestamp;

import org.h2.engine.User;

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
public class Board{
	int id;
	String title;
	Date createdate;
	String content;
	String attachment;
	int viewcnt;
	String type;
	User user;
	public void setCreatedate(Timestamp timestamp) {
		// TODO Auto-generated method stub
		
	}
	public void setUser(member.model.User u) {
		// TODO Auto-generated method stub
		
	}
}
