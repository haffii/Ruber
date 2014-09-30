package is.ru.honn.ruber.feeds;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.process.TripImportProcess;
import is.ruframework.http.SimpleHttpRequest;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.concurrent.ExecutionException;
import java.util.logging.Handler;

public class RssReader extends AbstractFeedReader
{
  public RssReader()
  {
  }

  public void setFeedHandler(FeedHandler handler)
  {
    this.handler = handler;
  }

  public void read(String source) throws FeedException {

      JSONParser parser = new JSONParser();

      if (handler == null) {
          throw new FeedException("Handler unspecified");
      }

      try {
          String s = SimpleHttpRequest.sendGetRequest(source);
          JSONObject json = (JSONObject) parser.parse(s);
          JSONArray history = (JSONArray) json.get("history");

          Trip i = new Trip("7354db54-cc9b-4961-81f2-0094b8e2d215", 1, "ddsa", "dsa", 1, 1,1 );
          //this.handler.processContent(i);
          handler.processContent(i);


      }
      catch ( Exception e){

      }

  }
}
