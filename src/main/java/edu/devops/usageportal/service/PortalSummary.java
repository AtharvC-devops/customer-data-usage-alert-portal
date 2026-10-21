package edu.devops.usageportal.service;

import java.math.BigDecimal;

public record PortalSummary(long customerCount, long alertCount, BigDecimal averageUsagePercent) {
}
