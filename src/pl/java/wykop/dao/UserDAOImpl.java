package pl.java.wykop.dao;

import java.util.List;

import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import pl.java.wykop.model.User;
import pl.java.wykop.util.ConnectionProvider;

public class UserDAOImpl implements UserDAO {

	private static final String CREATE_USER =
			"insert into user(username, email, password, is_active) values(:username, :email, :password, :active);";
	private NamedParameterJdbcTemplate template;
	
	public UserDAOImpl() {
		template = new NamedParameterJdbcTemplate(ConnectionProvider.getDataSource());
	}
	
	@Override
	public User create(User user) {
		User resultUser = new User(user);
		//KeyHolder automatyczne przechwyca klucz wygenerowany przete baze
		KeyHolder holder = new GeneratedKeyHolder();
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		int update = template.update(CREATE_USER, parameterSource, holder);
		if (update > 0) {
			resultUser.setId((Long)holder.getKey());
			setPrivigiles(resultUser);
		}
		return resultUser;
	}
	

	private void setPrivigiles(User user) {
		final String userRoleQuery = "insert into user_role(username) values(:username);";
		SqlParameterSource parameterSource = new BeanPropertySqlParameterSource(user);
		template.update(userRoleQuery, parameterSource);
	}

	@Override
	public User read(Long primaryKey) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean update(User updateObject) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean delete(Long key) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsername(String username) {
		// TODO Auto-generated method stub
		return null;
	}

}
