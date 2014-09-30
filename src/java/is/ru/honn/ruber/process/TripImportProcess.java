
package is.ru.honn.ruber.process;

import is.ru.honn.ruber.feeds.FeedException;
import is.ru.honn.ruber.feeds.FeedHandler;
import is.ru.honn.ruber.feeds.FeedReader;
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

public class ImportContentProcess extends RuAbstractProcess implements FeedHandler
{
    Logger log = Logger.getLogger(this.getClass().getName());
    RuberService contentService;
    FeedReader reader;
    MessageSource msg;

    public void beforeProcess()
    {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("app.xml");
        contentService = (RuberService)ctx.getBean("contentService");
        reader = (FeedReader)ctx.getBean("feedReader");
        reader.setFeedHandler(this);
        msg = (MessageSource)ctx.getBean("messageSource");
        log.info(msg.getMessage("processbefore",
                new Object[] { getProcessContext().getProcessName() } ,
                Locale.getDefault()));
    }

    public void processContent(Object content)
    {
        contentService.addContent((Content)content);
    }


    public void startProcess()
    {
        log.info(msg.getMessage("processstart",
                new Object[] { getProcessContext().getProcessName() },
                Locale.getDefault()));
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
        log.info(msg.getMessage("processstartdone",
                new Object[] {contentService.getContents().size()},
                Locale.getDefault()));
    }

    public void afterProcess()
    {
        Collection<Content> col = contentService.getContents();
        for (Content cnt: col)
        {
            System.out.println(cnt);
        }
    }

    public void processContent(Content content)
    {
        contentService.addContent(content);
    }
}
