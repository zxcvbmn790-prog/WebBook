package member;

public class memberService {

	memberDAO dao;
	
	public memberService () {
		dao=new memberDAOH2();
	}
	
	public boolean isLogin(String username, String password) {
		int login = dao.login(username, password); 
		System.out.println("login test //////// " + username + "     "+password);
		System.out.println("boolean :" + login );
		if (login == 1) {
			return true;
		}else {
			return false;
			
		}
	}

}