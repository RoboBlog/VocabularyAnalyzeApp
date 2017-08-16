package pl;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import javax.lang.model.util.Elements;
import javax.print.Doc;
import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;

import static org.jsoup.nodes.Document.OutputSettings.Syntax.html;

/**
 * Created by maciek on 8/16/17.
 */
@Service
 class WebScraperService {


     Document getWebsite(String url) throws IOException {
        Document doc = Jsoup.connect(url).get();
        System.out.print(doc);
        return doc;
    }

     String parseHtmlDataToString(Document htmlWebsite){
        Element body = htmlWebsite.body();
        String data = body.text();
        System.out.print(data);
        return data;
    }
}
