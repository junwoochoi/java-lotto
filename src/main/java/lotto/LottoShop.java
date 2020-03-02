package lotto;

import java.util.ArrayList;
import java.util.List;

public class LottoShop {
    private LottoShop() {

    }

    public static List<Lottery> sell(Money inputMoney, List<List<Integer>> manualLottoInputs) {
        assert inputMoney != null && manualLottoInputs != null;
        final LottoMachine lottoMachine = new LottoMachine();
        final List<Lottery> lotteries = new ArrayList<>();
        int lottoCount = inputMoney.availableLottoCount();

        for (List<Integer> inputs : manualLottoInputs) {
            final Lottery lotto = lottoMachine.createLotto(new ManualLottoGenerator(inputs));
            lotteries.add(lotto);
            lottoCount--;
        }

        final RandomLottoGenerator randomLottoGenerator = new RandomLottoGenerator();
        for (int i = lottoCount; i > 0; i--) {
            final Lottery lotto = lottoMachine.createLotto(randomLottoGenerator);
            lotteries.add(lotto);
        }
        return lotteries;
    }

}
