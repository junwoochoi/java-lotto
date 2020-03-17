package lotto;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class LottoShop {
    private LottoShop() {

    }

    public static List<Lottery> sell(Money inputMoney, List<List<Integer>> manualLottoInputs) {
        //Spring 쓸때는 Assert.notNull() 사용 가능
        Objects.requireNonNull(inputMoney);
        Objects.requireNonNull(manualLottoInputs);
        final LottoMachine lottoMachine = new LottoMachine();

        final List<Lottery> manualLotteries = createManualLotteries(manualLottoInputs, lottoMachine);
        final int randomLottoCount = inputMoney.availableLottoCount() - manualLotteries.size();

        final List<Lottery> randomLotteries = createRandomLotteries(lottoMachine, randomLottoCount);

        return Stream.of(manualLotteries, randomLotteries)
                .flatMap(Collection::stream)
                .collect(Collectors.toList());
    }

    private static List<Lottery> createRandomLotteries(LottoMachine lottoMachine, int randomLottoCount) {
        final List<Lottery> lotteries = new ArrayList<>();
        final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        for (int i = randomLottoCount; i > 0; i--) {
            final Lottery lotto = lottoMachine.createLotto(randomLottoGenerator);
            lotteries.add(lotto);
        }
        return lotteries;
    }

    private static List<Lottery> createManualLotteries(List<List<Integer>> manualLottoInputs, LottoMachine lottoMachine) {
        return manualLottoInputs.stream()
                .map(inputs -> lottoMachine.createLotto(new ManualLottoGenerator(inputs)))
                .collect(Collectors.toList());
    }

}
