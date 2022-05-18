package com.supung.requirement.admin;

import com.supung.requirement.config.serviceconfig.Properties;
import com.supung.requirement.config.serviceconfig.ServiceConfig;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/admin")
public class AdminController {

    private ServiceConfig serviceConfig;

    public AdminController(ServiceConfig serviceConfig) {
        this.serviceConfig = serviceConfig;
    }

    @GetMapping("/properties")
    public Properties getAllProperties() {
        return Properties.builder()
                .displayName(serviceConfig.getDisplayName())
                .activeBranches(serviceConfig.getActiveBranches())
                .mailDetails(serviceConfig.getMailDetails())
                .build();
    }
}
