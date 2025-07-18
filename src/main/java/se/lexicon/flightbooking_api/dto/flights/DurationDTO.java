package se.lexicon.flightbooking_api.dto.flights;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DurationDTO {
    private int raw;
    private String text;
}
