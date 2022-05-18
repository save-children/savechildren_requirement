package com.supung.requirement.service;

import com.supung.requirement.model.dto.RequirementInputDTO;
import com.supung.requirement.model.dto.RequirementOutputDTO;

import java.util.List;

public interface RequirementService {
    RequirementOutputDTO save(RequirementInputDTO requirementInputDTO);

    List<RequirementOutputDTO> findAll();

    List<RequirementOutputDTO> findAllByOrphanageId(long orphanageId);

    RequirementOutputDTO findById(long id);
}
