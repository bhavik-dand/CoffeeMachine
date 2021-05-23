package com.machine.coffee.factories;

import com.machine.coffee.exceptions.DrinkNotSupportedException;
import com.machine.coffee.models.Drink;
import com.machine.coffee.models.Ingredient;

import java.util.EnumMap;

/***
 * Generates objects of Drink class
 */
public class DrinkFactory {

    private DrinkFactory(){}

    public static Drink createDrink(final String drinkName, final EnumMap<Ingredient, Integer> requiredIngredients) throws DrinkNotSupportedException {

        switch (drinkName){
            case "hot_tea":
            case "hot_coffee":
            case "green_tea":
            case "black_tea":
                return new Drink(drinkName, requiredIngredients);
            default:
                throw new DrinkNotSupportedException(drinkName + " is not supported");
        }

    }
}
