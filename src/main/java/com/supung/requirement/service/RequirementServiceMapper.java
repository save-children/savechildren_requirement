package com.supung.requirement.service;

import com.supung.requirement.model.dto.DonationOutputDTO;
import com.supung.requirement.service.client.donation.DonationFeignClient;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RequirementServiceMapper {

    private final DonationFeignClient donationFeignClient;

    public RequirementServiceMapper(DonationFeignClient donationFeignClient) {
        this.donationFeignClient = donationFeignClient;
    }


    @Retry(name = "getDonationByRequirementId", fallbackMethod = "getDonationByRequirementIdFallback")
    public DonationOutputDTO getDonationByRequirementId(long requirementId) {
        log.info("Calling donation service with {}", requirementId);
        return donationFeignClient.getDonationByRequirementId(requirementId).getBody();
    }

    public DonationOutputDTO getDonationByRequirementIdFallback(long requirementId, Throwable t) {
        log.info("Called getDonationByRequirementIdFallback with {}", requirementId);
        return null;
    }

}
