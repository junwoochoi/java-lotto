package view;

import lotto.Money;
import lotto.WinningNumbers;

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
