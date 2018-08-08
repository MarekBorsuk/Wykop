package pl.java.wykop.service;

import pl.java.wykop.dao.DAOFactory;
import pl.java.wykop.dao.UserDAO;
import pl.java.wykop.model.User;

public class UserService {
	public void addUser(String username, String email, String password) {
		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setEmail(email);
		user.setActive(true);
		DAOFactory factory = DAOFactory.getDAOFactory();
		UserDAO userDAO = factory.getUserDAO();
		userDAO.create(user);
	}
}
