package com.supung.requirement.service.impl;

import com.supung.requirement.exception.ResourceNotFoundException;
import com.supung.requirement.model.domain.Requirement;
import com.supung.requirement.model.dto.RequirementInputDTO;
import com.supung.requirement.model.dto.RequirementOutputDTO;
import com.supung.requirement.repository.RequirementRepository;
import com.supung.requirement.service.RequirementService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RequirementServiceImpl implements RequirementService {

    private final RequirementRepository requirementRepository;

    public RequirementServiceImpl(final RequirementRepository requirementRepository) {
        this.requirementRepository = requirementRepository;
    }

    @Override
    public RequirementOutputDTO save(RequirementInputDTO requirementInputDTO) {
        return requirementRepository.save(requirementInputDTO.viewAsRequirement())
                .viewAsRequirementOutputDTO();
    }

    @Override
    public List<RequirementOutputDTO> findAll() {
        return requirementRepository.findAll().stream()
                .map(Requirement::viewAsRequirementOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public List<RequirementOutputDTO> findAllByOrphanageId(long orphanageId) {
        return requirementRepository.findAllByOrphanageId(orphanageId).stream()
                .map(Requirement::viewAsRequirementOutputDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RequirementOutputDTO findById(long id) {
        return requirementRepository.findById(id)
                .map(Requirement::viewAsRequirementOutputDTO)
                .orElseThrow(() -> new ResourceNotFoundException("No Requirement for given id "));
    }
}
