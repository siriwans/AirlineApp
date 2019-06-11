package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BookingDAO implements Dao<Booking> {


    private List<Booking> bookings = new ArrayList<>();




    @Override
    public List<Booking> getAll() {
        return bookings;
    }

    @Override
    public void save(Booking t) {
        bookings.add(t);
    }

    @Override
    public void update(Booking t, String[] params) {

    }

    @Override
    public void delete(Booking t) {
        bookings.remove(t);
    }
}
