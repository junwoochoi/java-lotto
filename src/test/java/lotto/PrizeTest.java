package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class PrizeTest {

    //given
    @CsvSource({"6,false,FIRST_PRIZE", "5,true,SECOND_PRIZE", "5,false,THIRD_PRIZE", "4,false,FOURTH_PRIZE", "3,false,FIFTH_PRIZE", "1,false,NONE"})
    @ParameterizedTest
    @DisplayName("Prize 생성 테스트")
    void testCreate(int matchCount, boolean isBonusMatch, Prize expected) {

        //when
        final Prize prize = Prize.ofMatch(matchCount, isBonusMatch);

        //then
        assertThat(prize).isEqualTo(expected);
    }

}