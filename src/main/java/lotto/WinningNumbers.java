package lotto;

import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static lotto.Lottery.LOTTO_MAX_NUMBER;
import static lotto.Lottery.LOTTO_MIN_NUMBER;

public class WinningNumbers {

    private List<Integer> winningNumbers;
    private Integer bonusNumber;

    private WinningNumbers(List<Integer> numbers, Integer bonusNumber) {
        validateParams(numbers, bonusNumber);
        winningNumbers = new ArrayList<>(numbers);
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
        if (numbers.stream().anyMatch(this::isExceedRange)) {
            throw new IllegalArgumentException("로또의 숫자 범위가 올바르지 않습니다.");
        }
    }

    private void validateBonusNumber(Integer bonusNumber, List<Integer> winningNumbers) {
        if (Objects.isNull(bonusNumber)) {
            throw new IllegalArgumentException("bonus number can not be null or empty");
        }

        if (isExceedRange(bonusNumber)) {
            throw new IllegalArgumentException("bonus number exceed number range");
        }

        if (winningNumbers.contains(bonusNumber)) {
            throw new IllegalArgumentException("보너스 숫자가 이미 당첨 숫자에 포함되어 있습니다.");
        }
    }


    private boolean isExceedRange(Integer number) {
        return number < LOTTO_MIN_NUMBER || number > LOTTO_MAX_NUMBER;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(winningNumbers);
    }

    public Integer getBonusNumber() {
        return bonusNumber;
    }

}
