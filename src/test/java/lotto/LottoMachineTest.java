package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;


@DisplayName("로또 기계 테스트")
class LottoMachineTest {

    @Test
    @DisplayName("로또 객체를 반환한다.")
    void createLottoNumbers() {
        //given
        LottoMachine lottoMachine = new LottoMachine();

        //when
        Lotto lotto = lottoMachine.createLotto();

        //then
        assertThat(lotto).isNotNull();
    }
}