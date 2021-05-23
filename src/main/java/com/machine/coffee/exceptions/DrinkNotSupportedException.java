package com.machine.coffee.exceptions;

/***
 * A subclass of RuntimeException thrown when the requested drink
 * is not supported.
 */
public class DrinkNotSupportedException extends Throwable {

    public DrinkNotSupportedException(String msg) {
        super(msg);
    }
}
