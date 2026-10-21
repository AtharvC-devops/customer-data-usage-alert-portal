package edu.devops.usageportal.web;

import edu.devops.usageportal.model.UsageRecord;
import edu.devops.usageportal.service.UsageService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PortalController {
    private final UsageService service;

    public PortalController(UsageService service) {
        this.service = service;
    }

    @GetMapping("/")
    public String dashboard(@RequestParam(required = false) String q, Model model) {
        List<UsageRecord> records = service.findAll(q);
        model.addAttribute("records", records);
        model.addAttribute("alerts", service.findAlerts());
        model.addAttribute("summary", service.summarize(records));
        model.addAttribute("query", q == null ? "" : q);
        model.addAttribute("newUsage", new UsageRecord());
        return "dashboard";
    }

    @GetMapping("/usage/{id}")
    public String detail(@PathVariable long id, Model model) {
        model.addAttribute("usage", service.findById(id));
        return "usage-detail";
    }

    @PostMapping("/usage")
    public String create(@Valid @ModelAttribute("newUsage") UsageRecord record,
                         BindingResult bindingResult, Model model,
                         RedirectAttributes redirectAttributes) {
        if (bindingResult.hasErrors()) {
            List<UsageRecord> records = service.findAll(null);
            model.addAttribute("records", records);
            model.addAttribute("alerts", service.findAlerts());
            model.addAttribute("summary", service.summarize(records));
            model.addAttribute("query", "");
            return "dashboard";
        }
        service.create(record);
        redirectAttributes.addFlashAttribute("successMessage", "Usage event added.");
        return "redirect:/";
    }
}
