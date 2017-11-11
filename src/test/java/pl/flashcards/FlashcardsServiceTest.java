package pl.flashcards;

import org.junit.Test;
import pl.dictionary.UserDictionariesService;
import pl.dictionary.UserDictionary;
import pl.dictionary.UserWord;
import pl.translator.Word;
import pl.user.User;
import pl.user.UserService;

import java.util.HashSet;
import java.util.Set;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anySet;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class FlashcardsServiceTest {


   @Test
   public void getFlashcard_CorrectSet_NotNull(){
      UserService userService = mock(UserService.class);
      UserDictionariesService userDictionariesService = mock(UserDictionariesService.class);

      User user = new User("test", "test", "test@gmail.com");
      UserDictionary dictionary = new UserDictionary("test");
      dictionary.setId(1);
      dictionary.setUser(user);
      UserWord userWord = new UserWord(new Word("test", "test"));
      dictionary.addWord(userWord);

      when(userService.getUser()).thenReturn(user);
      when(userDictionariesService.getDictionary(anyLong())).thenReturn(dictionary);

      FlashcardsService flashcardsService = new FlashcardsService(userService, userDictionariesService);

      assertThat(flashcardsService).isNotNull();
   }


   @Test
   public void getRandomUserWord_CorrectSet_NotNull() {
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
