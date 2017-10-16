package pl.datapreparation;

import org.junit.Before;
import org.junit.Test;
import pl.datapreparation.DataService;
import pl.fileupload.FileSystemStorageService;
import pl.fileupload.StorageProperties;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;

public class DataServiceTest {

    private StorageProperties properties = new StorageProperties();
    private FileSystemStorageService service;
    public final String filename = "/test";

    @Before
    public void init() {
        properties.setLocation("test/");
        service = new FileSystemStorageService(properties);
        service.init();
    }


    @Test
    public void getDataTxtTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String txt = dataService.getData("txt", filename + ".txt");

        assertThat(txt).isEqualToIgnoringWhitespace("test");
    }

    @Test
    public void getDataPdfTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String pdf = dataService.getData("pdf", filename + ".pdf");

        assertThat(pdf).isEqualToIgnoringWhitespace("test");
    }

    @Test
    public void getDataDocmTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String docm = dataService.getData("docm", filename + ".docm");

        assertThat(docm).isEqualToIgnoringWhitespace("test");
    }
    @Test
    public void getDataDocxTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String docx = dataService.getData("docx", filename + ".docx");

        assertThat(docx).isEqualToIgnoringWhitespace("test");
    }

    @Test
    public void getDataDocTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String doc = dataService.getData("doc", filename + ".doc");


        assertThat(doc).isEqualToIgnoringWhitespace("test");
    }

    @Test
    public void getDataOdtTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String odt = dataService.getData("odt", filename + ".odt");

        assertThat(odt).isEqualToIgnoringWhitespace("test");
    }


    @Test
    public void getDataRtfTest() throws IOException, BadLocationException {
        DataService dataService = new DataService();
        dataService.fileLocation="test";
        String rtf = dataService.getData("rtf", filename + ".rtf");

        assertThat(rtf).isEqualToIgnoringWhitespace("test");
    }



}