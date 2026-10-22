package edu.devops.usageportal;

import edu.devops.usageportal.model.UsageRecord;
import edu.devops.usageportal.model.UsageStatus;
import edu.devops.usageportal.repository.UsageRecordRepository;
import edu.devops.usageportal.service.PortalSummary;
import edu.devops.usageportal.service.UsageService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.lang.reflect.Proxy;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Sort;

import static org.assertj.core.api.Assertions.assertThat;
class UsageServiceTest {
    private UsageRecordRepository repository;
    private UsageService service;
    private List<UsageRecord> allRecords = List.of();
    private List<UsageRecord> queryRecords = List.of();
    private Object[] queryArguments;

    @BeforeEach
    void setUp() {
        repository = (UsageRecordRepository) Proxy.newProxyInstance(
                UsageRecordRepository.class.getClassLoader(),
                new Class<?>[]{UsageRecordRepository.class},
                (proxy, method, arguments) -> {
                    if (method.getName().equals("findAll")) {
                        return allRecords;
                    }
                    if (method.getName().startsWith("findByCustomerIdContaining")) {
                        queryArguments = arguments;
                        return queryRecords;
                    }
                    if (method.getName().equals("findById")) {
                        return Optional.empty();
                    }
                    return null;
                });
        service = new UsageService(repository);
    }

    @Test
    void searchTrimsQueryAndLooksAcrossCustomerNameIdAndPlan() {
        List<UsageRecord> expected = List.of(record("CUS-1003", "Kabir Joshi", "Business 200", "208.5", "200"));
        queryRecords = expected;

        List<UsageRecord> result = service.findAll("  CUS-1003  ");

        assertThat(result).isEqualTo(expected);
        assertThat(queryArguments).hasSize(4);
        assertThat(queryArguments[0]).isEqualTo("CUS-1003");
        assertThat(queryArguments[1]).isEqualTo("CUS-1003");
        assertThat(queryArguments[2]).isEqualTo("CUS-1003");
        assertThat(queryArguments[3]).isEqualTo(Sort.by(Sort.Direction.DESC, "recordedAt"));
    }

    @Test
    void summaryCountsDistinctCustomersAndWarningOrExceededRecords() {
        List<UsageRecord> records = List.of(
                record("CUS-1", "Normal", "Home", "50", "100"),
                record("CUS-1", "Warning", "Home", "80", "100"),
                record("CUS-2", "Exceeded", "Home", "150", "100"));

        PortalSummary summary = service.summarize(records);

        assertThat(summary.customerCount()).isEqualTo(2);
        assertThat(summary.alertCount()).isEqualTo(2);
        assertThat(summary.averageUsagePercent()).isEqualByComparingTo("93.3");
        assertThat(records).extracting(UsageRecord::getStatus)
                .containsExactly(UsageStatus.NORMAL, UsageStatus.WARNING, UsageStatus.EXCEEDED);
    }

    private UsageRecord record(String customerId, String name, String plan, String used, String quota) {
        return new UsageRecord(customerId, name, plan, "2026-10",
                new BigDecimal(used), new BigDecimal(quota), "test");
    }
}
