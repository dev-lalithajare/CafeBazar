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

package CafeBazar.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import CafeBazar.models.Order;
import CafeBazar.utils.Constants;

public class OrderRepository implements IOrderRepo {
    
    private List<Order> previousOrders = new ArrayList<>();

    @Override
    public List<Order> getPreviousOrders() {
        return previousOrders;
    }

    @Override
    public void saveDataAndClear(List<Order> orders) {
        File file = new File(Constants.ORDER_RECORDS_FILE);        
        try {
            file.createNewFile();
            FileOutputStream fos = new FileOutputStream(file, false);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            orders.addAll(previousOrders);
            oos.writeObject(orders);
            oos.close();
            orders.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Order> retrieveData() {
        try{
            FileInputStream fin = new FileInputStream(Constants.ORDER_RECORDS_FILE);
            ObjectInputStream ois = new ObjectInputStream(fin);
            List<Order> orders = (List<Order>) ois.readObject();
            ois.close();
            this.previousOrders.addAll(orders);
            return this.previousOrders;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } 
    }
}
