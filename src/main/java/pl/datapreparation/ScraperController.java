package pl.datapreparation;

import org.hibernate.validator.constraints.URL;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import pl.translator.Word;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

/**
 * Created by maciek on 8/16/17.
 */

@CrossOrigin(origins = "http://localhost:8000")
@RestController
@Validated
public class ScraperController {

    private final WebScraperService webScraperService;
    private final AnalyzeDataService analyzeDataService;

    @Autowired
    public ScraperController(WebScraperService webScraperService, AnalyzeDataService analyzeDataService) {
        this.webScraperService = webScraperService;
        this.analyzeDataService = analyzeDataService;
    }


    @PostMapping("/fetchwebsite")
    public Map<Word, Integer> fetchWebsite(@NotNull @URL @RequestParam String url) throws IOException {
            Document website = webScraperService.getWebsite(url);
            String websiteBodyInString = webScraperService.parseHtmlDataToString(website);
            Map<Word, Integer> stringIntegerMap = analyzeDataService.dataPreparation(websiteBodyInString);
        return stringIntegerMap;
    }

}
