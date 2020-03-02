package lotto;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static lotto.Lottery.LOTTO_NUMBER_COUNT;
import static lotto.LottoNo.LOTTO_MAX_NUMBER;
import static lotto.LottoNo.LOTTO_MIN_NUMBER;

public class RandomLottoGenerator implements LottoGenerator {
    private List<Integer> numbers = IntStream.rangeClosed(LOTTO_MIN_NUMBER, LOTTO_MAX_NUMBER)
            .boxed()
            .collect(Collectors.toList());

    @Override
    public Lottery generate() {
        Collections.shuffle(numbers);
        final List<Integer> lottoNumbers = numbers.subList(0, LOTTO_NUMBER_COUNT);

        return Lottery.of(lottoNumbers);
    }
}
