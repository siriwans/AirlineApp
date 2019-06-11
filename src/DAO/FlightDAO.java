package DAO;

import com.group03_application.AirlineApp;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class FlightDAO implements Dao<Flight> {


    private List<Flight> flights = new ArrayList<>();

    public List<Flight> get(String source, String destination) {

        ResultSet results = AirlineApp.airlineDB.getFlight(source, destination);

        try {
            while (results.next()) {
                Flight f = new Flight(results.getInt("Airline"), results.getInt("FlightNo"),
                        results.getString("SourceAirport"), results.getString("DestAirport"));
                save(f);
            }

            List<Flight> collection = flights.stream().filter(f -> (f.getDestAirport() == destination) && (f.getSourceAirport() == source)).collect(Collectors.toList());
            return collection;
        } catch (Exception sqlException) {
            System.out.println("//TODO No customer by that passport in our system");
        }

        return  null;


/*

        List<Flight> collection = flights.stream().filter(f -> (f.getDestAirport() == destination) && (f.getSourceAirport() == source)).collect(Collectors.toList());

        if (collection.size() > 0) {
            return collection;
        } else {
            AirlineApp.airlineDB.getFlight(source, destination);
            Flight
        }

        return null;*/
    }

    @Override
    public List<Flight> getAll() {
        return flights;
    }

    @Override
    public void save(Flight t) {
        flights.add(t);
    }

    @Override
    public void update(Flight t, String[] params) {

    }

    @Override
    public void delete(Flight t) {
        flights.remove(t);
    }
}
