package pl;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Map;

/**
 * Created by maciek on 8/16/17.
 */
@Service
 class WebScraperService {

    private AnalyzeDataService analyzeDataService;

    WebScraperService(AnalyzeDataService analyzeDataService) {
        this.analyzeDataService = analyzeDataService;
    }

    Document getWebsite(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        return doc;
    }

     Map<String, Integer> parseHtmlDataToString(Document htmlWebsite){
        Element body = htmlWebsite.body();
        String data = body.text();
         Map<String, Integer> strings = analyzeDataService.dataPreparation(data);

         return strings;
    }
}
