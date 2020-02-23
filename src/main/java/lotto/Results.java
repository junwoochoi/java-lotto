package lotto;

import java.util.HashMap;
import java.util.Map;

public class Results {
    public static final int ZERO = 0;
    private Map<Integer, Integer> results;
    private int yield;

    private Results(Map<Integer, Integer> results, int yield, Money inputMoney) {
        validate(results, inputMoney);
        this.yield = yield;
        this.results = results;
    }

    private void validate(Map<Integer, Integer> results, Money inputMoney) {
        assert inputMoney != null && results != null;
        if (results.isEmpty()) {
            throw new IllegalArgumentException("results cannot be empty");
        }
    }


    public static Results of(Map<Integer, Integer> results, int yield, Money inputMoney) {
        return new Results(results, yield, inputMoney);
    }

    public Map<Integer, Integer> getCountOfPrize() {
        return new HashMap<>(results);
    }

    public int getYield() {
        return yield;
    }
}
