package com.machine.coffee.models;

import com.machine.coffee.constants.Constants;
import com.machine.coffee.exceptions.IngredientNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;


/***
 * Enum for Ingredients
 */
@AllArgsConstructor
public enum Ingredient {
    HOT_WATER(Constants.HOT_WATER, "Hot water"),
    HOT_MILK(Constants.HOT_MILK,"Hot milk"),
    GINGER_SYRUP(Constants.GINGER_SYRUP,"Ginger syrup"),
    ELAICHI_SYRUP(Constants.ELAICHI_SYRUP,"Elaichi syrup"),
    SUGAR_SYRUP(Constants.SUGAR_SYRUP,"Sugar syrup"),
    TEA_LEAVES_SYRUP(Constants.TEA_LEAVES_SYRUP,"Tea leaves syrup"),
    GREEN_MIXTURE(Constants.GREEN_MIXTURE,"Green mixture");

    private final String id;
    @Getter
    private final String name;
    private static final Ingredient[] values = values();

    public static Ingredient get(final String id){
        for(Ingredient ingredient: values){
            if(ingredient.id.equalsIgnoreCase(id)){
                return ingredient;
            }
        }
        throw new IngredientNotFoundException("Ingredient doesn't exist!");
    }
}
