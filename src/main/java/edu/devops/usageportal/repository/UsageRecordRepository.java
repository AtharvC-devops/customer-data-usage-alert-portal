package edu.devops.usageportal.repository;

import edu.devops.usageportal.model.UsageRecord;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UsageRecordRepository extends JpaRepository<UsageRecord, Long> {
    List<UsageRecord> findByCustomerIdContainingIgnoreCaseOrCustomerNameContainingIgnoreCaseOrPlanNameContainingIgnoreCase(
            String customerId, String customerName, String planName, Sort sort);
}
