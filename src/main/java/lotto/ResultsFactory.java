package lotto;

import java.util.ArrayList;
import java.util.EnumMap;
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
            final Prize prize = result.getPrize();
            totalEarnMoney = Money.sum(prize.getRewardMoney(), totalEarnMoney);
        }

        return Money.calculateYield(totalEarnMoney, inputMoney);
    }

    private static Map<Prize, Integer> buildResultMap(List<Result> results) {
        final ArrayList<Result> parameterResults = new ArrayList<>(results);
        final Map<Prize, Integer> resultMap = new EnumMap<>(Prize.class);

        for (Result parameterResult : parameterResults) {
            final Prize prize = parameterResult.getPrize();
            final Integer beforeCount = resultMap.getOrDefault(prize, ZERO);
            resultMap.put(prize, beforeCount + 1);
        }
        
        return resultMap;
    }
}
