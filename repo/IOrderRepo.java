package CafeBazar.repo;

import java.util.List;

import CafeBazar.models.Order;

public interface IOrderRepo {
    public void saveDataAndClear(List<Order> orders);
    public List<Order> retrieveData();
}
