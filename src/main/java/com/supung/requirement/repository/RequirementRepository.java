package com.supung.requirement.repository;

import com.supung.requirement.model.domain.Requirement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequirementRepository extends JpaRepository<Requirement, Long> {
    List<Requirement> findAllByOrphanageId(long orphanageId);
}
