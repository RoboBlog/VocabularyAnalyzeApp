package pl.datapreparation;

import org.junit.Test;
import pl.datapreparation.DataService;

import javax.swing.text.BadLocationException;
import java.io.IOException;

/**
 * Created by maciek on 8/18/17.
 */
public class DataServiceTest {

    public String filename;

    @Test
    public void getDataTxtTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String txt = dataService.getData("txt", filename);
        //assert
    }

    @Test
    public void getDataPdfTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String pdf = dataService.getData("pdf", filename);
        //assert
    }

    @Test
    public void getDataDocmTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String doc = dataService.getData("docm", filename);
        //assert
    }
    @Test
    public void getDataDocxTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String docx = dataService.getData("docx", filename);
        //assert
    }

    @Test
    public void getDataDocTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String docx = dataService.getData("doc", filename);
        //assert
    }

    @Test
    public void getDataOdtTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String docx = dataService.getData("odt", filename);
        //assert
    }


    @Test
    public void getDataRtfTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        String docx = dataService.getData("rtf", filename);
        //assert
    }



}