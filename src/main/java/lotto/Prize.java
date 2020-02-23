package lotto;

import java.util.Collections;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toMap;

public enum Prize {
    FIRST_PRIZE(6, Money.of(2000000000)),
    SECOND_PRIZE(5, Money.of(1500000)),
    THIRD_PRIZE(4, Money.of(50000)),
    FOURTH_PRIZE(3, Money.of(5000)),
    NONE(0, Money.ZERO);

    private static final Map<Integer, Prize> matchCounts =
            Collections.unmodifiableMap(
                    Stream.of(values())
                            .collect(toMap(o -> o.matchCount, Function.identity())));
    private int matchCount;
    private Money rewardMoney;

    Prize(int matchCount, Money rewardMoney) {
        this.matchCount = matchCount;
        this.rewardMoney = rewardMoney;
    }

    public static Prize ofMatchCount(int matchCount) {
        return matchCounts.getOrDefault(matchCount, NONE);
    }

    public Money getRewardMoney() {
        return rewardMoney;
    }
}
