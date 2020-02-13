package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoMachine {
    public static final int LOTTO_MIN_NUMBER = 1;
    public static final int LOTTO_MAX_NUMBER = 45;
    public static final int LOTTO_NUMBER_COUNT = 6;
    private List<Integer> numbers = IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    public Lotto createLotto() {
        Collections.shuffle(numbers);
        final List<Integer> lottoNumbers = numbers.subList(0, LOTTO_NUMBER_COUNT);

        return Lotto.of(lottoNumbers);
    }
}
