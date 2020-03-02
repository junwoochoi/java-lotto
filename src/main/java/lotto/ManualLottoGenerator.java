package lotto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

public class ManualLottoGenerator implements LottoGenerator {

    private final Scanner scanner = new Scanner(System.in);
    private List<Integer> inputNumbers;

    public ManualLottoGenerator(List<Integer> inputNumbers) {
        validateNumbes(inputNumbers);
        this.inputNumbers = Collections.unmodifiableList(inputNumbers);
    }

    private void validateNumbes(List<Integer> inputNumbers) {
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
