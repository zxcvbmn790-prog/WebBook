package member;

public class memberVO {
	
	private String id;
	private String pw;
	private String hp;
	private String email;
	private String nickname;
	
	
	public memberVO(String id, String pw, String hp, String email, String nickname) {
		this.id = id;
		this.pw = pw;
		this.hp = hp;
		this.email = email;
		this.nickname = nickname;
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPw() {
		return pw;
	}
	public void setPw(String pw) {
		this.pw = pw;
	}
	public String getHp() {
		return hp;
	}
	public void setHp(String hp) {
		this.hp = hp;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	@Override
	public String toString() {
		return "memberVO [id=" + id + ", pw=" + pw + ", hp=" + hp + ", email=" + email + ", nickname=" + nickname + "]";
	}
	
}
