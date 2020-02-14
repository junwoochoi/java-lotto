package dto;

import lotto.Results;

import java.util.HashMap;
import java.util.Map;

public class ResultsDto {

    private Map<Integer, Integer> countOfPrize;
    private int yield;

    public ResultsDto(Results results) {
        assert results != null;
        this.yield = results.getYield();
        this.countOfPrize = results.getCountOfPrize();
    }

    public Map<Integer, Integer> getCountOfPrize() {
        return new HashMap<>(countOfPrize);
    }

    public int getYield() {
        return yield;
    }
}
