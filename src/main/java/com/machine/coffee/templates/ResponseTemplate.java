package com.machine.coffee.templates;

import lombok.AllArgsConstructor;
import lombok.ToString;


/***
 * This class's instance is used as response by DrinkBrewer
 */
@AllArgsConstructor
@ToString
public class ResponseTemplate {
    private final boolean status;
    private final String message;

    public boolean getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
