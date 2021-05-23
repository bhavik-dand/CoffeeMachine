package com.machine.coffee.providers;

import com.machine.coffee.models.Ingredient;

import java.util.Map;

/***
 * Interface which provides locking mechanism for Ingredients present in
 * the machine.
 */
public interface IngredientsLockProvider {

    void lockIngredients(final Map<Ingredient, Integer> ingredients);
    boolean isIngredientAvailable(final Ingredient ingredient, int requiredQuantity);
}
