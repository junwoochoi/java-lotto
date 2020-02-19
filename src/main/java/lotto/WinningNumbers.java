package lotto;

import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

public class WinningNumbers {

    List<Integer> winningNumbers;

    private WinningNumbers(List<Integer> numbers) {
        validateNumbers(numbers);
        winningNumbers = new ArrayList<>(numbers);
    }

    public static WinningNumbers of(List<Integer> numbers) {
        return new WinningNumbers(numbers);
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

    public List<Integer> getNumbers() {
        return new ArrayList<>(winningNumbers);
    }

}
