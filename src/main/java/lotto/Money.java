package lotto;

public class Money {
    private long amount;
    private long MIN_MONEY = 1000;

    private Money(long amount) {
        validate(amount);
        this.amount = amount;
    }

    private void validate(long amount) {
        if (amount < MIN_MONEY) {
            throw new IllegalArgumentException("돈은 로또의 최소 금액인 1,000원을 넘어야 합니다.");
        }
    }

    public static Money of(long amount) {
        return new Money(amount);
    }

    public int availableLottoCount() {
        return Math.toIntExact(amount / MIN_MONEY);
    }
}
