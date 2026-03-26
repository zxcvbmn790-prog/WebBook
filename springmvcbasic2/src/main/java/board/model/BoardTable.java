package board.model;

import java.sql.Date;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class BoardTable{
	private int id;
	private String title;
	private Date createdate;
	private String content;
	private String attachment;
	private int viewcnt;
	private String type;
	private int userid;
}
