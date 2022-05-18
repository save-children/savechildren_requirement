package com.supung.requirement.service.client.donation;

import com.supung.requirement.model.dto.DonationOutputDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@FeignClient("DONATION")
public interface DonationFeignClient {

    @RequestMapping(method = RequestMethod.GET, value = "/api/v1/donations/findByRequirement/{requirementId}", consumes = "application/json")
    ResponseEntity<DonationOutputDTO> getDonationByRequirementId(@PathVariable("requirementId") long requirementId);

//    @GetMapping("/api/v1/donations/findByRequirement/{requirementId}")
//    ResponseEntity<DonationOutputDTO> getDonationByRequirementId(@PathVariable("requirementId") long requirementId);
}
