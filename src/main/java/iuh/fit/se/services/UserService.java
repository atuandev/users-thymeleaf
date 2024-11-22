package iuh.fit.se.services;

import java.util.List;

import iuh.fit.se.entities.User;

public interface UserService {
	void save(User user);

	User findByEmail(String email);

	User findById(Integer id);

	User myInfo();

	List<User> findAll();

	List<User> findByKeyword(String keyword);

	void deleteById(Integer id);
}
