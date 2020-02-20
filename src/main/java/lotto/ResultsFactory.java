package lotto;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lotto.Results.ZERO;

public class ResultsFactory {
    public static Results create(List<Result> results, Money money) {
        return Results.of(buildResultMap(results), calculateYield(money, results), money);
    }

    private static int calculateYield(Money inputMoney, List<Result> results) {
        Money totalEarnMoney = Money.ZERO;
        for (Result result : results) {
            totalEarnMoney = Money.sum(result.getWonMoney(), totalEarnMoney);
        }

        return Money.calculateYield(totalEarnMoney, inputMoney);
    }

    private static Map<Integer, Integer> buildResultMap(List<Result> results) {
        final ArrayList<Result> parameterResults = new ArrayList<>(results);
        final Map<Integer, Integer> resultMap = new HashMap<>();

        for (Result parameterResult : parameterResults) {
            final int correctCount = parameterResult.getCorrectCount();
            final Integer beforeCount = resultMap.getOrDefault(correctCount, ZERO);
            resultMap.put(correctCount, beforeCount + 1);
        }
        
        return resultMap;
    }
}
