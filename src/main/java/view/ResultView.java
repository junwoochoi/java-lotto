package view;

import dto.LotteriesDto;
import dto.ResultsDto;
import lotto.Money;
import lotto.Prize;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultView {
    private static final Integer ZERO = 0;

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

        final Map<Integer, Integer> countOfPrize = resultsDto.getCountOfPrize();
        final Set<Integer> correctCounts = countOfPrize.keySet();

        printCountOfPrize(countOfPrize, correctCounts);

        printYield(resultsDto);
    }

    private static void printHeader() {
        System.out.println("당첨 통계");
        System.out.println("============");
    }

    private void printCountOfPrize(Map<Integer, Integer> countOfPrize, Set<Integer> correctCounts) {
        correctCounts.stream()
                .filter(count -> count > 2)
                .sorted()
                .forEach(correctCount -> printEach(countOfPrize, correctCount));
    }

    private void printEach(Map<Integer, Integer> countOfPrize, Integer correctCount) {
        final Money prizeMoney = getPrizeMoney(correctCount);
        System.out.println(correctCount + "개 일치 (" + prizeMoney.wonString() + ") - " + countOfPrize.getOrDefault(correctCount, ZERO) + "개");
    }

    private void printYield(ResultsDto resultsDto) {
        System.out.println("총 수익률은 " + resultsDto.getYield() + "% 입니다. ");
    }

    private Money getPrizeMoney(Integer correctCount) {
        final Prize prize = Prize.ofMatchCount(correctCount);
        final Money prizeMoney = prize.getRewardMoney();
        if (prizeMoney == null || prize == Prize.NONE) {
            throw new IllegalArgumentException("상금이 없는 당첨 갯수 입니다.");
        }
        return prizeMoney;
    }


}
