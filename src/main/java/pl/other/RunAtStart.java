package pl.other;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.translator.Word;
import pl.translator.WordRepository;
import pl.user.*;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * Created by maciek on 8/17/17.
 */
@Component
public class RunAtStart {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final AccountActivationService accountActivationService;
    private final WordRepository wordRepository;

    @Autowired
    public RunAtStart(PasswordEncoder passwordEncoder, UserRepository userRepository, AccountActivationService accountActivationService, WordRepository wordRepository) throws FileNotFoundException {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
        this.accountActivationService = accountActivationService;
        this.wordRepository = wordRepository;
    }

    @PostConstruct
    public void runAtStart() throws FileNotFoundException {
        System.out.println("I'am alive");

//        User user = new User("maciek", passwordEncoder.encode("maciek"), "Robovlogg@gmail.com");
//        userRepository.save(user);
//        197413    
//        accountActivationService.sendActivationMail(user);
//        userRepository.save(user);
//        User user = new User("maciek", passwordEncoder.encode("test"), "robovlogg@gmail.com");
//
//        System.out.println("I'am alive 2");
//
//        Word word1 = new Word("mother", "matka");
//        wordRepository.save(word1);
//        Word word2 = new Word("father", "ojciec");
//        wordRepository.save(word2);
//        System.out.println("I'am alive 3");
//
//        List<Word> words = new LinkedList<>();
//        words.add(word1);
//        words.add(word2);
//
//        user.setWords(words);
//        System.out.println("I'am alive 4");
//
//        userRepository.save(user);
//
//        System.out.println("I'am alive 5");
//
//        User maciek = userRepository.findByUsername("maciek");
//        System.out.println("I'am alive 6");
//
//        List<Word> words1 = maciek.getWords();
//        System.out.println(words1);
/////home/maciek/DATA/WordsDataset/Audio/full.txt

//        File file = new File("/home/maciek/DATA/WordsDataset/translate/FULL.txt");
//        Scanner scanner = new Scanner(file, "UTF-8");
//        String text = scanner.useDelimiter("\\A").next();
//        scanner.close();
//        int i = 0;
//
//        List<String> words = Arrays.asList(text.split("\n"));
//        for (String word : words) {
//            String[] englishandpolish = word.split("/");
//            String english = englishandpolish[0];
//            String polish = englishandpolish[1];
//            System.out.println();
////            System.out.println("English = " + english);
//  //          System.out.println("Polish = " + polish + i);
//            i = i + 1;
//            Word wordd = new Word(english,polish);
//            wordRepository.save(wordd);
//            System.out.println(i);
//        try {
//            File file = new File("/home/maciek/DATA/WordsDataset/Audio/part2.txt");
//            Scanner scanner = new Scanner(file, "UTF-8");
//            String text = scanner.useDelimiter("\\A").next();
//            scanner.close();
//            int i = 0;
//
//            List<String> words = Arrays.asList(text.split("\n"));
//            for (String word : words) {
//                String[] englishandpolish = word.split("}");
//                String english = englishandpolish[0];
//                String audioUk = englishandpolish[1];
//                String audioUS = englishandpolish[2];
////                System.out.println("Word "+english+"uk "+audioUk+" us "+audioUS);
////                System.out.println();
////                System.out.println("English = " + english);
////                System.out.println("Link = " + audio);
//                i = i + 1;
//                Word byEnglishWord = wordRepository.findByEnglishWord(english);
//                byEnglishWord.setUrlAudioUk(audioUk);
//                byEnglishWord.setUrlAudioUs(audioUS);
//                wordRepository.save(byEnglishWord);
//                System.out.println(i);
//            }
//        }
//        catch(Exception e) {
//            System.out.println("COS SIE ZJEBALO I TO POWAZNIE");
//        }
    }
//    }
}
