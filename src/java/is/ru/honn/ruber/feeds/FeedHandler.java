package is.ru.honn.ruber.feeds;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.service.Content;

public interface FeedHandler
{
  public void processContent(Trip content);
}
