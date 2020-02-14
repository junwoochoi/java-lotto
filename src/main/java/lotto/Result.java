package lotto;

import java.util.Map;

public class Result {

    private int correctCount;
    private Money wonMoney;


    public Result(int correctCount) {
        assert correctCount >= 0;
        this.correctCount = correctCount;
        final Map<Integer, Money> prizes = Prizes.getPrizes();
        this.wonMoney = prizes.getOrDefault(correctCount, Money.zero());
    }

    public int getCorrectCount() {
        return this.correctCount;
    }


}
