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
