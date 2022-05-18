package com.supung.requirement.model.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DonationOutputDTO {
    private Long id;
    private Long requirementId;
    private Long donarId;
}
