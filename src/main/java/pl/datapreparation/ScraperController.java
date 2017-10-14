package pl.datapreparation;

import org.hibernate.validator.constraints.URL;
import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
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
    public List<ResultWord> fetchWebsite(@NotNull @URL @RequestParam String url) throws IOException {
            Document website = webScraperService.getWebsite(url);
            String websiteBodyInString = webScraperService.parseHtmlDataToString(website);
            List<ResultWord> stringIntegerMap = analyzeDataService.dataPreparation(websiteBodyInString);
        System.out.println(stringIntegerMap);

        return stringIntegerMap;
    }

}
