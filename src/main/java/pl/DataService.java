package pl;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by maciek on 8/17/17.
 */
@Service
public class DataService {

    final String fileLocation = "/home/maciek/Desktop/vocabularyanalyzeapp/upload-dir/";


    //factory
    public String getData(String fileExtension, String fileName) throws IOException {
        switch (fileExtension) {
            case "txt":
                String dataFromTextFile = getDataFromTextFile(fileName);
                return dataFromTextFile;
            case "pdf":
                String dataFromPdfFile = getDataFromPdfFile(fileName);
                return dataFromPdfFile;
            default:
                return "Format is not supported";
        }
    }

    public String getDataFromTextFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner( new File(fileLocation+fileName), "UTF-8" );
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        return text;
    }

    public String getDataFromPdfFile(String filename) throws IOException {
        PdfReader pdfReader = new PdfReader(fileLocation+filename);
        int numberOfPages = pdfReader.getNumberOfPages();
        StringBuilder text = new StringBuilder();
        for(int i = 1; i<=numberOfPages; i++){
            text.append(PdfTextExtractor.getTextFromPage(pdfReader, i));
        }

        return text.toString();
    }
}
