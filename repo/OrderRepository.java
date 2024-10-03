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
