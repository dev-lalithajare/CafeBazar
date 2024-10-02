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
import CafeBazar.service.OrderService;
import CafeBazar.service.UserService;

public class Main {
    
    static OrderService orderService = new OrderService();
    static UserService userService = new UserService(orderService);

    public static void main(String[] args) {

        orderService.retrieveData();
        userService.retrieveData();
        boolean takeNewOrder = true;        
        Scanner userInput = new Scanner(System.in);

        Customer customer = userService.askUserData(userInput);        

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
        orderService.displayBill(customer.getCustomerId());
        orderService.saveDataAndClear();
        userService.saveData();
        userInput.close();        
    }

}
