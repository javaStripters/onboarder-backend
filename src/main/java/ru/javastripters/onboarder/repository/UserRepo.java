package ru.javastripters.onboarder.repository;

import org.springframework.data.repository.CrudRepository;
import ru.javastripters.onboarder.model.User;

public interface UserRepo extends CrudRepository<User, Integer> {
}
