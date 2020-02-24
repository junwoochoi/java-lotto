package view;

import dto.LotteriesDto;
import dto.ResultsDto;
import lotto.Money;
import lotto.Prize;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultView {
    public void printLotteries(LotteriesDto lotteriesDto) {
        final List<Set<Integer>> lotteryNumbers = lotteriesDto.getLotteries();
        printLotteriesCount(lotteryNumbers.size());
        printAllLotteriesNumber(lotteryNumbers);
    }

    private void printLotteriesCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    private void printAllLotteriesNumber(List<Set<Integer>> lotteryNumbers) {
        for (Set<Integer> lotteryNumber : lotteryNumbers) {
            System.out.println(lotteryNumber);
        }
    }

    public void printLottoResults(ResultsDto resultsDto) {
        printHeader();

        final Map<Prize, Integer> countOfPrize = resultsDto.getCountOfPrize();
        final Set<Prize> prizeSet = countOfPrize.keySet();

        printCountOfPrize(countOfPrize, prizeSet);

        printYield(resultsDto);
    }

    private static void printHeader() {
        System.out.println("당첨 통계");
        System.out.println("============");
    }

    private void printCountOfPrize(Map<Prize, Integer> countOfPrize, Set<Prize> prizes) {
        prizes.stream()
                .filter(prize -> prize != Prize.NONE)
                .forEach(prize -> printEach(countOfPrize, prize));
    }

    private void printEach(Map<Prize, Integer> countOfPrize, Prize prize) {
        final Money prizeMoney = prize.getRewardMoney();
        final String bonusString = prize.isBonusMatch() ? "보너스볼 일치" : "";
        System.out.println(String.format("%s개 일치, %s (%s) - %s개", prize.getMatchCount(), bonusString, prizeMoney.wonString(), countOfPrize.get(prize)));
    }

    private void printYield(ResultsDto resultsDto) {
        System.out.println("총 수익률은 " + resultsDto.getYield() + "% 입니다. ");
    }

}
