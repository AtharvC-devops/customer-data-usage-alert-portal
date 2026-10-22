package edu.devops.usageportal;

import edu.devops.usageportal.model.UsageRecord;
import edu.devops.usageportal.model.UsageStatus;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

class UsageStatusTest {
    @Test
    void classifiesUsageBelowWarningThresholdAsNormal() {
        UsageRecord record = record("79.9", "100");
        assertThat(record.getStatus()).isEqualTo(UsageStatus.NORMAL);
        assertThat(record.getUsagePercent()).isEqualByComparingTo("79.9");
    }

    @Test
    void classifiesUsageAtWarningThresholdAsWarning() {
        assertThat(record("80", "100").getStatus()).isEqualTo(UsageStatus.WARNING);
    }

    @Test
    void classifiesUsageAtQuotaAsExceeded() {
        assertThat(record("100", "100").getStatus()).isEqualTo(UsageStatus.EXCEEDED);
    }

    @Test
    void usesConfiguredWarningThreshold() {
        UsageRecord record = record("74.9", "100");
        record.setWarningThresholdPercent(new BigDecimal("75"));
        assertThat(record.getStatus()).isEqualTo(UsageStatus.NORMAL);

        record.setUsedGb(new BigDecimal("75"));
        assertThat(record.getStatus()).isEqualTo(UsageStatus.WARNING);
    }

    private UsageRecord record(String used, String quota) {
        return new UsageRecord("CUS-TEST", "Test Customer", "Test Plan", "2026-10",
                new BigDecimal(used), new BigDecimal(quota), "Test");
    }
}
