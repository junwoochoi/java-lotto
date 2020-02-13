package view;

import lotto.Money;

import java.util.Scanner;

public class InputView {
    private Scanner scanner = new Scanner(System.in);

    public Money inputMoney() {
        System.out.println("구입 금액을 입력해주세요.");
        return Money.of(scanner.nextLong());
    }
}
