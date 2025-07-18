package se.lexicon.flightbooking_api.entity.flights;

import lombok.Getter;

import java.math.BigInteger;

@Getter
public class PriceHistoryEntry {
    private BigInteger time;
    private Double value;
}
