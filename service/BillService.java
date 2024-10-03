/* 
 *  MIT License
 *  
 *  Copyright (c) 2024 Lalit Hajare
 *  
 *  Created on Thu Oct 03 2024 by lalit
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

import java.util.List;
import java.util.UUID;

import CafeBazar.models.MenuItem;
import CafeBazar.models.Order;
import CafeBazar.repo.IOrderRepo;

public class BillService {

    private IOrderRepo orderRepository;

     public BillService(IOrderRepo orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void displayPurchaseHistory(UUID customerId){
        System.out.println("****************************** PURCHASE HISTORY *********************");
        for (Order order : orderRepository.getPreviousOrders()) {      
            if (order.getCustomerId().equals(customerId)) {
                MenuItem item = order.getItem();
                System.out.println("ITEM: "+item.getTitle()+", PRICE: "+item.getPrice()+", QUANTITY: "+order.getQuantity());
            }                 
        }
        System.out.println("--------------------------------------------------------------------");
        System.out.println("CUSTOMER ID:      "+customerId);
        System.out.println("*******************************************************************");        
    }

    public void displayBill(UUID customerId, List<Order> orders){
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

}
