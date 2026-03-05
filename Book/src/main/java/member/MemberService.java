package member;

import member.MemberDAO;

public class MemberService {
    private MemberDAO dao;

    public MemberService() {
        dao = new MemberDAO();
    }
    
}