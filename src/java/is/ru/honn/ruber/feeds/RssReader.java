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


          for(int i = 0; i < 5; i++)
          {

              JSONObject jsonObj = (JSONObject) history.get(i);
              System.out.print((String)jsonObj.get("uuid"));
              String uuid = (String)jsonObj.get("uuid");
              int request_time = (Integer)jsonObj.get("request_time");
              String product_id = (String)jsonObj.get("product_id");
              String status = (String)jsonObj.get("status");
              float distance = (Float)jsonObj.get("distance");
              int start_time = (Integer)jsonObj.get("start_time");
              int end_time = (Integer)jsonObj.get("end_time");

              handler.processContent(new Trip(uuid, request_time, product_id, status, distance, start_time, end_time ));
          }

          System.out.print("dsadsa");

      }
      catch ( Exception e){

      }

  }
}
