package com.supung.requirement.model.domain;

import com.supung.requirement.model.dto.RequirementOutputDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Requirement {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private Long orphanageId;
    private String title;
    private String description;
    private Long quantity;

    public RequirementOutputDTO viewAsRequirementOutputDTO() {
        return RequirementOutputDTO.builder()
                .id(id).orphanageId(orphanageId)
                .title(title).description(description)
                .quantity(quantity).build();
    }
}
