package lotto;

import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import static lotto.Lottery.LOTTO_MAX_NUMBER;
import static lotto.Lottery.LOTTO_MIN_NUMBER;

public class WinningNumbers {

    private List<Integer> winningNumbers;

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
        if (numbers.stream().noneMatch(this::isInValidRange)) {
            throw new IllegalArgumentException("로또의 숫자 범위가 올바르지 않습니다.");
        }
    }


    private boolean isInValidRange(Integer number) {
        return number >= LOTTO_MIN_NUMBER && number <= LOTTO_MAX_NUMBER;
    }

    public List<Integer> getNumbers() {
        return new ArrayList<>(winningNumbers);
    }

}
