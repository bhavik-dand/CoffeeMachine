package com.machine.coffee.providers;

import com.machine.coffee.exceptions.IngredientInsufficientException;
import com.machine.coffee.exceptions.IngredientNotFoundException;
import com.machine.coffee.models.Ingredient;

import java.util.EnumMap;
import java.util.Map;

/***
 * Concrete implementation of IngredientsLockProvider.
 * Uses a map to keep track of ingredients of the machine.
 */
public class MapBasedIngredientsLockProvider implements IngredientsLockProvider{

    private final EnumMap<Ingredient, Integer> resourcesMap;

    public MapBasedIngredientsLockProvider(final Map<String, Integer> map) {
        this.resourcesMap = new EnumMap<>(Ingredient.class);
        for(Map.Entry<String, Integer> internalEntry: map.entrySet()){
            Ingredient ingredient = Ingredient.get(internalEntry.getKey());
            resourcesMap.put(ingredient, internalEntry.getValue());
        }
    }

    @Override
    public synchronized void lockIngredients(final Map<Ingredient, Integer> ingredients)
            throws IngredientInsufficientException, IngredientNotFoundException {
        for(Map.Entry<Ingredient, Integer> entry: ingredients.entrySet()){
            if(!resourcesMap.containsKey(entry.getKey())){
                throw new IngredientNotFoundException(entry.getKey().getName() +" is not present!");
            }
            if(!isIngredientAvailable(entry.getKey(), entry.getValue())){
                throw new IngredientInsufficientException(entry.getKey().getName() + " has been exhausted");
            }
        }
        for(Map.Entry<Ingredient, Integer> entry: ingredients.entrySet()){
            resourcesMap.put(entry.getKey(), resourcesMap.get(entry.getKey()) - entry.getValue());
        }
    }

    @Override
    public boolean isIngredientAvailable(final Ingredient ingredient, int requiredQuantity) {
        return resourcesMap.get(ingredient) >= requiredQuantity;
    }
}
