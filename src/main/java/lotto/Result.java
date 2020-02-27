package lotto;

public class Result {

    private Prize prize;


    private Result(int correctCount, boolean isBonusMatch) {
        assert correctCount >= 0;

        this.prize = Prize.ofMatch(correctCount, isBonusMatch);
    }

    public static Result of(int correctCount, boolean isBonusMatch) {
        return new Result(correctCount, isBonusMatch);
    }

    public Prize getPrize() {
        return prize;
    }
}

