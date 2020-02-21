package lotto;

import spark.utils.CollectionUtils;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Lottery {

    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final long LOTTO_PRICE = 1000;

    private Set<Integer> numbers;

    private Lottery(Set<Integer> lottoNumbers) {
        validateNumbers(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    public static Lottery of(List<Integer> lottoNumbers) {
        final HashSet<Integer> distinctNumbers = new HashSet<>(lottoNumbers);

        return new Lottery(distinctNumbers);
    }

    public Set<Integer> getNumbers() {
        return new HashSet<>(numbers);
    }

    public Result checkResult(WinningNumbers winningNumbers) {
        return Result.of(correctCount(winningNumbers));
    }

    private void validateNumbers(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또의 갯수가 잘못되었거나 중복된 숫자가 있습니다.");
        }
        if (CollectionUtils.isEmpty(lottoNumbers)) {
            throw new IllegalArgumentException("로또 번호 입력이 비어있거나 없습니다.");
        }
        if (lottoNumbers.stream().noneMatch(this::isInValidRange)) {
            throw new IllegalArgumentException("로또의 숫자 범위가 올바르지 않습니다.");
        }

    }

    private boolean isInValidRange(Integer number) {
        return number >= LOTTO_MIN_NUMBER && number <= LOTTO_MAX_NUMBER;
    }

    private int correctCount(WinningNumbers winningNumbers) {
        final Set<Integer> myNumbers = new HashSet<>(this.numbers);
        myNumbers.addAll(winningNumbers.getNumbers());
        final int combinedSize = myNumbers.size();


        return LOTTO_NUMBER_COUNT + (LOTTO_NUMBER_COUNT - combinedSize);
    }

}
