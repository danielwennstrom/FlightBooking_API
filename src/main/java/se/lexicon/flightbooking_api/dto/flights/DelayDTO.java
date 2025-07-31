package se.lexicon.flightbooking_api.dto.flights;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class DelayDTO {
    private boolean values;
    private int text;
}
