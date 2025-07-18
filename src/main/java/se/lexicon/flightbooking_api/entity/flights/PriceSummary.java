package se.lexicon.flightbooking_api.entity.flights;

import lombok.Getter;

import java.util.List;

@Getter
public class PriceSummary {
    private Integer current;
    private List<OperationValue> low;
    private List<OperationValue> typical;
    private List<OperationValue> high;
}

