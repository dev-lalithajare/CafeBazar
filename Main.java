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

package CafeBazar;

import java.util.Scanner;

import CafeBazar.models.Customer;
import CafeBazar.repo.IOrderRepo;
import CafeBazar.repo.IUserRepo;
import CafeBazar.repo.OrderRepository;
import CafeBazar.repo.UserRepository;
import CafeBazar.service.BillService;
import CafeBazar.service.OrderService;
import CafeBazar.service.UserService;

public class Main {
    
    static IOrderRepo orderRepository = new OrderRepository();
    static IUserRepo userRepository = new UserRepository();

    static OrderService orderService = new OrderService(orderRepository);
    static UserService userService = new UserService(userRepository);
    static BillService billService = new BillService(orderRepository);

    public static void main(String[] args) {

        orderService.loadPreviousOrders();
        userService.retrieveData();
        boolean takeNewOrder = true;        
        Scanner userInput = new Scanner(System.in);
        System.out.println("\nWelcome to CAFE BAZAR!");
        System.out.println("Enter your name");
        String name = userInput.nextLine();

        Customer customer = userService.getCustomerByName(name);
        if (customer != null) {
            billService.displayPurchaseHistory(customer.getCustomerId());
        }else{
            customer = userService.addCustomer(name);
        }

        while (takeNewOrder) {
            boolean isOrderTaken = orderService.takeOrder(userInput, customer.getCustomerId());  
            if (isOrderTaken) {                
                System.out.println("Another order? Y/N");   
                if (userInput.next().equalsIgnoreCase("Y")) {
                    takeNewOrder = true;
                }else{
                    takeNewOrder = false;
                }
            }else{
                takeNewOrder = true;
            }             
            
        }
        billService.displayBill(customer.getCustomerId(), orderService.getOrders());
        orderService.finalizeData();
        userService.finalizeData();
        userInput.close();        
    }

}
