/* 
 *  MIT License
 *  
 *  Copyright (c) 2024 lalit
 *  
 *  Created on Tue Oct 01 2024 by lalit
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

package CoffeeMaker.service;

import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import CoffeeMaker.models.MenuItem;
import CoffeeMaker.models.Order;
import CoffeeMaker.models.burger.Classic;
import CoffeeMaker.models.burger.Hamburger;
import CoffeeMaker.models.burger.Indian;
import CoffeeMaker.models.coffee.Americano;
import CoffeeMaker.models.coffee.Cappucino;
import CoffeeMaker.models.coffee.Espresso;
import CoffeeMaker.models.coffee.Latte;

public class OrderService {

    public Order takeOrder(Scanner userInput, UUID customerId) {
        System.out.println("Please order something, we have -");
        System.out.println("1. Coffee");
        System.out.println("2. Tea");
        System.out.println("3. Burger");
        int category = 1;
        if (userInput.hasNextInt()) {
            category = userInput.nextInt();
        }        
        return prepareOrder(category, userInput, customerId);
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
                System.out.println("Please enter the quantity");
                if (userInput.hasNextInt()) {
                    quantityInput = userInput.nextInt();  
                }                 
            return prepareCoffeeOrder(orderInput, quantityInput, customerId);            

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
                System.out.println("Please enter the quantity");
                if (userInput.hasNextInt()) {
                    quantityInput = userInput.nextInt();  
                } 
            return prepareBurgerOrder(orderInput, quantityInput, customerId);
        
            default:
                System.out.println("Please order something from our Menu");                                      
            return null; 
        }
    }


    Order prepareCoffeeOrder(int coffeeOrder, int quantity, UUID customerId){
        switch (coffeeOrder) {
            case 1: return new Order(new Americano(), quantity, customerId);    
        
            case 2: return new Order(new Cappucino(), quantity, customerId);   
            
            case 3: return new Order(new Latte(), quantity, customerId);

            case 4: return new Order(new Espresso(), quantity, customerId);

            default: return new Order(new Americano(), quantity, customerId);
        }
    }


    Order prepareBurgerOrder(int burgerOrder, int quantity, UUID customerId){
        switch (burgerOrder) {
            case 1: return new Order(new Hamburger(), quantity);    
        
            case 2: return new Order(new Classic(), quantity);   
            
            case 3: return new Order(new Indian(), quantity);

            default: return new Order(new Hamburger(), quantity);
        }
    }


    Order prepareTeaOrder(int burgerOrder, int quantity, UUID customerId){
        switch (burgerOrder) {
            case 1: return new Order(new Hamburger(), quantity);    
        
            case 2: return new Order(new Classic(), quantity);   
            
            case 3: return new Order(new Indian(), quantity);

            default: return new Order(new Hamburger(), quantity);
        }
    }


    public void displayBill(List<Order> orders, UUID customerId){
        System.out.println("****************************** INVOICE ************************************");
        double total = 0.00;
        for (Order order : orders) {            
            MenuItem item = order.getItem();
            total += item.getPrice() * order.getQuantity();
            System.out.println("ITEM: "+item.getTitle()+", PRICE: "+item.getPrice()+", QUANTITY: "+order.getQuantity());
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("TOTAL BILL:     "+total);
        System.out.println("*******************************************************************");
    }

}
