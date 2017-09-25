package pl.datapreparation;

import pl.translator.Word;

import javax.persistence.OneToOne;

public class ResultWord {
    @OneToOne
    private Word word;
    private int amount;

    public ResultWord(Word word, int amount) {
        this.word = word;
        this.amount = amount;
    }

    public Word getWord() {
        return word;
    }

    public void setWord(Word word) {
        this.word = word;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
}
