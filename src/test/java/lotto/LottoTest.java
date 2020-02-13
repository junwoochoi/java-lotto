package lotto;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

@DisplayName("로또 객체 테스트")
class LottoTest {

    @Test
    @DisplayName("로또 정상 생성 테스트")
    void create() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 3, 6, 30, 45, 20);

        //when
        final Lotto lotto = Lotto.of(numbers);

        //then
        assertThat(lotto).isNotNull();
    }

    @Test
    @DisplayName("로또 중복값 생성 테스트")
    void duplicateNumber() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 1, 6, 30, 45, 20);


        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Lotto.of(numbers))
                .withMessage("로또에 중복된 숫자가 들어올 수 없습니다.");
    }

    @Test
    @DisplayName("로또 모자란 숫자 갯수 생성 테스트")
    void checkNumber() {
        //given
        final ArrayList<Integer> numbers = Lists.newArrayList(1, 1, 6, 30, 45, 20);


        //then
        assertThatIllegalArgumentException()
                //when
                .isThrownBy(() -> Lotto.of(numbers))
                .withMessage("로또에 중복된 숫자가 들어올 수 없습니다.");
    }
}