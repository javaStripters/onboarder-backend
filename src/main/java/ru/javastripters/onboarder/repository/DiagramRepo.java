package ru.javastripters.onboarder.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import ru.javastripters.onboarder.model.Diagram;

public interface DiagramRepo extends CrudRepository<Diagram, Integer> {
}
