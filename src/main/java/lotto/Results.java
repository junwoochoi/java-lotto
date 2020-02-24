package lotto;

import java.util.EnumMap;
import java.util.Map;

public class Results {
    public static final int ZERO = 0;
    private Map<Prize, Integer> results;
    private int yield;

    private Results(Map<Prize, Integer> results, int yield, Money inputMoney) {
        validate(results, inputMoney);
        this.yield = yield;
        this.results = results;
    }

    private void validate(Map<Prize, Integer> results, Money inputMoney) {
        assert inputMoney != null && results != null;
        if (results.isEmpty()) {
            throw new IllegalArgumentException("results cannot be empty");
        }
    }


    public static Results of(Map<Prize, Integer> results, int yield, Money inputMoney) {
        return new Results(results, yield, inputMoney);
    }

    public Map<Prize, Integer> getCountOfPrize() {
        return new EnumMap<>(results);
    }

    public int getYield() {
        return yield;
    }
}
