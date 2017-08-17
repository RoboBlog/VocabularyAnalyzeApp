package pl;

import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by maciek on 8/16/17.
 */
@Service
public class AnalyzeDataService {

    public Map<String, Integer> dataPreparation(String body){
        String bodyLowercase = body.toLowerCase().replaceAll("[^a-żA-Ż]", " ");
        List<String> words = Arrays.asList(bodyLowercase.split("\\s+"));

        Map<String, Integer> map = new HashMap<String, Integer>();


        for(String word : words){
            Integer count = map.get(word);
            if(word.matches(".*\\w.*"))
            map.put(word,(count==null)?1:count+1);
        }


        Map<String, Integer> result = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                        (oldValue, newValue) -> oldValue, LinkedHashMap::new));
        return result;
    }



}
