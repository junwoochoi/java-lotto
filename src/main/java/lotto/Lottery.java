package lotto;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class Lottery {

    public static final int LOTTO_NUMBER_COUNT = 6;
    public static final long LOTTO_PRICE = 1000;

    private Set<LottoNo> numbers;

    private Lottery(Set<Integer> lottoNumbers) {
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
        return new TreeSet<>(numbers);
    }

    public Result checkResult(WinningNumbers winningNumbers) {
        return Result.of(correctCount(winningNumbers), this.numbers.contains(winningNumbers.getBonusNumber()));
    }

    private void validateNumbers(Set<Integer> lottoNumbers) {
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

}
