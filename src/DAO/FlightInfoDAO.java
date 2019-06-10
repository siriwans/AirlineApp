package DAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FlightInfoDAO implements Dao<FlightInfo> {



    private List<FlightInfo> flights = new ArrayList<>();

    public FlightInfo get(int flightNo, int airline){

        List<FlightInfo> collection = flights.stream().filter(f -> (f.getAirline() == flightNo) && (f.getAirline() == airline)).collect(Collectors.toList());

        if (collection.size() > 0) {
            return collection.get(0);
        }

        return null;
    }

    @Override
    public List<FlightInfo> getAll() {
        return flights;
    }

    @Override
    public void save(FlightInfo t) {
        flights.add(t);
    }

    @Override
    public void update(FlightInfo t, String[] params) {

    }

    @Override
    public void delete(FlightInfo t) {
        flights.remove(t);
    }
}
