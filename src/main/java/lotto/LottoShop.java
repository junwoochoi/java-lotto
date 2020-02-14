package lotto;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class LottoShop {
    private LottoMachine lottoMachine = new LottoMachine();

    public List<Lottery> buy(Money inputMoney) {
        final int lottoCount = inputMoney.availableLottoCount();

        return IntStream.range(0, lottoCount)
                .mapToObj(value -> lottoMachine.createLotto())
                .collect(Collectors.toList());
    }
}
