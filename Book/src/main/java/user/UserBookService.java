package user;

import user.UserBookDAO;

public class UserBookService {
    private UserBookDAO dao;

    public UserBookService() {
        dao = new UserBookDAO();
    }
    // getBookList(), getBookDetail() 등 추가
}
