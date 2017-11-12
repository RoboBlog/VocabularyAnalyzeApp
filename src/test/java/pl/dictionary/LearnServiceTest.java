package pl.dictionary;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import pl.fileupload.StorageService;
import pl.user.User;
import pl.user.UserRepository;
import pl.user.UserService;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
@DataJpaTest
public class LearnServiceTest {

    @MockBean
    private StorageService storageService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestEntityManager testEntityManager;

    @Before
    public void init(){
        User user = new User("test", "test", "test@test.com");
        testEntityManager.persist(user);
        testEntityManager.flush();
    }

    @Test
    public void decrementationAmountWords_UserWithoutWords_decrementationAmount(){
        UserService userService = mock(UserService.class);
        when(userService.getUser()).thenReturn(userRepository.findByUsername("test"));

        LearnService learnService = new LearnService(userService, userRepository);
        learnService.decrementationAmountWords();

        assertThat(userRepository.findByUsername("test").getAmountWords()).isEqualTo(-1);
    }

    @Test
    public void decrementationAmountWords_UserWithoutWords_decreaseAmountByThree(){
        UserService userService = mock(UserService.class);
        when(userService.getUser()).thenReturn(userRepository.findByUsername("test"));

        LearnService learnService = new LearnService(userService, userRepository);
        learnService.decrementationAmountWords(3);

        assertThat(userRepository.findByUsername("test").getAmountWords()).isEqualTo(-3);
    }

    @Test
    public void incrementationAmountWords_UserWithoutWords_incrementationAmount(){
        UserService userService = mock(UserService.class);
        when(userService.getUser()).thenReturn(userRepository.findByUsername("test"));

        LearnService learnService = new LearnService(userService, userRepository);
        learnService.incrementationAmountWords();

        assertThat(userRepository.findByUsername("test").getAmountWords()).isEqualTo(1);

    }


    @Test
    public void decrementationAmountKnownWords_UserWithoutKnownWords_decrementationKnownAmount(){
        UserService userService = mock(UserService.class);
        when(userService.getUser()).thenReturn(userRepository.findByUsername("test"));

        LearnService learnService = new LearnService(userService, userRepository);
        learnService.decrementationAmountKnownWords();

        assertThat(userRepository.findByUsername("test").getAmountKnownWords()).isEqualTo(-1);
    }


    @Test
    public void decrementationAmountKnownWords_UserWithoutKnownWords_decreaseKnownAmountByThree(){
        UserService userService = mock(UserService.class);
        when(userService.getUser()).thenReturn(userRepository.findByUsername("test"));

        LearnService learnService = new LearnService(userService, userRepository);
        learnService.decrementationAmountKnownWords(3);

        assertThat(userRepository.findByUsername("test").getAmountKnownWords()).isEqualTo(-3);
    }

    @Test
    public void incrementationKnownAmountWords_UserWithoutKnownWords_incrementationKnownAmount(){
        UserService userService = mock(UserService.class);
        when(userService.getUser()).thenReturn(userRepository.findByUsername("test"));

        LearnService learnService = new LearnService(userService, userRepository);
        learnService.incrementationAmountKnownWords();

        assertThat(userRepository.findByUsername("test").getAmountKnownWords()).isEqualTo(1);
    }
}
