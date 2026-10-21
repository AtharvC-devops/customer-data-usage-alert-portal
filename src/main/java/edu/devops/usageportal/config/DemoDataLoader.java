package edu.devops.usageportal.config;

import edu.devops.usageportal.model.UsageRecord;
import edu.devops.usageportal.repository.UsageRecordRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.util.List;

@Configuration
public class DemoDataLoader {
    @Bean
    ApplicationRunner loadDemoData(UsageRecordRepository repository) {
        return args -> {
            if (repository.count() > 0) {
                return;
            }
            repository.saveAll(List.of(
                    new UsageRecord("CUS-1001", "Aarav Mehta", "Home Plus", "2026-10",
                            new BigDecimal("84.2"), new BigDecimal("100.0"), "Network feed"),
                    new UsageRecord("CUS-1002", "Mira Shah", "Mobile Max", "2026-10",
                            new BigDecimal("12.6"), new BigDecimal("50.0"), "Network feed"),
                    new UsageRecord("CUS-1003", "Kabir Joshi", "Business 200", "2026-10",
                            new BigDecimal("208.5"), new BigDecimal("200.0"), "Network feed"),
                    new UsageRecord("CUS-1004", "Diya Rao", "Home Plus", "2026-10",
                            new BigDecimal("63.0"), new BigDecimal("100.0"), "Manual entry")
            ));
        };
    }
}
