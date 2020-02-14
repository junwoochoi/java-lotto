package lotto;

import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Results {
    public static final int ZERO = 0;
    private Map<Integer, Integer> results = new HashMap<>();
    private int yield;

    private Results(List<Result> results, Money inputMoney) {
        validate(results, inputMoney);
        initResultsWithParameter(results);
        initYield(inputMoney, results);
    }

    private void validate(List<Result> results, Money inputMoney) {
        assert inputMoney != null;
        if (CollectionUtils.isEmpty(results)) {
            throw new IllegalArgumentException("results cannot be empty");
        }
    }

    private void initResultsWithParameter(List<Result> results) {
        final ArrayList<Result> parameterResults = new ArrayList<>(results);
        for (Result parameterResult : parameterResults) {
            final int correctCount = parameterResult.getCorrectCount();
            final Integer beforeCount = this.results.getOrDefault(correctCount, ZERO);
            this.results.put(correctCount, beforeCount + 1);

        }
    }

    private void initYield(Money inputMoney, List<Result> results) {
        Money totalEarnMoney = Money.zero();
        for (Result result : results) {
            totalEarnMoney = Money.sum(result.getWonMoney(), totalEarnMoney);
        }

        this.yield = Money.calculateYield(totalEarnMoney, inputMoney);
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
