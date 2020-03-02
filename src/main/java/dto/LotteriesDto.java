package dto;

import lotto.Lottery;
import lotto.LottoNo;
import spark.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class LotteriesDto {
    private List<Set<LottoNo>> lotteries;

    public LotteriesDto(List<Lottery> lotteries) {
        if (CollectionUtils.isEmpty(lotteries)) {
            throw new IllegalArgumentException();
        }

        this.lotteries = lotteries.stream()
                .map(Lottery::getNumbers)
                .collect(Collectors.toList());
    }

    public List<Set<LottoNo>> getLotteries() {
        return new ArrayList<>(lotteries);
    }

}
