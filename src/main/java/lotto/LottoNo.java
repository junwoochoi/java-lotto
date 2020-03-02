package lotto;

import java.util.Objects;


public class LottoNo implements Comparable<LottoNo> {
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    private int number;
    private LottoNo(int number) {
        validate(number);
        this.number = number;
    }

    public static LottoNo of(int number) {
        return new LottoNo(number);
    }


    private void validate(int number) {
        if (!isValid(number)) {
            throw new IllegalArgumentException("로또의 숫자 범위가 올바르지 않습니다.");
        }
    }

    private boolean isValid(int number) {
        return number >= LOTTO_MIN_NUMBER && number <= LOTTO_MAX_NUMBER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LottoNo lottoNo = (LottoNo) o;
        return number == lottoNo.number;
    }

    @Override
    public int hashCode() {
        return Objects.hash(number);
    }

    @Override
    public String toString() {
        return String.valueOf(number);
    }

    @Override
    public int compareTo(LottoNo o) {
        return Integer.compare(this.number, o.number);
    }
}
