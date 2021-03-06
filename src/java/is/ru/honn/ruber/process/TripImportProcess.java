
package is.ru.honn.ruber.process;

import is.ru.honn.ruber.domain.Trip;
import is.ru.honn.ruber.domain.User;
import is.ru.honn.ruber.feeds.FeedException;
import is.ru.honn.ruber.feeds.FeedHandler;
import is.ru.honn.ruber.feeds.FeedReader;
import is.ru.honn.ruber.service.RuberServiceStub;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.util.Collection;
import java.util.Locale;
import java.util.List;
import java.util.logging.Logger;

import is.ruframework.process.RuAbstractProcess;
import is.ru.honn.ruber.service.RuberService;
import is.ru.honn.ruber.service.Content;

public class TripImportProcess extends RuAbstractProcess implements FeedHandler
{
    Logger log = Logger.getLogger(this.getClass().getName());
    RuberService contentService;
    FeedReader reader;
    MessageSource msg;
    Locale loc = new Locale("is");


    public void beforeProcess()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("app.xml");
        contentService = (RuberServiceStub)ctx.getBean("RuberService");
        reader = (FeedReader)ctx.getBean("feedReader");
        reader.setFeedHandler(this);
        msg = (MessageSource)ctx.getBean("messageSource");

        log.info(msg.getMessage("processbefore",
                new Object[]{getProcessContext().getProcessName()},
                loc));

    }

    @Override
    public void processContent(Trip content)
    {
        contentService.addTrip(content);
    }


    public void startProcess()
    {
        log.info(msg.getMessage("processstart",
                new Object[] { getProcessContext().getProcessName() },
                loc));
        try
        {
            reader.read(getProcessContext().getImportURL());
        }
        catch (FeedException e)
        {
            log.info(msg.getMessage("processreaderror",
                    new Object[] { getProcessContext().getImportFile() },
                    Locale.getDefault()));
            log.info(e.getMessage());
        }

    }

    public void afterProcess()
    {
        log.info(msg.getMessage("processstartdone",
                new Object[] {contentService.getHistory("7354db54-cc9b-4961-81f2-0094b8e2d215").size()},
                loc));
    }


}
