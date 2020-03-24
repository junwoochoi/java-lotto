package lotto;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

import static view.InputView.COMMA;


public class Lottery {

    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final long LOTTO_PRICE = 1000;

    protected Set<LottoNo> numbers;

    protected Lottery(Set<Integer> lottoNumbers) {
        validateNumbers(lottoNumbers);
        this.numbers = lottoNumbers.stream()
                .map(LottoNo::of)
                .collect(Collectors.toSet());
    }

    public static Lottery of(List<Integer> lottoNumbers) {
        final Set<Integer> distinctNumbers = new TreeSet<>(lottoNumbers);

        return new Lottery(distinctNumbers);
    }

    public Set<LottoNo> getNumbers() {
        return Collections.unmodifiableNavigableSet(new TreeSet<>(numbers));
    }

    public Result checkResult(WinningNumbers winningNumbers) {
        return Result.of(correctCount(winningNumbers), this.numbers.contains(winningNumbers.getBonusNumber()));
    }

    private void validateNumbers(Set<Integer> lottoNumbers) {
        if (lottoNumbers == null) {
            throw new IllegalArgumentException("입력값이 NULL입니다.");
        }
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또의 갯수가 잘못되었거나 중복된 숫자가 있습니다.");
        }

    }


    private int correctCount(WinningNumbers winningNumbers) {
        final Set<LottoNo> myNumbers = new TreeSet<>(this.numbers);
        myNumbers.addAll(winningNumbers.getNumbers());
        final int combinedSize = myNumbers.size();


        return LOTTO_NUMBER_COUNT + (LOTTO_NUMBER_COUNT - combinedSize);
    }

    @Override
    public String toString() {
        return "["
                + numbers.stream()
                .map(String::valueOf)
                .collect(Collectors.joining(COMMA))
                + "]";
    }
}
