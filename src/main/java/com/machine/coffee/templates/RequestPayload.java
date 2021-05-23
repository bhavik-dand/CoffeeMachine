package com.machine.coffee.templates;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Map;

/***
 * This class is used to define the initial status of the machine.
 */
@Getter
@AllArgsConstructor
public class RequestPayload {
    private final int outletsCount;
    private final Map<String, Integer> totalItemsQuantity;
    private final Map<String, Map<String, Integer>> beverages;
}
