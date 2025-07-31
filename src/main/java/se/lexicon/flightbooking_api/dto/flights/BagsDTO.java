package se.lexicon.flightbooking_api.dto.flights;

import lombok.*;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BagsDTO {
    private Integer carryOn;
    private Integer checked;
}
