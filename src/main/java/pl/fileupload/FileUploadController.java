package pl.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.datapreparation.AnalyzeDataService;
import pl.datapreparation.DataService;
import pl.datapreparation.ResultWord;

import javax.swing.text.BadLocationException;
import java.io.IOException;
import java.util.List;

//@CrossOrigin(origins = "http://localhost:8081")
@RestController
public class FileUploadController {

    private final StorageService storageService;
    private final DataService dataService;
    private final AnalyzeDataService analyzeDataService;
    @Autowired
    public FileUploadController(StorageService storageService, DataService dataService, AnalyzeDataService analyzeDataService) {
        this.storageService = storageService;
        this.dataService = dataService;
        this.analyzeDataService = analyzeDataService;
    }


    @PostMapping("/up")
    public List<ResultWord> handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException, BadLocationException {
        String fileName = storageService.store(file);
        String fileExtension = storageService.getFileExtension(fileName);
        String data = dataService.getData(fileExtension, fileName);
        List<ResultWord> resultWords = analyzeDataService.dataPreparation(data);
        return resultWords;
    }



    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }



}
