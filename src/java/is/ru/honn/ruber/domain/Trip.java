package is.ru.honn.ruber.domain;

/**
 * Created by Hafþór Örn on 28.9.2014.
 */
public class Trip {
    String uuid;
    int request_time;
    String product_id;
    String status;
    float distance;
    int start_time;
    int end_time;

    public Trip(String uuid, int request_time, String product_id, String status, float distance, int start_time, int end_time) {
        this.uuid = uuid;
        this.request_time = request_time;
        this.product_id = product_id;
        this.status = status;
        this.distance = distance;
        this.start_time = start_time;
        this.end_time = end_time;
    }

}