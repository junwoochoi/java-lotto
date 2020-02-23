package lotto;

public class Result {

    private int correctCount;
    private Money wonMoney;


    private Result(int correctCount) {
        assert correctCount >= 0;
        this.correctCount = correctCount;
        final Prize prize = Prize.ofMatchCount(correctCount);
        this.wonMoney = prize.getRewardMoney();
    }

    public static Result of(int correctCount) {
        return new Result(correctCount);
    }

    public int getCorrectCount() {
        return this.correctCount;
    }

    public Money getWonMoney() {
        return wonMoney;
    }
}
