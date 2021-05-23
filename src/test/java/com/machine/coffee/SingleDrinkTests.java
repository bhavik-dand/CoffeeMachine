package com.machine.coffee;

import com.machine.coffee.service.CoffeeMachineService;
import com.machine.coffee.templates.RequestPayload;
import com.machine.coffee.templates.ResponseTemplate;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import static com.machine.coffee.constants.Constants.*;

public class SingleDrinkTests {

    /***
     * This test brew's 1 drink successfully.
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 500);
        ingredientsQuantity.put(HOT_MILK, 500);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);
        String hot_tea = "hot_tea";
        beverages.put(hot_tea, hotTea);

        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertTrue(resp.get(hot_tea).getStatus());
    }

    /***
     * This test tries to brew a drink which is not supported. Fails.
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 500);
        ingredientsQuantity.put(HOT_MILK, 500);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);
        String drinkName = "hot_chocolate";
        beverages.put(drinkName, hotTea);

        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertFalse(resp.get(drinkName).getStatus());
        Assert.assertNotNull( resp.get(drinkName).getMessage());
    }

    /***
     * This test tries to brew a drink but has insufficient ingredients. Fails.
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 100);
        ingredientsQuantity.put(HOT_MILK, 500);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);
        String drinkName = "hot_chocolate";
        beverages.put(drinkName, hotTea);

        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertFalse(resp.get(drinkName).getStatus());
        Assert.assertNotNull( resp.get(drinkName).getMessage());
    }

}
