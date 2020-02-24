package lotto;

import java.util.Arrays;

public enum Prize {
    FIRST_PRIZE(6, false, Money.of(2000000000)),
    SECOND_PRIZE(5, true, Money.of(3000000)),
    THIRD_PRIZE(5, false, Money.of(1500000)),
    FOURTH_PRIZE(4, false, Money.of(50000)),
    FIFTH_PRIZE(3, false, Money.of(5000)),
    NONE(0, false, Money.ZERO);


    private int matchCount;
    private boolean isBonusMatch;
    private Money rewardMoney;

    Prize(int matchCount, boolean isBonusMatch, Money rewardMoney) {
        this.matchCount = matchCount;
        this.isBonusMatch = isBonusMatch;
        this.rewardMoney = rewardMoney;
    }

    public static Prize ofMatchCount(int matchCount, boolean isBonusMatch) {
        return Arrays.stream(values())
                .filter(prize -> prize.matchCount == matchCount && prize.isBonusMatch == isBonusMatch)
                .findAny()
                .orElse(NONE);
    }

    public Money getRewardMoney() {
        return rewardMoney;
    }

    public int getMatchCount() {
        return matchCount;
    }

    public boolean isBonusMatch() {
        return isBonusMatch;
    }
}
