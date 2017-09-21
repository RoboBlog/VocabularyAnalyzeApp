package pl.datapreparation;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pl.translator.Word;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by maciek on 8/16/17.
 */

@CrossOrigin(origins = "http://localhost:8000")
@RestController
public class ScraperController {

    private final WebScraperService webScraperService;
    private final AnalyzeDataService analyzeDataService;

    @Autowired
    public ScraperController(WebScraperService webScraperService, AnalyzeDataService analyzeDataService) {
        this.webScraperService = webScraperService;
        this.analyzeDataService = analyzeDataService;
    }


    @PostMapping("/fetchwebsite")
    public Map<Word, Integer> fetchWebsite(@RequestParam String url, @RequestParam int part) throws IOException {
            Document website = webScraperService.getWebsite(url);
            String websiteBodyInString = webScraperService.parseHtmlDataToString(website);
            Map<Word, Integer> stringIntegerMap = analyzeDataService.dataPreparation(websiteBodyInString, part);
        return stringIntegerMap;
    }

}