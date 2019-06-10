package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirlineDAO implements Dao<Airline> {

    private List<Airline> airlines = new ArrayList<>();

    @Override
    public Optional<Airline> get(long id) {
        return Optional.empty().ofNullable(airlines.get((int) id));
    }

    @Override
    public List<Airline> getAll() {
        return airlines;
    }

    @Override
    public void save(Airline t) {
        airlines.add(t);
    }

    @Override
    public void update(Airline t, String[] params) {

    }

    @Override
    public void delete(Airline t) {
        airlines.remove(t);
    }

}
