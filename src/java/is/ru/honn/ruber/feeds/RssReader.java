package is.ru.honn.ruber.feeds;

import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndEntry;

import java.net.URL;
import java.net.MalformedURLException;
import java.util.List;
import java.util.Iterator;

import is.ru.honn.ruber.service.Content;

public class RssReader extends AbstractFeedReader
{
  public RssReader()
  {
  }

  public void setFeedHandler(FeedHandler handler)
  {
    this.handler = handler;
  }

  public void read(String source) throws FeedException
  {
    if (handler == null)
    {
      throw new FeedException("Handler unspecified");
    }

    URL feedUrl = null;
    try
    {
      feedUrl = new URL(source);
    }
    catch (MalformedURLException murlex)
    {
      throw new FeedException ("URL is not correct", murlex);
    }

    // Open the feed
    SyndFeedInput input = new SyndFeedInput();
    SyndFeed syndFeed = null;
    try
    {
      syndFeed = input.build(new XmlReader(feedUrl));
    }
    catch (Exception ioex)
    {
      throw new FeedException ("URL is not correct", ioex);
    }

    // Get the items and create content for each
    List list = syndFeed.getEntries();
    Iterator i  = list.iterator();
    SyndEntry ent = null;
    Content content;
    while (i.hasNext())
    {
      ent = (SyndEntry)i.next();
      content = new Content();
      content.setTitle(ent.getTitle());
      content.setLink(ent.getLink());
      content.setDescription(ent.getDescription().getValue());
      content.setPubDate(ent.getPublishedDate());
      handler.processContent(content);
    }
  }
}
