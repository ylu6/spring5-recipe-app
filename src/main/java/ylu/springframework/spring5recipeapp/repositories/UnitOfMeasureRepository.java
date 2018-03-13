package ylu.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import ylu.springframework.spring5recipeapp.domain.UnitOfMeasure;

import java.util.Optional;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {

    // derived query method, query UnitOfMeasure by description string
    Optional<UnitOfMeasure> findByDescription(String description);
}
