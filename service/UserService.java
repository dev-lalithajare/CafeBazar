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

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import CafeBazar.models.Customer;
import CafeBazar.models.Order;
import CafeBazar.utils.Constants;

public class UserService {
    
    private List<Customer> customers = new ArrayList<>();
    private OrderService orderService;

    public UserService(OrderService orderService) {
        this.orderService = orderService;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public Customer askUserData(Scanner userInput){
        System.out.println("\nWelcome to CAFE BAZAR!");
        System.out.println("Enter your name");
        String name = userInput.nextLine();
        Customer customer = addCustomer(name);
        System.out.println("\nTaking orders from "+customer.getName()+"\n");        
        return customer;
    }

    private Customer addCustomer(String name){
        Customer customer = getCustomerByName(name);
        if (customer != null) {
            List<Order> previousOrders = orderService.getPreviousOrdersForCustomer(customer.getCustomerId());
            if (!previousOrders.isEmpty()) {
                System.out.println("\nYour previous orders");
                orderService.displayPurchaseHistory(customer.getCustomerId());
            }
            return customer;
        }else{
            customer = new Customer(name);
            customers.add(customer);
            return customer;
        }
    }

    private Customer getCustomerByName(String name){
        for(int i=0; i<customers.size(); i++){
            if (customers.get(i).getName().equals(name)) {
                return customers.get(i);
            }
        }
        return null;
    }

    public void saveData(){
        File file = new File(Constants.CUTOMER_RECORDS_FILE);
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(customers);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public void retrieveData(){        
        try{
            FileInputStream fin = new FileInputStream(Constants.CUTOMER_RECORDS_FILE);
            ObjectInputStream ois = new ObjectInputStream(fin);
            List<Customer> customers = (List<Customer>) ois.readObject();
            ois.close();
            this.customers = customers;
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        
    }

}
