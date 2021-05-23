package com.machine.coffee.exceptions;

/***
 * A subclass of RuntimeException thrown when ingredient required is
 * not present with the machine.
 */
public class IngredientNotFoundException extends RuntimeException{

    public IngredientNotFoundException(String message) {
        super(message);
    }
}
