package com.supung.requirement.model.dto;

import com.supung.requirement.model.domain.Requirement;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class RequirementInputDTO {
    private Long orphanageId;
    private String title;
    private String description;
    private Long quantity;

    public Requirement viewAsRequirement() {
        return Requirement.builder()
                .orphanageId(orphanageId).title(title).description(description)
                .quantity(quantity).build();
    }
}
