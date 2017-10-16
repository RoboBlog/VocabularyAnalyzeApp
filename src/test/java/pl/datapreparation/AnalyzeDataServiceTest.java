package pl.datapreparation;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import pl.translator.Word;
import pl.translator.WordRepository;
import static org.assertj.core.api.Assertions.*;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AnalyzeDataServiceTest {
    private WordRepository mockedWordRepository;
    private AnalyzeDataService analyzeDataService;


    @Before
    public void before(){
        mockedWordRepository = mock(WordRepository.class);
        List<Word> word = new LinkedList<>();
        word.add(new Word(1,"mother", "testpl"));
        word.add(new Word(2,"arm", "test2pl"));
        word.add(new Word(3,"home", "test3pl"));
        when(mockedWordRepository.findByEnglishWordIn(any())).thenReturn(word);
        analyzeDataService = new AnalyzeDataService(mockedWordRepository);
    }

    @Test
    public void dataPreparation_CorrectData_WordsList(){
       List<ResultWord> test = analyzeDataService.dataPreparation("home arm mother mother mother");

        assertThat(test.get(0).getAmount()).isEqualTo(3);
        assertThat(test.get(0).getWord().getPolishWord()).isEqualTo("testpl");
    }


    //TODO fix this test
    @Test
    public void dataPreparation_UknownWords_EmptyList(){
        List<ResultWord> test = analyzeDataService.dataPreparation("test test2");

//        assertThat(test==null);

    }
}
