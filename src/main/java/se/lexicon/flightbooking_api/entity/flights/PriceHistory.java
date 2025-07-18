package se.lexicon.flightbooking_api.entity.flights;

import lombok.Getter;

import java.util.List;

@Getter
public class PriceHistory {
    private PriceSummary summary;
    private List<PriceHistoryEntry> history;
}
