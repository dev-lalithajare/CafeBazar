package CafeBazar.repo;

import java.util.List;

import CafeBazar.models.Order;

public interface IOrderRepo {
    public List<Order> getPreviousOrders();
    public void saveDataAndClear(List<Order> orders);
    public List<Order> retrieveData();
}
