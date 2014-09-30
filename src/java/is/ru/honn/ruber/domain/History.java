package is.ru.honn.ruber.domain;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hafþór Örn on 28.9.2014.
 */
public class History {
    private int offset;
    private int limit;
    private int count;
    private List<Trip> trips;

    public History() {
    }

    public History(int offset, int limit,int count) {
        this.offset = offset;
        this.limit = limit;
        this.count = count;
        this.trips = new ArrayList<Trip>();

    }

    public List<Trip> getTrips(String uuid) {
        List<Trip> tripsById = new ArrayList<Trip>();
            for(int i = 0; i<trips.size();i++){
                if(trips.get(i).uuid == uuid){
                    tripsById.add(trips.get(i));
                }
            }
        return tripsById;
    }
    public void addTrip(Trip trip) {
        if(count <limit) {
            trips.add(trip);
        }
        else{
            //throw exception if limit is exceeded
        }
    }
}
