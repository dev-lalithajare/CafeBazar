package CafeBazar.repo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;

import CafeBazar.models.Customer;
import CafeBazar.utils.Constants;

public class UserRepository implements IUserRepo{

    @Override
    public void saveData(List<Customer> customers) {
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

    @Override
    public List<Customer> retrieveData() {
        try{
            FileInputStream fin = new FileInputStream(Constants.CUTOMER_RECORDS_FILE);
            ObjectInputStream ois = new ObjectInputStream(fin);
            List<Customer> customers = (List<Customer>) ois.readObject();
            ois.close();
            return customers;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }
}
