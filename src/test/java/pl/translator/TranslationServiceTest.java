package pl.translator;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TranslationServiceTest {

    @Test
    public void translateEnglishToPolish_EnglishWord_PolishWord(){
        WordRepository wordRepository = mock(WordRepository.class);
        when(wordRepository.findByEnglishWord("mother")).thenReturn(new Word("mother", "matka"));

        TranslationService translationService = new TranslationService(wordRepository);
        String word = translationService.translateEnglishToPolish("mother");

        assertThat(word).isEqualTo("matka");
    }

    @Test(expected = NoSuchElementException.class)
    public void translateEnglishToPolish_UnknownEnglishWord_NoSuchElementException(){
        WordRepository wordRepository = mock(WordRepository.class);

        TranslationService translationService = new TranslationService(wordRepository);
        String word = translationService.translateEnglishToPolish("mother");
    }
}
