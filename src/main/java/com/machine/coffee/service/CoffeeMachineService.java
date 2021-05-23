package com.machine.coffee.service;

import com.machine.coffee.providers.IngredientsLockProvider;
import com.machine.coffee.providers.MapBasedIngredientsLockProvider;
import com.machine.coffee.templates.RequestPayload;
import com.machine.coffee.templates.ResponseTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/***
 * This class is responsible for instantiating the coffee machine and brewing drinks.
 */

public class CoffeeMachineService {

    private final RequestPayload requestPayload;
    private final IngredientsLockProvider lockProvider;
    private final ExecutorService outlets;

    public CoffeeMachineService(RequestPayload requestPayload) {
        this.requestPayload = requestPayload;
        this.lockProvider = new MapBasedIngredientsLockProvider(requestPayload.getTotalItemsQuantity());
        this.outlets = Executors.newFixedThreadPool(requestPayload.getOutletsCount());
    }

    public Map<String, ResponseTemplate> brewDrinks() throws ExecutionException, InterruptedException {
        Map<String, ResponseTemplate> responses = new HashMap<>();
        for(Map.Entry<String, Map<String, Integer>> entry: requestPayload.getBeverages().entrySet()){
            Future<ResponseTemplate> response = outlets.submit(new DrinkBrewer(lockProvider, entry.getKey(), entry.getValue()));
            responses.put(entry.getKey(), response.get());
        }
        outlets.shutdown();
        return responses;
    }
}
