package se.lexicon.flightbooking_api.dto.flights;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DurationDTO {
    private int raw;
    private String text;
}
