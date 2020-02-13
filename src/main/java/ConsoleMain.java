import lotto.Lotto;
import lotto.LottoShop;
import lotto.Money;
import view.InputView;

import java.util.List;

public class ConsoleMain {
    public static void main(String[] args) {
        //TODO 스텝1 구현
        InputView inputView = new InputView();
        final Money inputMoney = inputView.inputMoney();

        LottoShop lottoShop = new LottoShop();
        List<Lotto> lotteries = lottoShop.buy(inputMoney);


    }
}
