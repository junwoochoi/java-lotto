package lotto;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static lotto.LottoMachine.LOTTO_NUMBER_COUNT;

public class Lotto {
    private Set<Integer> numbers;

    private Lotto() {
    }

    private Lotto(Set<Integer> lottoNumbers) {
        validateNumbers(lottoNumbers);
        this.numbers = lottoNumbers;
    }

    private void validateNumbers(Set<Integer> lottoNumbers) {
        if (lottoNumbers.size() != LOTTO_NUMBER_COUNT) {
            throw new IllegalArgumentException("로또의 갯수가 잘못되었습니다.");
        }
    }

    public static Lotto of(List<Integer> lottoNumbers) {
        final HashSet<Integer> distinctNumbers = new HashSet<>(lottoNumbers);
        if (distinctNumbers.size() != lottoNumbers.size()) {
            throw new IllegalArgumentException("로또에 중복된 숫자가 들어올 수 없습니다.");
        }

        return new Lotto(distinctNumbers);
    }

    public int correctCount(List<Integer> winningNumbers) {
        final Set<Integer> myNumbers = new HashSet<>(this.numbers);
        myNumbers.addAll(winningNumbers);
        final int combinedSize = myNumbers.size();

        return LOTTO_NUMBER_COUNT + (LOTTO_NUMBER_COUNT - combinedSize);
    }
}
