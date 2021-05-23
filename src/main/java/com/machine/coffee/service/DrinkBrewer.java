package com.machine.coffee.service;

import com.machine.coffee.exceptions.DrinkNotSupportedException;
import com.machine.coffee.exceptions.IngredientInsufficientException;
import com.machine.coffee.exceptions.IngredientNotFoundException;
import com.machine.coffee.factories.DrinkFactory;
import com.machine.coffee.models.Drink;
import com.machine.coffee.models.Ingredient;
import com.machine.coffee.providers.IngredientsLockProvider;
import com.machine.coffee.templates.ResponseTemplate;
import lombok.AllArgsConstructor;

import java.util.EnumMap;
import java.util.Map;
import java.util.concurrent.Callable;

/***
 * This class is responsible for brewing the drink.
 */
@AllArgsConstructor
public class DrinkBrewer implements Callable<ResponseTemplate> {

    private final IngredientsLockProvider lockProvider;
    private final String drinkName;
    private final Map<String, Integer> requiredIngredients;


    @Override
    public ResponseTemplate call() {
        Drink drink;
        EnumMap<Ingredient, Integer> requiredQuantityMap = new EnumMap<>(Ingredient.class);
        for(Map.Entry<String, Integer> internalEntry: requiredIngredients.entrySet()){
            try {
                Ingredient ingredient = Ingredient.get(internalEntry.getKey());
                requiredQuantityMap.put(ingredient, internalEntry.getValue());
            } catch (IngredientNotFoundException e){
                return new ResponseTemplate(false, e.getMessage());
            }
        }
        try{
            drink = DrinkFactory.createDrink(drinkName, requiredQuantityMap);
        } catch (DrinkNotSupportedException exception){
            return new ResponseTemplate(false, drinkName +" could not be prepared because " + exception.getMessage());
        }

        try{
            lockProvider.lockIngredients(drink.getRequiredIngredients());
        } catch (IngredientInsufficientException | IngredientNotFoundException e){
            return new ResponseTemplate(false, e.getMessage());
        }

        return new ResponseTemplate(true, drinkName +" is prepared");
    }
}
