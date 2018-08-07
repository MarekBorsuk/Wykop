package pl.java.wykop.dao;

import java.util.List;

import pl.java.wykop.model.User;

public interface UserDAO extends GenericDAO<User, Long> {
	List<User> getAll();
	User getUserByUsername(String username);
}
