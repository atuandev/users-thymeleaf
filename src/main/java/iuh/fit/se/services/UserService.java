package iuh.fit.se.services;

import java.time.LocalDate;
import java.util.List;

import iuh.fit.se.entities.User;

public interface UserService {
	void save(User user);

	User findByEmail(String email);

	User findById(Integer id);

	User myInfo();

	List<User> findAll();

	List<User> findByKeyword(String keyword);

	List<User> findByDobBetween(LocalDate startDate, LocalDate endDate);

	void deleteById(Integer id);
}
