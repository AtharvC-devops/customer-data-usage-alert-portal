package edu.devops.usageportal;

import edu.devops.usageportal.service.UsageService;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class UsageServiceThresholdTest {
    @Test
    void acceptsAThresholdWithinTheSupportedRange() {
        UsageService service = new UsageService(null, new BigDecimal("65"));
        assertThat(service.getWarningThresholdPercent()).isEqualByComparingTo("65");
    }

    @Test
    void rejectsThresholdsOutsideTheSupportedRange() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new UsageService(null, new BigDecimal("-1")));
        assertThatIllegalArgumentException().isThrownBy(
                () -> new UsageService(null, new BigDecimal("101")));
    }

    @Test
    void rejectsAMissingThreshold() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new UsageService(null, null));
    }
}