package lotto;

import java.util.List;
import java.util.Objects;
import java.util.TreeSet;

public class WinningNumbers extends Lottery {

    private LottoNo bonusNumber;

    protected WinningNumbers(List<Integer> numbers, Integer bonusNumber) {
        super(new TreeSet<>(numbers));
        validateBonusNumber(bonusNumber);
        this.bonusNumber = LottoNo.of(bonusNumber);
    }

    public static WinningNumbers of(List<Integer> numbers, Integer bonusNumber) {
        return new WinningNumbers(numbers, bonusNumber);
    }

    private void validateBonusNumber(Integer bonusNumber) {
        if (Objects.isNull(bonusNumber)) {
            throw new IllegalArgumentException("bonus number can not be null or empty");
        }

        if (this.numbers.contains(LottoNo.of(bonusNumber))) {
            throw new IllegalArgumentException("bonus number is already in");
        }
    }

    public LottoNo getBonusNumber() {
        return bonusNumber;
    }

}
