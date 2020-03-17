package lotto;

import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class WinningNumbers {

    private List<LottoNo> winningNumbers;
    private LottoNo bonusNumber;

    private WinningNumbers(List<Integer> numbers, Integer bonusNumber) {
        validateParams(numbers, bonusNumber);
        this.bonusNumber = LottoNo.of(bonusNumber);
        this.winningNumbers = numbers.stream()
                .map(LottoNo::of)
                .collect(Collectors.toList());
    }

    private void validateParams(List<Integer> numbers, Integer bonusNumber) {
        validateNumbers(numbers);
        validateBonusNumber(bonusNumber, numbers);
    }

    public static WinningNumbers of(List<Integer> numbers, Integer bonusNumber) {
        return new WinningNumbers(numbers, bonusNumber);
    }

    private void validateNumbers(List<Integer> numbers) {
        if (CollectionUtils.isEmpty(numbers)) {
            throw new IllegalArgumentException("winning numbers cannot be empty");
        }

        if (numbers.size() != Lottery.LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("winning numbers size should be LOTTO_NUMBER_COUNT");
        }

        if (numbers.stream().distinct().count() != numbers.size()) {
            throw new IllegalArgumentException("winning numbers can not have duplicate numbers");
        }
    }

    private void validateBonusNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        if (Objects.isNull(bonusNumber)) {
            throw new IllegalArgumentException("bonus number can not be null or empty");
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("bonus number is already in");
        }
    }

    public List<LottoNo> getNumbers() {
        return new ArrayList<>(winningNumbers);
    }

    public LottoNo getBonusNumber() {
        return bonusNumber;
    }

}
