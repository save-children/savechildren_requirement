package com.supung.requirement.controller;

import com.supung.requirement.model.dto.RequirementInputDTO;
import com.supung.requirement.model.dto.RequirementOutputDTO;
import com.supung.requirement.service.RequirementService;
import com.supung.requirement.service.RequirementServiceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/v1/requirements")
public class RequirementController {

    private final RequirementService requirementService;

    private final RequirementServiceMapper requirementServiceMapper;

    public RequirementController(final RequirementService requirementService,
                                 final RequirementServiceMapper requirementServiceMapper) {
        this.requirementService = requirementService;
        this.requirementServiceMapper = requirementServiceMapper;
    }

    @PostMapping
    public ResponseEntity<RequirementOutputDTO> save(@RequestBody RequirementInputDTO requirementInputDTO) {
        return ResponseEntity.ok(requirementService.save(requirementInputDTO));
    }

    @GetMapping
    public ResponseEntity<List<RequirementOutputDTO>> findAll() {
        return ResponseEntity.ok(requirementService.findAll());
    }

    @GetMapping("getByOrphanageId/{orphanageId}")
    public ResponseEntity<List<RequirementOutputDTO>> findAllByOrphanageId(@RequestHeader("correlation-id") String correlationId,
                                                                           @PathVariable("orphanageId") long orphanageId) {
        log.info("Finding requirements by orphanage id : {}, correlationId : {}", orphanageId, correlationId);
        return ResponseEntity.ok(requirementService.findAllByOrphanageId(orphanageId));
    }

    @GetMapping("{id}")
    public ResponseEntity<RequirementOutputDTO> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(requirementService.findById(id));
    }

    @GetMapping("{id}/withDonation")
    public RequirementOutputDTO findByIdWithDonation(@PathVariable("id") long id) {
        RequirementOutputDTO requirementOutputDTO = requirementService.findById(id);
        requirementOutputDTO.setDonationOutputDTO(requirementServiceMapper.getDonationByRequirementId(id));
        return requirementOutputDTO;
    }
}
