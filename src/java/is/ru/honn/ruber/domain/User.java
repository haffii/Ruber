package is.ru.honn.ruber.domain;

/**
 * Created by Hafþór Örn on 28.9.2014.
 */
public class User {
    String uuid;
    String userName;
    String firstName;
    String lastName;
    String password;
    String email;
    String picture;
    String promoCode;

    public User() {

    }

    public String getUuid() {
        return uuid;
    }

    public User(String uuid, String userName, String firstName, String lastName, String password, String email, String picture, String promoCode) {
        this.uuid = uuid;
        this.userName = userName;
        this.firstName = firstName;
        this.lastName = lastName;
        this.password = password;
        this.email = email;
        this.picture = picture;
        this.promoCode = promoCode;
    }
}
