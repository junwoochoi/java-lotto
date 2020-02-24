package dto;

import lotto.Prize;
import lotto.Results;

import java.util.EnumMap;
import java.util.Map;

public class ResultsDto {

    private Map<Prize, Integer> countOfPrize;
    private int yield;

    public ResultsDto(Results results) {
        assert results != null;
        this.yield = results.getYield();
        this.countOfPrize = results.getCountOfPrize();
    }

    public Map<Prize, Integer> getCountOfPrize() {
        return new EnumMap<>(countOfPrize);
    }

    public int getYield() {
        return yield;
    }
}
