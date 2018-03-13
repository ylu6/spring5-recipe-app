package ylu.springframework.spring5recipeapp.repositories;

import org.springframework.data.repository.CrudRepository;
import ylu.springframework.spring5recipeapp.domain.Category;

import java.util.Optional;

public interface CategoryRepository extends CrudRepository<Category, Long> {

    // derived query method, the method name will be translated into sql query
    Optional<Category> findByDescription(String description);
}
