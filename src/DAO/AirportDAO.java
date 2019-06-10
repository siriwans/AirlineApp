package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AirportDAO implements Dao<Airport> {


    private List<Airport> airports = new ArrayList<>();

    @Override
    public Optional<Airport> get(long id) {
        return Optional.empty().ofNullable(airports.get((int) id));
    }

    @Override
    public List<Airport> getAll() {
        return airports;
    }

    @Override
    public void save(Airport t) {
        airports.add(t);
    }

    @Override
    public void update(Airport t, String[] params) {

    }

    @Override
    public void delete(Airport t) {
        airports.remove(t);
    }

}
