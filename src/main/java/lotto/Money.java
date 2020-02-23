package lotto;

public class Money {
    public static final Money ZERO = Money.of(0);
    public static final long HUNDRED = 100L;
    public static final int ZERO_AMOUNT = 0;
    private long amount;

    private Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(long amount) {
        if (amount < ZERO_AMOUNT) {
            throw new IllegalArgumentException("돈은 0원을 넘어야 합니다.");
        }
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public int availableLottoCount() {
        return Math.toIntExact(amount / Lottery.LOTTO_PRICE);
    }

    public String wonString() {
        return this.amount + "원";
    }

    public static Money multiply(Money money, Integer number) {
        assert money != null && number >= 0;
        return of(money.amount * number);
    }

    public static Money sum(Money x, Money y) {
        assert x != null && y != null;
        return of(x.amount + y.amount);
    }

    public static int calculateYield(Money totalEarnMoney, Money beforeMoney) {
        assert totalEarnMoney != null && beforeMoney != null;
        final double divide = ((double) totalEarnMoney.amount / (double) beforeMoney.amount);
        return (int) (divide * HUNDRED);
    }
}
