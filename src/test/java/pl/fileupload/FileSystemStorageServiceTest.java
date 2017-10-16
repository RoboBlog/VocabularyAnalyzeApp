package pl.fileupload;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.assertj.core.api.Assertions.assertThat;


public class FileSystemStorageServiceTest {

        private StorageProperties properties = new StorageProperties();
        private FileSystemStorageService service;

        @Before
        public void init() {
            properties.setLocation("target/files/" + Math.abs(new Random().nextLong()));
            service = new FileSystemStorageService(properties);
            service.init();
        }

        @Test
        public void load_NonExistent_NotExist() {
            assertThat(service.load("foo.txt")).doesNotExist();
        }

        @Test
        public void save_CorrectData_FileIsExists() {
            service.store(new MockMultipartFile("foo", "foo.txt", MediaType.TEXT_PLAIN_VALUE,
                    "Hello World".getBytes()));

            assertThat(service.load(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd-HH:mm"))+"-foo.txt")).exists();
        }

        @Test(expected = StorageException.class)
        public void save_NotPermitted_StorageException() {
            service.store(new MockMultipartFile("foo", "../foo.txt",
                    MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
        }

        @Test
        public void save_Permitted_Nothing() {
            service.store(new MockMultipartFile("foo", "bar/../foo.txt",
                    MediaType.TEXT_PLAIN_VALUE, "Hello World".getBytes()));
        }

        @After
        public void deleteAll(){
            service.deleteAll();
        }

}
