package iuh.fit.se.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import iuh.fit.se.entities.User;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByEmail(String email);

    List<User> findByFirstNameOrLastNameOrEmailContainingIgnoreCase(String lastName, String firstName, String email);

    List<User> findByDobBetween(LocalDate startDate, LocalDate endDate);

}
