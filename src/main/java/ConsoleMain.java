import dto.LotteriesDto;
import dto.ResultsDto;
import lotto.*;
import view.InputView;
import view.ResultView;

import java.util.ArrayList;
import java.util.List;

public class ConsoleMain {
    public static void main(String[] args) {
        InputView inputView = new InputView();
        ResultView resultView = new ResultView();
        final Money inputMoney = inputView.inputMoney();

        List<Lottery> lotteries = buyLotto(inputMoney);
        resultView.printLotteries(new LotteriesDto(lotteries));
        final WinningNumbers winningNumbers = inputView.inputWinningNumbers();

        final Results results = checkResults(lotteries, winningNumbers, inputMoney);
        resultView.printLottoResults(new ResultsDto(results));
    }

    private static Results checkResults(List<Lottery> lotteries, WinningNumbers winningNumbers, Money inputMoney) {
        List<Result> resultList = new ArrayList<>();
        for (Lottery lottery : lotteries) {
            final Result result = lottery.checkResult(winningNumbers);
            resultList.add(result);
        }

        return Results.of(resultList, inputMoney);
    }

    private static List<Lottery> buyLotto(Money inputMoney) {
        LottoShop lottoShop = new LottoShop();
        return lottoShop.sell(inputMoney);
    }
}
