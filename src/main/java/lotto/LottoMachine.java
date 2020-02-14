package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.Lottery.*;

public class LottoMachine {

    private List<Integer> numbers = IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    public Lottery createLotto() {
        Collections.shuffle(numbers);
        final List<Integer> lottoNumbers = numbers.subList(0, LOTTO_NUMBER_COUNT);

        return Lottery.of(lottoNumbers);
    }
}
