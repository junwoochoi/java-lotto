package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Lottery {

    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final long LOTTO_PRICE = 1000;

    private Set<Integer> numbers;

    private Lottery() {
    }

    private Lottery(Set<Integer> lottoNumbers) {
        validateNumbers(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private void validateNumbers(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또의 갯수가 잘못되었습니다.");
        }
    }

    public static Lottery of(List<Integer> lottoNumbers) {
        final HashSet<Integer> distinctNumbers = new HashSet<>(lottoNumbers);
        if (distinctNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또에 중복된 숫자가 들어올 수 없습니다.");
        }

        return new Lottery(distinctNumbers);
    }

    private int correctCount(WinningNumbers winningNumbers) {
        final Set<Integer> myNumbers = new HashSet<>(this.numbers);
        myNumbers.addAll(winningNumbers.getNumbers());
        final int combinedSize = myNumbers.size();

        return LOTTO_NUMBER_COUNT + (LOTTO_NUMBER_COUNT - combinedSize);
    }

    public Result checkResult(WinningNumbers winningNumbers) {
        return new Result(correctCount(winningNumbers));
    }

    public Set<Integer> getNumbers() {
        return new HashSet<>(numbers);
    }

}
