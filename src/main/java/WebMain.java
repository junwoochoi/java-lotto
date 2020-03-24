import dto.LotteryRequestDto;
import handlebars.CustomHandlebarsTemplateEngine;
import lotto.*;
import spark.ModelAndView;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static spark.Spark.*;
import static view.InputView.COMMA;

public class WebMain {
    private static final String LOTTERIES = "lotteries";
    private static final String INPUT_MONEY = "inputMoney";
    public static final String WINNING_NUMBER = "winningNumber";
    public static final String BONUS_NUMBER = "bonusNumber";
    public static final String YIELD = "yield";
    public static final String RESULTS = "results";

    public static void main(String[] args) {
        port(8080);

        get("/", (req, res) -> {
            req.session(true);
            return render("/index.html");
        });
        post("/buyLotto", (req, res) -> {
            final LotteryRequestDto requestDto = new LotteryRequestDto(req.queryParams("inputMoney"), req.queryParams("manualNumber"));

            final List<Lottery> lotteries = LottoShop.sell(requestDto.getInputMoney(), requestDto.getManualLottoInputs());

            req.session().attribute(LOTTERIES, lotteries);
            req.session().attribute(INPUT_MONEY, requestDto.getInputMoney());

            final Map<String, Object> responseParams = new HashMap<>();
            responseParams.put(LOTTERIES, lotteries.stream()
                    .map(Lottery::toString)
                    .collect(Collectors.toList()));
            return render(responseParams, "/show.html");
        });

        post("/matchLotto", (req, res) -> {
            final List<Lottery> lotteries = req.session().attribute(LOTTERIES);
            final Money inputMoney = req.session().attribute(INPUT_MONEY);

            req.session().attribute(LOTTERIES, lotteries);

            final String[] winningNumber = req.queryParams(WINNING_NUMBER)
                    .trim()
                    .split(COMMA);
            final String inputBonusNumber = req.queryParams(BONUS_NUMBER).trim();

            final WinningNumbers winningNumbers = WinningNumbers.of(
                    Arrays.stream(winningNumber)
                            .map(String::trim)
                            .map(Integer::parseInt)
                            .collect(Collectors.toList())
                    , Integer.parseInt(inputBonusNumber)
            );

            final Results results = ResultsFactory.create(lotteries, winningNumbers, inputMoney);
            final Map<String, Object> responseParams = new HashMap<>();
            responseParams.put(YIELD, results.getYield());
            for (Prize prize : Prize.values()) {
                responseParams.put(prize.name(), results.getCountOfPrize().getOrDefault(prize, 0));
            }

            req.session().invalidate();
            return render(responseParams, "/result.html");
        });
    }

    private static String render(String templatePath) {
        return render(null, templatePath);
    }

    public static String render(Map<String, Object> model, String templatePath) {
        return new CustomHandlebarsTemplateEngine().render(new ModelAndView(model, templatePath));
    }
}
