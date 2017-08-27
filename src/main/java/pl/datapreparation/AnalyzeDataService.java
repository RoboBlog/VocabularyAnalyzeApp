package pl.datapreparation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.translator.TranslationService;
import pl.translator.Word;
import pl.translator.WordRepository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 8/16/17.
 */
@Service
public class AnalyzeDataService {

    private final TranslationService translationService;
    private final WordRepository wordRepository;
    @Autowired
    public AnalyzeDataService(TranslationService translationService, WordRepository wordRepository) {
        this.translationService = translationService;
        this.wordRepository = wordRepository;
    }
//
    public Map<Word, Integer> dataPreparation(String body){
        String bodyLowercase = body.toLowerCase().replaceAll("[^a-żA-Ż]", " ");
        List<String> words = Arrays.asList(bodyLowercase.split("\\s+"));
        Map<Word, Integer> wordsMap = new HashMap<>();//variable name

        for(String word : words){

            if(word.matches(".*\\w.*")) //REGEX
            {
                System.out.println(word);

                Word word1 = wordRepository.findByEnglishWord(word);
                Integer count = wordsMap.get(word1);
                if(word1!=null) {
                    wordsMap.put(word1, (count == null)?1:count+1);
                }
            }
        }

        Map<Word, Integer> result = wordsMap.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return result;
    }











//    public Map<String, Integer> dataPreparation(String body){
//        String bodyLowercase = body.toLowerCase().replaceAll("[^a-żA-Ż]", " ");
//        List<String> words = Arrays.asList(bodyLowercase.split("\\s+"));
//
//        Map<String, Integer> map = new HashMap<String, Integer>();
//
//        int i =0;
//
//        for(String word : words){
//            Integer count = map.get(word);
//            if(word.matches(".*\\w.*"))
//            map.put(word,(count==null)?1:count+1);
////            System.out.println(word);
////            if(word.length()==1){
////                System.out.print(word);
////            }
////            else
////                System.out.println(translationService.translateEnglishToPolish(word));
////            i++;
////            System.out.print(" " + i);
//
//        }
//
////        Map<Word, Integer> result
//
//
//        Map<String, Integer> result = map.entrySet().stream()
//                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
//                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
//        return result;
//    }



}
