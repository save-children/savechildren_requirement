package com.supung.requirement.controller;

import com.supung.requirement.model.dto.RequirementInputDTO;
import com.supung.requirement.model.dto.RequirementOutputDTO;
import com.supung.requirement.service.RequirementService;
import com.supung.requirement.service.RequirementServiceMapper;
import io.micrometer.core.annotation.Timed;
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
    @Timed(value = "save.time", description = "Time taken to add a requirement")
    public ResponseEntity<RequirementOutputDTO> save(@RequestBody RequirementInputDTO requirementInputDTO) {
        return ResponseEntity.ok(requirementService.save(requirementInputDTO));
    }

    @GetMapping
    @Timed(value = "findAll.time", description = "Time taken to get all requirements")
    public ResponseEntity<List<RequirementOutputDTO>> findAll() {
        return ResponseEntity.ok(requirementService.findAll());
    }

    @GetMapping("getByOrphanageId/{orphanageId}")
    @Timed(value = "findAllByOrphanageId.time", description = "Time taken to get all requirements by orphanage id")
    public ResponseEntity<List<RequirementOutputDTO>> findAllByOrphanageId(@RequestHeader("correlation-id") String correlationId,
                                                                           @PathVariable("orphanageId") long orphanageId) {
        log.info("Finding requirements by orphanage id : {}, correlationId : {}", orphanageId, correlationId);
        return ResponseEntity.ok(requirementService.findAllByOrphanageId(orphanageId));
    }

    @GetMapping("{id}")
    @Timed(value = "findById.time", description = "Time taken to get a requirement by id")
    public ResponseEntity<RequirementOutputDTO> findById(@PathVariable("id") long id) {
        return ResponseEntity.ok(requirementService.findById(id));
    }

    @GetMapping("{id}/withDonation")
    @Timed(value = "findByIdWithDonation.time", description = "Time taken to get a requirement by id with donation")
    public RequirementOutputDTO findByIdWithDonation(@PathVariable("id") long id) {
        RequirementOutputDTO requirementOutputDTO = requirementService.findById(id);
        requirementOutputDTO.setDonationOutputDTO(requirementServiceMapper.getDonationByRequirementId(id));
        return requirementOutputDTO;
    }
}
