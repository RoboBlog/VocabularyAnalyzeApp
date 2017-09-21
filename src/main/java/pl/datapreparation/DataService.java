package pl.datapreparation;

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
import org.jopendocument.dom.ODPackage;
import org.jopendocument.dom.ODSingleXMLDocument;
import org.jopendocument.dom.ODXMLDocument;
import org.jopendocument.dom.text.Paragraph;
import org.jopendocument.dom.text.TextDocument;
import org.springframework.stereotype.Service;

import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.rtf.RTFEditorKit;
import java.io.*;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maciek on 8/17/17.
 */
@Service
public class DataService {

    final String fileLocation = "/home/maciek/Desktop/vocabularyanalyzeapp/upload-dir/";


    //factory
    public String getData(String fileExtension, String fileName) throws IOException, BadLocationException {
        switch (fileExtension) {
            case "txt":
                String dataFromTextFile = getDataFromTextFile(fileName);
                return dataFromTextFile;
            case "pdf":
                String dataFromPdfFile = getDataFromPdfFile(fileName);
                return dataFromPdfFile;
            case "docm":
                String dataFromDocmFile = getDataFromDocxFile(fileName);
                return dataFromDocmFile;
            case "docx":
                String dataFromDocxFile = getDataFromDocxFile(fileName);
                return dataFromDocxFile;
            case "doc":
                String dataFromDocFile = getDataFromDocFile(fileName);
                return dataFromDocFile;
            case "odt":
                String dataFromOdtFile = getDataFromOdtFile(fileName);
                return dataFromOdtFile;
            case "rtf":
                String dataFromRtfFile = getDataFromRtfFile(fileName);
                return dataFromRtfFile;
            default:
                return "Format is not supported";
        }
    }

    private String getDataFromRtfFile(String fileName) throws IOException, BadLocationException {
        RTFEditorKit rtfParser = new RTFEditorKit();
        Document document = rtfParser.createDefaultDocument();
        rtfParser.read(new FileInputStream(fileLocation+fileName), document, 0);
        String text = document.getText(0, document.getLength());

        return text;

    }

    private String getDataFromOdtFile(String fileName) throws IOException {
        ODPackage file = new ODPackage(new File(fileLocation+fileName));
        StringBuilder text = new StringBuilder();
        int paragraphCount=file.getTextDocument().getParagraphCount();

        for(int i = 0; i<paragraphCount; i++){
            text.append(" ").append(file.getTextDocument().getParagraph(i).toString());
        }
        return text.toString();
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
        file.close();

        return text;
    }

}
