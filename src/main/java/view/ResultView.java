package view;

import dto.LotteriesDto;
import dto.ResultsDto;
import lotto.Money;
import lotto.Prizes;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class ResultView {
    private static final Integer ZERO = 0;

    public void printLotteries(LotteriesDto lotteriesDto) {
        final List<Set<Integer>> lotteryNumbers = lotteriesDto.getLotteries();
        printLotteriesCount(lotteryNumbers.size());
        printAllLotteriesNubmer(lotteryNumbers);
    }

    private void printLotteriesCount(int count) {
        System.out.println(count + "개를 구매했습니다.");
    }

    private void printAllLotteriesNubmer(List<Set<Integer>> lotteryNumbers) {
        for (Set<Integer> lotteryNumber : lotteryNumbers) {
            System.out.println(lotteryNumber);
        }
    }

    public void printLottoResults(ResultsDto resultsDto) {
        printHeader();

        final Map<Integer, Integer> countOfPrize = resultsDto.getCountOfPrize();
        final Set<Integer> correctCounts = countOfPrize.keySet();
        final Map<Integer, Money> prizes = Prizes.getPrizes();

        printCountOfPrize(countOfPrize, correctCounts, prizes);

        printYield(resultsDto);
    }

    private static void printHeader() {
        System.out.println("당첨 통계");
        System.out.println("============");
    }

    private void printCountOfPrize(Map<Integer, Integer> countOfPrize, Set<Integer> correctCounts, Map<Integer, Money> prizes) {
        correctCounts.stream()
                .filter(count -> count > 2)
                .sorted()
                .forEach(correctCount -> {
                    final Money prizeMoney = getPrizeMoney(prizes, correctCount);
                    System.out.println(correctCount + "개 일치 (" + prizeMoney.wonString() + ") - " + countOfPrize.getOrDefault(correctCount, ZERO) + "개");
                });
    }

    private void printYield(ResultsDto resultsDto) {
        System.out.println("총 수익률은 " + resultsDto.getYield() + "% 입니다. ");
    }

    private Money getPrizeMoney(Map<Integer, Money> prizes, Integer correctCount) {
        final Money prizeMoney = prizes.get(correctCount);
        if (prizeMoney == null) {
            throw new IllegalArgumentException("상금이 없는 당첨 갯수 입니다.");
        }
        return prizeMoney;
    }


}
