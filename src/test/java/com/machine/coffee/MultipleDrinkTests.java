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

public class MultipleDrinkTests {

    /***
     * This test tries to brew 2 drinks. Both succeed.
     */
    @Test
    public void test1() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 1000);
        ingredientsQuantity.put(HOT_MILK, 1000);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);

        String drink1 = "hot_tea";
        beverages.put(drink1, hotTea);

        Map<String, Integer> hotCoffee = new HashMap<>();
        hotCoffee.put(HOT_WATER, 100);
        hotCoffee.put(GINGER_SYRUP, 30);
        hotCoffee.put(HOT_MILK, 400);
        hotCoffee.put(TEA_LEAVES_SYRUP, 30);
        hotCoffee.put(SUGAR_SYRUP, 30);

        String drink2 = "hot_coffee";
        beverages.put(drink2, hotCoffee);


        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertTrue(resp.get(drink1).getStatus());
        Assert.assertTrue(resp.get(drink2).getStatus());
    }

    /***
     * This test tries to brew 2 drinks (1 supported and 1 not supported). 1 succeeds and 1 fails.
     */
    @Test
    public void test2() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 1000);
        ingredientsQuantity.put(HOT_MILK, 1000);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);

        String drink1 = "hot_tea";
        beverages.put(drink1, hotTea);

        Map<String, Integer> hotChocolate = new HashMap<>();
        hotChocolate.put(HOT_WATER, 100);
        hotChocolate.put(GINGER_SYRUP, 30);
        hotChocolate.put(HOT_MILK, 400);
        hotChocolate.put(TEA_LEAVES_SYRUP, 30);
        hotChocolate.put(SUGAR_SYRUP, 30);

        String drink2 = "hot_chocolate";
        beverages.put(drink2, hotChocolate);


        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertTrue(resp.get(drink1).getStatus());
        Assert.assertFalse(resp.get(drink2).getStatus());
    }

    /***
     * This test tries to brew 2 drinks. 1 of the drink has an unsupported ingredient. 1 succeeds and 1 fails.
     */
    @Test
    public void test3() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 1000);
        ingredientsQuantity.put(HOT_MILK, 1000);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put("neem_leaves", 30);

        String drink1 = "hot_tea";
        beverages.put(drink1, hotTea);

        Map<String, Integer> hotCoffee = new HashMap<>();
        hotCoffee.put(HOT_WATER, 100);
        hotCoffee.put(GINGER_SYRUP, 30);
        hotCoffee.put(HOT_MILK, 400);
        hotCoffee.put(TEA_LEAVES_SYRUP, 30);
        hotCoffee.put(SUGAR_SYRUP, 30);

        String drink2 = "hot_coffee";
        beverages.put(drink2, hotCoffee);


        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertFalse(resp.get(drink1).getStatus());
        Assert.assertTrue(resp.get(drink2).getStatus());
    }

    /***
     * This test tries to brew 3 drinks. All succeed.
     */
    @Test
    public void test4() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 1000);
        ingredientsQuantity.put(HOT_MILK, 1000);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();

        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);

        String drink1 = "hot_tea";
        beverages.put(drink1, hotTea);

        Map<String, Integer> hotCoffee = new HashMap<>();
        hotCoffee.put(HOT_WATER, 100);
        hotCoffee.put(GINGER_SYRUP, 30);
        hotCoffee.put(HOT_MILK, 400);
        hotCoffee.put(TEA_LEAVES_SYRUP, 30);
        hotCoffee.put(SUGAR_SYRUP, 30);

        String drink2 = "hot_coffee";
        beverages.put(drink2, hotCoffee);

        Map<String, Integer> blackTea = new HashMap<>();
        blackTea.put(HOT_WATER, 300);
        blackTea.put(GINGER_SYRUP, 30);
        blackTea.put(TEA_LEAVES_SYRUP, 30);
        blackTea.put(SUGAR_SYRUP, 50);

        String drink3 = "black_tea";
        beverages.put(drink3, blackTea);


        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        Assert.assertTrue(resp.get(drink1).getStatus());
        Assert.assertTrue(resp.get(drink2).getStatus());
        Assert.assertTrue(resp.get(drink3).getStatus());
    }

    /***
     * This test tries to brew 4 drinks. 1 drink fails due to insufficient ingredients. 3 succeed.
     */
    @Test
    public void test5() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 1000);
        ingredientsQuantity.put(HOT_MILK, 1000);
        ingredientsQuantity.put(GINGER_SYRUP, 100);
        ingredientsQuantity.put(SUGAR_SYRUP, 100);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 100);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);

        String drink1 = "hot_tea";
        beverages.put(drink1, hotTea);

        Map<String, Integer> hotCoffee = new HashMap<>();
        hotCoffee.put(HOT_WATER, 100);
        hotCoffee.put(GINGER_SYRUP, 30);
        hotCoffee.put(HOT_MILK, 400);
        hotCoffee.put(TEA_LEAVES_SYRUP, 30);
        hotCoffee.put(SUGAR_SYRUP, 30);

        String drink2 = "hot_coffee";
        beverages.put(drink2, hotCoffee);

        Map<String, Integer> blackTea = new HashMap<>();
        blackTea.put(HOT_WATER, 300);
        blackTea.put(GINGER_SYRUP, 30);
        blackTea.put(TEA_LEAVES_SYRUP, 30);
        blackTea.put(SUGAR_SYRUP, 50);

        String drink3 = "black_tea";
        beverages.put(drink3, blackTea);

        Map<String, Integer> greenTea = new HashMap<>();
        greenTea.put(HOT_WATER, 500);
        greenTea.put(GINGER_SYRUP, 30);
        greenTea.put(TEA_LEAVES_SYRUP, 10);
        greenTea.put(SUGAR_SYRUP, 10);

        String drink4 = "green_tea";
        beverages.put(drink4, greenTea);


        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        int successCount = 0;
        for(Map.Entry<String, ResponseTemplate> entry: resp.entrySet()){
            if(entry.getValue().getStatus()) successCount++;
        }
        Assert.assertEquals(3, successCount);
    }

    /***
     * This test tries to brew 4 drinks. All fail due to insufficient ingredients.
     */
    @Test
    public void test_4drink_fail_insufficient_ingredient() throws ExecutionException, InterruptedException {
        int countOutlets = 3;
        HashMap<String, Integer> ingredientsQuantity = new HashMap<>();
        ingredientsQuantity.put(HOT_WATER, 10);
        ingredientsQuantity.put(HOT_MILK, 10);
        ingredientsQuantity.put(GINGER_SYRUP, 10);
        ingredientsQuantity.put(SUGAR_SYRUP, 10);
        ingredientsQuantity.put(TEA_LEAVES_SYRUP, 10);

        HashMap<String, Map<String, Integer>> beverages = new HashMap<>();
        Map<String, Integer> hotTea = new HashMap<>();
        hotTea.put(HOT_WATER, 200);
        hotTea.put(GINGER_SYRUP, 10);
        hotTea.put(HOT_MILK, 200);
        hotTea.put(TEA_LEAVES_SYRUP, 30);

        String drink1 = "hot_tea";
        beverages.put(drink1, hotTea);

        Map<String, Integer> hotCoffee = new HashMap<>();
        hotCoffee.put(HOT_WATER, 100);
        hotCoffee.put(GINGER_SYRUP, 30);
        hotCoffee.put(HOT_MILK, 400);
        hotCoffee.put(TEA_LEAVES_SYRUP, 30);
        hotCoffee.put(SUGAR_SYRUP, 30);

        String drink2 = "hot_coffee";
        beverages.put(drink2, hotCoffee);

        Map<String, Integer> blackTea = new HashMap<>();
        blackTea.put(HOT_WATER, 300);
        blackTea.put(GINGER_SYRUP, 30);
        blackTea.put(TEA_LEAVES_SYRUP, 30);
        blackTea.put(SUGAR_SYRUP, 50);

        String drink3 = "black_tea";
        beverages.put(drink3, blackTea);

        Map<String, Integer> greenTea = new HashMap<>();
        greenTea.put(HOT_WATER, 500);
        greenTea.put(GINGER_SYRUP, 30);
        greenTea.put(TEA_LEAVES_SYRUP, 10);
        greenTea.put(SUGAR_SYRUP, 10);

        String drink4 = "green_tea";
        beverages.put(drink4, greenTea);


        RequestPayload requestPayload = new RequestPayload(countOutlets, ingredientsQuantity, beverages);
        CoffeeMachineService cms = new CoffeeMachineService(requestPayload);
        Map<String, ResponseTemplate> resp = cms.brewDrinks();
        int successCount = 0;
        for(Map.Entry<String, ResponseTemplate> entry: resp.entrySet()){
            if(entry.getValue().getStatus()) successCount++;
        }
        Assert.assertEquals(0, successCount);
    }
}
