package pl.datapreparation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static org.assertj.core.api.Java6Assertions.assertThat;

public class WebScraperServiceTest {

    @Test(expected = UnknownHostException.class)
    public void getWebsite_UnknownWebsite_UnknownHostException() throws IOException {
        WebScraperService webScraperService = new WebScraperService();

        webScraperService.getWebsite("https://en.wikipedisa.org/wiki/Hypertext_Transfer_Protocol/");
    }


    @Test(expected = IllegalArgumentException.class)
    public void getWebsite_incorrectUrl_IllegalArgumentException() throws IOException {
        WebScraperService webScraperService = new WebScraperService();

        webScraperService.getWebsite("test");
    }


    @Test
    public void parseHtmlDataToString_CorrrectHtmlData_HtmlBody(){
        WebScraperService webScraperService = new WebScraperService();
        String html = "<html><head><title>Test</title></head>"
                + "<body><p>TEST</p></body></html>";
        Document doc = Jsoup.parse(html);


        String textData = webScraperService.parseHtmlDataToString(doc);

        assertThat(textData).isEqualTo("TEST");
    }


    @Test
    public void parseHtmlDataToString_NoData_BlankString(){
        WebScraperService webScraperService = new WebScraperService();
        String html = "";
        Document doc = Jsoup.parse(html);

        String textData = webScraperService.parseHtmlDataToString(doc);

        assertThat(textData).isEmpty();
    }
}
