package pl.datapreparation;

import com.github.tomakehurst.wiremock.WireMockServer;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Rule;
import org.junit.Test;

import java.io.IOException;
import java.net.UnknownHostException;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static org.assertj.core.api.Java6Assertions.assertThat;

public class WebScraperServiceTest {

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(8089);

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
    public void getWebsite_CorrectWebsite_DocWebsite() throws IOException {
        String html = "<html><head></head><body>TEST</body></html>";
        Document parse = Jsoup.parse(html);

        WireMockServer wireMockServer = new WireMockServer();
        wireMockServer.start();
        configureFor("localhost", 8080);
        stubFor(get(urlEqualTo("/test")).willReturn(aResponse().withBody("TEST")));

        WebScraperService webScraperService = new WebScraperService();

        Document website = webScraperService.getWebsite("http://localhost:8080/test");

        assertThat(website.body().text()).isEqualTo(parse.body().text());
        wireMockServer.stop();

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
