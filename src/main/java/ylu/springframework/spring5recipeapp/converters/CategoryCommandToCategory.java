package ylu.springframework.spring5recipeapp.converters;

import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ylu.springframework.spring5recipeapp.commands.CategoryCommand;
import ylu.springframework.spring5recipeapp.domain.Category;

@Component
public class CategoryCommandToCategory implements Converter<CategoryCommand, Category> {

    @Nullable
    // converter interface in spring 5 is thread-safe, is Sychronized still required here?
    @Synchronized
    @Override
    public Category convert(CategoryCommand categoryCommand) {
        if(categoryCommand==null) {
            return null;
        }

        final Category category  = new Category();
        category.setId(categoryCommand.getId());
        category.setDescription(categoryCommand.getDescription());

        return category;
    }
}
