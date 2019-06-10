package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CustomerDAO implements Dao<Customer> {

    private List<Customer> customers = new ArrayList<>();

    @Override
    public Optional<Customer> get(long id) {
        return Optional.empty().ofNullable(customers.get((int) id));
    }

    @Override
    public List<Customer> getAll() {
        return customers;
    }

    @Override
    public void save(Customer t) {
        customers.add(t);
    }

    @Override
    public void update(Customer t, String[] params) {

    }

    @Override
    public void delete(Customer t) {
        customers.remove(t);
    }
}
