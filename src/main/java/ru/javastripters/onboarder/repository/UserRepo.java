package ru.javastripters.onboarder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.User;

import java.util.List;

public interface UserRepo extends CrudRepository<User, Integer> {
    List<User> findUsersByIdIn(Iterable<Integer> ids);
    User findUsersById(int id);
}
