package is.ru.honn.ruber.feeds;

public abstract class AbstractFeedReader implements FeedReader
{
  protected FeedHandler handler;

  public void setFeedHandler(FeedHandler handler)
  {
    this.handler = handler;
  }

  public abstract void read(String url) throws FeedException;
}
