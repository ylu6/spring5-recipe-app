package ylu.springframework.spring5recipeapp.converters;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import ylu.springframework.spring5recipeapp.commands.CategoryCommand;
import ylu.springframework.spring5recipeapp.commands.IngredientCommand;
import ylu.springframework.spring5recipeapp.commands.NotesCommand;
import ylu.springframework.spring5recipeapp.commands.RecipeCommand;
import ylu.springframework.spring5recipeapp.domain.Difficulty;
import ylu.springframework.spring5recipeapp.domain.Recipe;

import java.util.HashSet;
import java.util.Set;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private IngredientToIngredientCommand ingredientConverter;
    private NotesToNotesCommand notesConverter;
    private CategoryToCategoryCommand categoryConverter;

    public RecipeToRecipeCommand(IngredientToIngredientCommand ingredientConverter, NotesToNotesCommand notesConverter, CategoryToCategoryCommand categoryConverter) {
        this.ingredientConverter = ingredientConverter;
        this.notesConverter = notesConverter;
        this.categoryConverter = categoryConverter;
    }

    @Nullable
    @Override
    public RecipeCommand convert(Recipe recipe) {
        if(recipe == null) {
            return null;
        }

        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(recipe.getId());
        recipeCommand.setDescription(recipe.getDescription());
        recipeCommand.setPrepTime(recipe.getPrepTime());
        recipeCommand.setCookTime(recipe.getCookTime());
        recipeCommand.setServings(recipe.getServings());
        recipeCommand.setSource(recipe.getSource());
        recipeCommand.setUrl(recipe.getUrl());
        recipeCommand.setDirections(recipe.getDirections());
        recipeCommand.setDifficulty(recipe.getDifficulty());
        recipeCommand.setNotes(notesConverter.convert(recipe.getNotes()));

        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            recipe.getIngredients().
                    forEach(ingredient -> recipeCommand.getIngredients().add(ingredientConverter.convert(ingredient)));
        }

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            recipe.getCategories().
                    forEach(category -> recipeCommand.getCategories().add(categoryConverter.convert(category)));
        }

        return recipeCommand;
    }
}
