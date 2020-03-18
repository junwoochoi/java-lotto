package lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

public class ManualLottoGenerator implements LottoGenerator {

    private List<Integer> inputNumbers;

    public ManualLottoGenerator(List<Integer> inputNumbers) {
        validateNumbers(inputNumbers);
        this.inputNumbers = Collections.unmodifiableList(inputNumbers);
    }

    private void validateNumbers(List<Integer> inputNumbers) {
        if (Objects.isNull(inputNumbers)) {
            throw new IllegalArgumentException("input numbers cannot be null");
        }
        if (inputNumbers.size() != Lottery.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("Lotto number count is invalid");
        }
    }

    @Override
    public Lottery generate() {
        return Lottery.of(inputNumbers);
    }
}
