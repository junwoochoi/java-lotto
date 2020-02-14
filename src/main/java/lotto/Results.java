package lotto;

import spark.utils.CollectionUtils;

import java.util.*;

public class Results {
    public static final int ZERO = 0;
    private Map<Integer, Integer> results = new HashMap<>();
    private int yield;

    private Results(List<Result> results, Money inputMoney) {
        validate(results, inputMoney);
        initResultsWithParameter(results);
        initYield(inputMoney);
    }

    private void initYield(Money inputMoney) {
        final Set<Integer> correctCounts = results.keySet();
        final Map<Integer, Money> prizes = Prizes.getPrizes();
        Money totalEarnMoney = Money.zero();

        for (Integer correctCount : correctCounts) {
            final Integer numberOfPrize = this.results.getOrDefault(correctCount, ZERO);
            final Money moneyPerPrize = prizes.getOrDefault(correctCount, Money.zero());

            final Money prizeMoney = Money.multiply(moneyPerPrize, numberOfPrize);
            totalEarnMoney = Money.sum(prizeMoney, totalEarnMoney);
        }


        this.yield = Money.calculateYield(totalEarnMoney, inputMoney);
    }

    private void initResultsWithParameter(List<Result> results) {
        final ArrayList<Result> parameterResults = new ArrayList<>(results);
        for (Result parameterResult : parameterResults) {
            final int correctCount = parameterResult.getCorrectCount();
            final Integer beforeCount = this.results.getOrDefault(correctCount, ZERO);
            this.results.put(correctCount, beforeCount + 1);

        }
    }

    private void validate(List<Result> results, Money inputMoney) {
        assert inputMoney != null;
        if (CollectionUtils.isEmpty(results)) {
            throw new IllegalArgumentException("results cannot be empty");
        }

    }

    public static Results of(List<Result> results, Money inputMoney) {
        return new Results(results, inputMoney);
    }

    public Map<Integer, Integer> getCountOfPrize() {
        return new HashMap<>(results);
    }

    public int getYield() {
        return yield;
    }
}
