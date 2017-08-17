package pl;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.PdfTextExtractor;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.poifs.filesystem.DocumentEntry;
import org.apache.poi.poifs.filesystem.DocumentInputStream;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
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
            case "docx":
                String dataFromDocxFile = getDataFromDocxFile(fileName);
                return dataFromDocxFile;
            case "doc":
                String dataFromDocFile = getDataFromDocFile(fileName);
                return dataFromDocFile;
//            case "odt":
//            case "rtf":
            default:
                return "Format is not supported";
        }
    }

     private String getDataFromTextFile(String fileName) throws FileNotFoundException {
        Scanner scanner = new Scanner( new File(fileLocation+fileName), "UTF-8" );
        String text = scanner.useDelimiter("\\A").next();
        scanner.close();

        return text;
    }

     private String getDataFromPdfFile(String filename) throws IOException {
        PdfReader pdfReader = new PdfReader(fileLocation+filename);
        int numberOfPages = pdfReader.getNumberOfPages();
        StringBuilder text = new StringBuilder();
        for(int i = 1; i<=numberOfPages; i++){
            text.append(PdfTextExtractor.getTextFromPage(pdfReader, i));
        }

        return text.toString();
    }

    private String getDataFromDocxFile(String filename) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation+filename));
        XWPFDocument document = new XWPFDocument(file);

        List<XWPFParagraph> paragraphs = document.getParagraphs();
        StringBuilder text = new StringBuilder();
        for (XWPFParagraph para : paragraphs) {
           text.append(para.getText());
        }
        file.close();

        return text.toString();
    }

    private String getDataFromDocFile(String filename) throws IOException {
        FileInputStream file = new FileInputStream(new File(fileLocation+filename));
        HWPFDocument document = new HWPFDocument(file);
        WordExtractor extractor = new WordExtractor(document);
        String text = extractor.getText();

        System.out.print(text);
        file.close();

        return "file";
    }

    //add odt/rtf
}
