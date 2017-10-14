package pl.datapreparation;

import org.jsoup.nodes.Document;
import org.junit.Test;

public class WebScraperServiceTest {
    public void test(){
        WebScraperService webScraperService = new WebScraperService();
//        webScraperService.getWebsite();



    }

    @Test
    public void testParse(){
//        Document htmlData = new Document("<html><body>test<body></html>");
        Document htmlData = Document.createShell("test");

        WebScraperService webScraperService = new WebScraperService();

        String textData = webScraperService.parseHtmlDataToString(htmlData);
        System.out.println(textData);

        //

    }
}
