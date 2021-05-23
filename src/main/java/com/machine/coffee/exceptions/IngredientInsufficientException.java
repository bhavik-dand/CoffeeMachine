package com.machine.coffee.exceptions;

/***
 * A subclass of RuntimeException thrown when ingredient required is
 * more than the current quantity present in the machine.
 */
public class IngredientInsufficientException extends RuntimeException{

    public IngredientInsufficientException(String msg) {
        super(msg);
    }
}
