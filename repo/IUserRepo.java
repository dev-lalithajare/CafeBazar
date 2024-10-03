package CafeBazar.repo;

import java.util.List;

import CafeBazar.models.Customer;

public interface IUserRepo {
    public void saveData(List<Customer> customers);
    public List<Customer> retrieveData();
}
