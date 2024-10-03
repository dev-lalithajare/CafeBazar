/* 
 *  MIT License
 *  
 *  Copyright (c) 2024 Lalit Hajare
 *  
 *  Created on Wed Oct 02 2024 by lalit
 *  
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *  
 *  The above copyright notice and this permission notice shall be included in
 *  all copies or substantial portions of the Software.
 *  
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 *  THE SOFTWARE.
 */

package CafeBazar.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import CafeBazar.models.IExtraChargeableBeverageToppings;
import CafeBazar.models.MenuItem;
import CafeBazar.models.Order;
import CafeBazar.models.burger.BurgerToppings;
import CafeBazar.models.burger.Classic;
import CafeBazar.models.burger.Hamburger;
import CafeBazar.models.burger.Indian;
import CafeBazar.models.coffee.Americano;
import CafeBazar.models.coffee.Cappucino;
import CafeBazar.models.coffee.CoffeeToppings;
import CafeBazar.models.coffee.Espresso;
import CafeBazar.models.coffee.Latte;
import CafeBazar.models.tea.Black;
import CafeBazar.models.tea.Green;
import CafeBazar.models.tea.Herbal;
import CafeBazar.repo.IOrderRepo;

public class OrderService {

    private IOrderRepo orderRepository;

    public OrderService(IOrderRepo orderRepository){
        this.orderRepository = orderRepository;
    }

    private List<Order> orders = new ArrayList<>();
    private List<Order> previousOrders = new ArrayList<>();

    public boolean takeOrder(Scanner userInput, UUID customerId) {
        System.out.println("Please order something, we have -");
        System.out.println("1. Coffee");
        System.out.println("2. Tea");
        System.out.println("3. Burger");
        int category = 1;
        if (userInput.hasNextInt()) {
            category = userInput.nextInt();
        }        
        Order order = prepareOrder(category, userInput, customerId);
        orders.add(order);
        return order != null;
    }

    public List<Order> getPreviousOrdersForCustomer(UUID customerId){
        List<Order> customerOrders = new ArrayList<>();
        for (Order order : previousOrders) {
            if (order.getCustomerId().equals(customerId)) {
                customerOrders.add(order);
            }
        }
        return customerOrders;
    }

    private Order prepareOrder(int category, Scanner userInput, UUID customerId) {
        int orderInput = 1;
        int quantityInput = 1;
        switch (category) {
            case 1:
                System.out.println("We have different types of Coffee, please select one");
                System.out.println("1. Americano");
                System.out.println("2. Cappucino");
                System.out.println("3. Latte");
                System.out.println("4. Espresso");
                if (userInput.hasNextInt()) {
                    orderInput = userInput.nextInt();    
                }                
                CoffeeToppings coffeeToppings = getCoffeeToppingsUserInput(userInput);
                System.out.println("Please enter the quantity");
                if (userInput.hasNextInt()) {
                    quantityInput = userInput.nextInt();  
                }                 
            return prepareCoffeeOrder(orderInput, quantityInput, customerId, coffeeToppings);            

            case 2:
                System.out.println("We have different types of Tea, please select one");
                System.out.println("1. Green");
                System.out.println("2. Black");
                System.out.println("3. Herbal");
                if (userInput.hasNextInt()) {
                    orderInput = userInput.nextInt();    
                }   
                System.out.println("Please enter the quantity");
                if (userInput.hasNextInt()) {
                    quantityInput = userInput.nextInt();  
                }   
            return prepareTeaOrder(orderInput, quantityInput, customerId); 

            case 3:
                System.out.println("We have different types of Burger, please select one");
                System.out.println("1. Hamburger");
                System.out.println("2. Classic");
                System.out.println("3. Indian/Wada-Pav");
                if (userInput.hasNextInt()) {
                    orderInput = userInput.nextInt();    
                } 
                BurgerToppings burgerToppings = getBurgerToppingsUserInput(userInput);   
                System.out.println("Please enter the quantity");
                if (userInput.hasNextInt()) {
                    quantityInput = userInput.nextInt();  
                }                 
            return prepareBurgerOrder(orderInput, quantityInput, customerId, burgerToppings);
        
            default:
                System.out.println("Please order something from our Menu");                                      
            return null; 
        }
    }

    CoffeeToppings getCoffeeToppingsUserInput(Scanner userInput){        
        boolean useExtraCream = false;
        boolean useSugarFreeSweetner = false;
        userInput.nextLine();
        System.out.println("Do you need extra Cream? Y/N");
        if (userInput.nextLine().equalsIgnoreCase("y")) {
            useExtraCream = true;
        }
        System.out.println("Do you need sugarless sweetner? Y/N");
        if (userInput.nextLine().equalsIgnoreCase("y")) {
            useSugarFreeSweetner = true;
        }
        return new CoffeeToppings(useExtraCream, useSugarFreeSweetner);
    }

    BurgerToppings getBurgerToppingsUserInput(Scanner userInput){        
        boolean useExtraBeacon = false;
        boolean useAvocado = false;
        boolean useSaraTogaChips = false;
        userInput.nextLine();
        System.out.println("Do you need extra Beacon? Y/N");
        if (userInput.nextLine().equalsIgnoreCase("y")) {
            useExtraBeacon = true;
        }

        System.out.println("Do you need Avocado? Y/N");
        if (userInput.nextLine().equalsIgnoreCase("y")) {
            useAvocado = true;
        }

        System.out.println("Do you need Saratoga chips? Y/N");
        if (userInput.nextLine().equalsIgnoreCase("y")) {
            useSaraTogaChips = true;
        }

        return new BurgerToppings(useAvocado, useSaraTogaChips, useExtraBeacon);
    }

    Order prepareCoffeeOrder(int coffeeOrder, int quantity, UUID customerId, CoffeeToppings coffeeToppings){
        switch (coffeeOrder) {
            case 1: return new Order(coffeeToppings.updateToppings(new Americano()), quantity, customerId);    
        
            case 2: return new Order(coffeeToppings.updateToppings(new Cappucino()), quantity, customerId);   
            
            case 3: return new Order(coffeeToppings.updateToppings(new Latte()), quantity, customerId);

            case 4: return new Order(coffeeToppings.updateToppings(new Espresso()), quantity, customerId);

            default: return new Order(coffeeToppings.updateToppings(new Americano()), quantity, customerId);
        }
    }

    Order prepareBurgerOrder(int burgerOrder, int quantity, UUID customerId, BurgerToppings burgerToppings){
        switch (burgerOrder) {
            case 1: return new Order(burgerToppings.updateToppings(new Hamburger()), quantity, customerId);    
        
            case 2: return new Order(burgerToppings.updateToppings(new Classic()), quantity, customerId);   
            
            case 3: return new Order(burgerToppings.updateToppings(new Indian()), quantity, customerId);

            default: return new Order(burgerToppings.updateToppings(new Hamburger()), quantity, customerId);
        }
    }

    Order prepareTeaOrder(int teaOrder, int quantity, UUID customerId){
        switch (teaOrder) {
            case 1: return new Order(new Green(), quantity, customerId);    
        
            case 2: return new Order(new Black(), quantity, customerId);   
            
            case 3: return new Order(new Herbal(), quantity, customerId);

            default: return new Order(new Black(), quantity, customerId);
        }
    }

    public void displayPurchaseHistory(UUID customerId){
        System.out.println("****************************** PURCHASE HISTORY *********************");
        for (Order order : previousOrders) {      
            if (order.getCustomerId().equals(customerId)) {
                MenuItem item = order.getItem();
                System.out.println("ITEM: "+item.getTitle()+", PRICE: "+item.getPrice()+", QUANTITY: "+order.getQuantity());
            }                 
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("CUSTOMER ID:      "+customerId);
        System.out.println("*******************************************************************");        
    }

    public void displayBill(UUID customerId){
        System.out.println("****************************** INVOICE ************************************");
        double total = 0.00;        
        for (Order order : orders) {      
            if (order.getCustomerId().equals(customerId)) {
                MenuItem item = order.getItem();
                total += item.getPrice() * order.getQuantity();
                System.out.println("ITEM: "+item.getTitle()+", PRICE: "+item.getPrice()+", QUANTITY: "+order.getQuantity());
            }                 
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("TOTAL BILL:     "+total);
        System.out.println("CUSTOMER ID:      "+customerId);
        System.out.println("*******************************************************************");              
    }

    public void finalizeData(){
        orderRepository.saveDataAndClear(orders);
    }

    public void loadPreviousOrders(){        
        List<Order> previousOrders = orderRepository.retrieveData(); 
        if (previousOrders != null) {
            this.previousOrders.addAll(previousOrders);   
        }
    }

}
