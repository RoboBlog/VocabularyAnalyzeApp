package pl.translator;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Word {
    @Id
    @GeneratedValue
    private Long id;
    private String englishWord;
    private String polishWord;
    private String urlAudioUk;
    private String urlAudioUs;


    public Word(String englishWord, String polishWord, String urlAudioUk, String urlAudioUs) {
        this.englishWord = englishWord;
        this.polishWord = polishWord;
        this.urlAudioUk = urlAudioUk;
        this.urlAudioUs = urlAudioUs;
    }

    public Word(String englishWord, String polishWord) {
        this.englishWord = englishWord;
        this.polishWord = polishWord;
    }

    public Word(long id, String englishWord, String polishWord) {
        this.id=id;
        this.englishWord = englishWord;
        this.polishWord = polishWord;
    }



    public Word() {
    }

    public String getUrlAudioUk() {
        return urlAudioUk;
    }

    public void setUrlAudioUk(String urlAudioUk) {
        this.urlAudioUk = urlAudioUk;
    }

    public String getUrlAudioUs() {
        return urlAudioUs;
    }

    public void setUrlAudioUs(String urlAudioUs) {
        this.urlAudioUs = urlAudioUs;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEnglishWord() {
        return englishWord;
    }

    public void setEnglishWord(String englishWord) {
        this.englishWord = englishWord;
    }

    public String getPolishWord() {
        return polishWord;
    }

    public void setPolishWord(String polishWord) {
        this.polishWord = polishWord;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", englishWord='" + englishWord + '\'' +
                ", polishWord='" + polishWord + '\'' +
                ", urlAudioUk='" + urlAudioUk + '\'' +
                ", urlAudioUs='" + urlAudioUs + '\'' +
                '}';
    }
}
