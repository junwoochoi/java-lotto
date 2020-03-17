package view;

import lotto.Money;
import lotto.WinningNumbers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.parseInt;
import static java.lang.Long.parseLong;
import static java.util.stream.Collectors.toList;

public class InputView {
    public static final String COMMA = ",";
    private Scanner scanner = new Scanner(System.in);

    public Money inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return Money.of(parseLong(getNextLine()));
    }

    public int inputManualGenerateLottoCount() {
        System.out.println("수동으로 구매할 로또 수를 입력해주세요.");
        return Integer.parseInt(getNextLine());
    }

    public List<List<Integer>> inputManualLottos() {
        final int manualLottoCount = inputManualGenerateLottoCount();
        if (manualLottoCount < 1) {
            return new ArrayList<>();
        }
        System.out.println("수동으로 구매할 로또 번호를 입력해 주세요.");
        List<List<Integer>> lottoNumbers = new ArrayList<>();
        for (int i = 0; i < manualLottoCount; i++) {
            final List<Integer> inputNumbers = Arrays.stream(getNextLine().split(COMMA))
                    .map(String::trim)
                    .map(Integer::parseInt)
                    .collect(toList());
            lottoNumbers.add(inputNumbers);
        }
        return lottoNumbers;
    }

    public WinningNumbers inputWinningNumbers() {
        System.out.println("지난 주 당첨 번호를 입력해 주세요.");

        final String inputLine = getNextLine();
        final String[] splitNumbers = inputLine.split(COMMA);

        System.out.println("보너스 볼을 입력해 주세요.");
        final Integer bonusNumber = parseInt(getNextLine());

        final List<Integer> inputWinningNumbers = Arrays.stream(splitNumbers)
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(toList());
        return WinningNumbers.of(inputWinningNumbers, bonusNumber);
    }

    private String getNextLine() {
        return scanner.nextLine();
    }
}
