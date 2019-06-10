package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CreditCardDAO implements Dao<CreditCard> {


    private List<CreditCard> creditcards = new ArrayList<>();


    @Override
    public List<CreditCard> getAll() {
        return creditcards;
    }

    @Override
    public void save(CreditCard t) {
        creditcards.add(t);
    }

    @Override
    public void update(CreditCard t, String[] params) {

    }

    @Override
    public void delete(CreditCard t) {
        creditcards.remove(t);
    }
}
