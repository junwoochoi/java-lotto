package lotto;

import java.util.HashMap;
import java.util.Map;

public class Prizes {
    private static Map<Integer, Money> prizes = new HashMap<>();

    static {
        prizes.put(3, Money.of(5000));
        prizes.put(4, Money.of(50000));
        prizes.put(5, Money.of(1500000));
        prizes.put(6, Money.of(2000000000));
    }

    public static Map<Integer, Money> getPrizes() {
        return new HashMap<>(prizes);
    }

}
