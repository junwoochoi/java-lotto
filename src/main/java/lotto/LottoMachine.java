package lotto;

public class LottoMachine {

    public Lottery createLotto(LottoGenerator lottoGenerator) {
        return lottoGenerator.generate();
    }

}
