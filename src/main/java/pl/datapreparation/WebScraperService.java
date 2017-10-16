package pl.datapreparation;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
 class WebScraperService{

    Document getWebsite(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();

        return doc;
    }

     String parseHtmlDataToString(Document htmlWebsite){
        Element body = htmlWebsite.body();
        String data = body.text();

        return data;
    }
}
