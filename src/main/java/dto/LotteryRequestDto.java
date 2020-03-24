package dto;

import lotto.Money;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static view.InputView.COMMA;

public class LotteryRequestDto {

    public static final String LINE_BREAK_REGEX = "\\r?\\n";
    private Money inputMoney;
    private List<List<Integer>> manualLottoInputs;

    public LotteryRequestDto(String inputMoney, String manualLottoInputs) {
        this.inputMoney = Money.of(Long.parseLong(inputMoney));
        this.manualLottoInputs = Arrays.stream(manualLottoInputs.split(LINE_BREAK_REGEX))
                .map(LotteryRequestDto::parseLine)
                .collect(Collectors.toList());
    }

    private static List<Integer> parseLine(String eachLine) {
        return Arrays.stream(eachLine.split(COMMA))
                .map(String::trim)
                .mapToInt(Integer::parseInt)
                .boxed()
                .collect(Collectors.toList());
    }

    public Money getInputMoney() {
        return inputMoney;
    }

    public List<List<Integer>> getManualLottoInputs() {
        return Collections.unmodifiableList(manualLottoInputs);
    }
}
