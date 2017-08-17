package pl.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pl.AnalyzeDataService;
import pl.DataService;

import java.io.File;
import java.io.IOException;
import java.nio.file.spi.FileTypeDetector;

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
    public String handleFileUpload(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = storageService.store(file);
        String fileExtension = storageService.getFileExtension(fileName);
        String data = dataService.getData(fileExtension, fileName);
        System.out.print(data);

        return "You successfully uploaded " + file.getOriginalFilename() + "!";
    }



    @ExceptionHandler(StorageFileNotFoundException.class)
    public ResponseEntity<?> handleStorageFileNotFound(StorageFileNotFoundException exc) {
        return ResponseEntity.notFound().build();
    }



}
