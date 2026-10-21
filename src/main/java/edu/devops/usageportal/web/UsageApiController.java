package edu.devops.usageportal.web;

import edu.devops.usageportal.model.UsageRecord;
import edu.devops.usageportal.service.PortalSummary;
import edu.devops.usageportal.service.UsageService;
import edu.devops.usageportal.service.UsageNotFoundException;
import jakarta.validation.Valid;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

@Validated
@RestController
@RequestMapping("/api")
public class UsageApiController {
    private final UsageService service;

    public UsageApiController(UsageService service) {
        this.service = service;
    }

    @GetMapping("/usages")
    public List<UsageRecord> usages(@RequestParam(required = false) String q) {
        return service.findAll(q);
    }

    @GetMapping("/usages/{id}")
    public UsageRecord usage(@PathVariable long id) {
        return service.findById(id);
    }

    @PostMapping("/usages")
    public ResponseEntity<UsageRecord> create(@Valid @RequestBody UsageRecord record) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(record));
    }

    @GetMapping("/alerts")
    public List<UsageRecord> alerts() {
        return service.findAlerts();
    }

    @GetMapping("/summary")
    public PortalSummary summary() {
        return service.summarize(service.findAll(null));
    }

    @PostMapping("/simulate")
    public ResponseEntity<UsageRecord> simulate(@Valid @RequestBody SimulationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.simulate(
                request.customerId(), request.customerName(), request.usedGb(), request.quotaGb()));
    }

    @ExceptionHandler(UsageNotFoundException.class)
    public ResponseEntity<Map<String, String>> notFound(UsageNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", exception.getMessage()));
    }

    public record SimulationRequest(
            @NotBlank String customerId,
            @NotBlank String customerName,
            @NotNull
            @DecimalMin("0.00") BigDecimal usedGb,
            @NotNull
            @DecimalMin("0.01") BigDecimal quotaGb) {
    }
}
