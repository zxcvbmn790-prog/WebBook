package member.repository;

import java.util.List;

import member.model.User;

public interface UserDAO {
public int save(User user);
public List<User> findAll();
public User findById(int id);
public User findByUsername(String username);
public int update(User user);
public int delete(String username);
}