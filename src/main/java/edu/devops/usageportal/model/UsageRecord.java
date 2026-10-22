package edu.devops.usageportal.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.Instant;

@Entity
@Table(name = "usage_records")
public class UsageRecord {
    private static final BigDecimal DEFAULT_WARNING_THRESHOLD_PERCENT = new BigDecimal("80");

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 40)
    private String customerId;

    @NotBlank
    @Size(max = 100)
    private String customerName;

    @NotBlank
    @Size(max = 60)
    private String planName;

    @NotBlank
    @Size(max = 20)
    private String billingPeriod;

    @NotNull
    @DecimalMin(value = "0.00")
    private BigDecimal usedGb;

    @NotNull
    @DecimalMin(value = "0.01")
    private BigDecimal quotaGb;

    @Size(max = 80)
    private String source;

    private Instant recordedAt;

    @Transient
    private BigDecimal warningThresholdPercent = DEFAULT_WARNING_THRESHOLD_PERCENT;

    public UsageRecord() {
    }

    public UsageRecord(String customerId, String customerName, String planName,
                       String billingPeriod, BigDecimal usedGb, BigDecimal quotaGb,
                       String source) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.planName = planName;
        this.billingPeriod = billingPeriod;
        this.usedGb = usedGb;
        this.quotaGb = quotaGb;
        this.source = source;
    }

    @PrePersist
    void setRecordedAtIfMissing() {
        if (recordedAt == null) {
            recordedAt = Instant.now();
        }
    }

    public BigDecimal getUsagePercent() {
        if (usedGb == null || quotaGb == null || quotaGb.signum() == 0) {
            return BigDecimal.ZERO;
        }
        return usedGb.multiply(BigDecimal.valueOf(100))
                .divide(quotaGb, 1, RoundingMode.HALF_UP);
    }

    public UsageStatus getStatus() {
        if (usedGb == null || quotaGb == null) {
            return UsageStatus.NORMAL;
        }
        if (usedGb.compareTo(quotaGb) >= 0) {
            return UsageStatus.EXCEEDED;
        }
        if (usedGb.multiply(BigDecimal.valueOf(100))
                .compareTo(quotaGb.multiply(warningThresholdPercent)) >= 0) {
            return UsageStatus.WARNING;
        }
        return UsageStatus.NORMAL;
    }

    public void setWarningThresholdPercent(BigDecimal warningThresholdPercent) {
        this.warningThresholdPercent = warningThresholdPercent;
    }

    public String getStatusLabel() {
        return switch (getStatus()) {
            case NORMAL -> "Within limit";
            case WARNING -> "Approaching limit";
            case EXCEEDED -> "Limit exceeded";
        };
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getCustomerId() { return customerId; }
    public void setCustomerId(String customerId) { this.customerId = customerId; }
    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }
    public String getPlanName() { return planName; }
    public void setPlanName(String planName) { this.planName = planName; }
    public String getBillingPeriod() { return billingPeriod; }
    public void setBillingPeriod(String billingPeriod) { this.billingPeriod = billingPeriod; }
    public BigDecimal getUsedGb() { return usedGb; }
    public void setUsedGb(BigDecimal usedGb) { this.usedGb = usedGb; }
    public BigDecimal getQuotaGb() { return quotaGb; }
    public void setQuotaGb(BigDecimal quotaGb) { this.quotaGb = quotaGb; }
    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }
    public Instant getRecordedAt() { return recordedAt; }
    public void setRecordedAt(Instant recordedAt) { this.recordedAt = recordedAt; }
}
