package lotto;

public class Money {
    private static final long ZERO = 0;
    public static final int HUNDRED = 100;
    private long amount;

    private Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(long amount) {
        if (amount < ZERO) {
            throw new IllegalArgumentException("돈은 0원을 넘어야 합니다.");
        }
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public static Money zero() {
        return new Money(ZERO);
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

    public static Money sum(Money money1, Money money2) {
        assert money1 != null && money2 != null;
        return of(money1.amount + money2.amount);
    }

    public static int calculateYield(Money totalEarnMoney, Money beforeMoney) {
        return (int) (totalEarnMoney.amount / beforeMoney.amount * HUNDRED);
    }
}
