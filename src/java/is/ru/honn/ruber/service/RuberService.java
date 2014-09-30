package is.ru.honn.ruber.service;

import is.ru.honn.ruber.domain.Price;
import is.ru.honn.ruber.domain.Product;
import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;

import java.util.List;

public interface RuberService
{
  public List<Product> getProducts(double latitude, double longitude) throws ServiceException;
  public List<Price> getPriceEstimates(double startLatitude, double startLongitude,
                                       double endLatitude, double endLongitude) throws ServiceException;
  public void addTrip(Trip trip);
    public List<Trip> getHistory(String uuid);
    public void signup(User user);
    public List<User>getUsers();
    public User getUser(String uuid);
}
