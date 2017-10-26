package pl.flashcards;

import org.junit.Test;
import pl.dictionary.UserDictionariesService;
import pl.dictionary.UserWord;
import pl.translator.Word;
import pl.user.UserService;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.mock;

public class FlashcardsServiceTest {


   //TODO INTEGRATION TEST
   //@Test
   public void getFlashcard(){
//      UserService userService = mock(UserService.class);
//      UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);
//
//      FlashcardsService flashcardsService = new FlashcardsService(userService, userDictionariesService);
   }


   @Test
   public void getRandomUserWord_CorrectSet_IllegalArgumentException() {
      UserService userService = mock(UserService.class);
      UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);

      FlashcardsService flashcardsService = new FlashcardsService(userService, userDictionariesService);

      Set<UserWord> words = new HashSet<>();
      words.add(new UserWord(new Word("test1", "test1")));
      words.add(new UserWord(new Word("test2", "test2")));

      UserWord randomUserWord = flashcardsService.getRandomUserWord(words);

      assertThat(randomUserWord).isNotNull();
   }


   @Test(expected = IllegalArgumentException.class)
   public void getRandomUserWord_EmptySet_IllegalArgumentException(){
      UserService userService = mock(UserService.class);
      UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);

      FlashcardsService flashcardsService = new FlashcardsService(userService, userDictionariesService);

      Set<UserWord> words = new HashSet<>();

      flashcardsService.getRandomUserWord(words);
   }

}
