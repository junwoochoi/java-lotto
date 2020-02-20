package lotto;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ResultTest {


    @Test
    @DisplayName("생성 테스트")
    void testCreate() {
        final Result result = Result.of(5);

        assertThat(result).isNotNull();
        assertThat(result.getWonMoney()).isEqualTo(Prize.ofMatchCount(5).getRewardMoney());
    }

}