package edu.devops.usageportal.service;

import edu.devops.usageportal.model.UsageRecord;
import edu.devops.usageportal.model.UsageStatus;
import edu.devops.usageportal.repository.UsageRecordRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;

@Service
@Transactional
public class UsageService {
    private final UsageRecordRepository repository;

    public UsageService(UsageRecordRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public List<UsageRecord> findAll(String query) {
        Sort sort = Sort.by(Sort.Direction.DESC, "recordedAt");
        if (query == null || query.isBlank()) {
            return repository.findAll(sort);
        }
        String value = query.trim();
        return repository.findByCustomerIdContainingIgnoreCaseOrCustomerNameContainingIgnoreCaseOrPlanNameContainingIgnoreCase(
                value, value, value, sort);
    }

    @Transactional(readOnly = true)
    public List<UsageRecord> findAlerts() {
        return repository.findAll(Sort.by(Sort.Direction.DESC, "recordedAt")).stream()
                .filter(record -> record.getStatus() != UsageStatus.NORMAL)
                .toList();
    }

    @Transactional(readOnly = true)
    public UsageRecord findById(long id) {
        return repository.findById(id)
                .orElseThrow(() -> new UsageNotFoundException(id));
    }

    public UsageRecord create(UsageRecord record) {
        record.setId(null);
        record.setRecordedAt(null);
        return repository.save(record);
    }

    public UsageRecord simulate(String customerId, String customerName, BigDecimal usedGb, BigDecimal quotaGb) {
        return create(new UsageRecord(customerId, customerName, "Simulation", "Current cycle",
                usedGb, quotaGb, "Simulation"));
    }

    @Transactional(readOnly = true)
    public PortalSummary summarize(List<UsageRecord> records) {
        if (records.isEmpty()) {
            return new PortalSummary(0, 0, BigDecimal.ZERO);
        }
        BigDecimal average = records.stream()
                .map(UsageRecord::getUsagePercent)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(records.size()), 1, RoundingMode.HALF_UP);
        long customers = records.stream().map(UsageRecord::getCustomerId).distinct().count();
        long alerts = records.stream().filter(record -> record.getStatus() != UsageStatus.NORMAL).count();
        return new PortalSummary(customers, alerts, average);
    }
}
