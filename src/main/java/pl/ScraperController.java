package pl;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

/**
 * Created by maciek on 8/16/17.
 */

@RestController
public class ScraperController {

    private final WebScraperService webScraperService;

    @Autowired
    public ScraperController(WebScraperService webScraperService) {
        this.webScraperService = webScraperService;
    }


    @PostMapping("/fetchwebsite")
    public String fetchWebsite(@RequestParam String url) throws IOException {
            Document website = webScraperService.getWebsite(url);
            String websiteBodyInString = webScraperService.parseHtmlDataToString(website);
        return websiteBodyInString;
    }


}
