package com.machine.coffee.models;

import lombok.Getter;

import java.util.EnumMap;

/***
 * Model class for drink.
 * This class takes drink name and required ingredients as parameters.
 */
@Getter
public class Drink {
    private final String name;
    private final EnumMap<Ingredient, Integer> requiredIngredients;

    public Drink(final String name, final EnumMap<Ingredient, Integer> requiredIngredients) {
        this.name = name;
        this.requiredIngredients = requiredIngredients;
    }
}
