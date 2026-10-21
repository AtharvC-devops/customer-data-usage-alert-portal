package edu.devops.usageportal.web;

import edu.devops.usageportal.service.UsageNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class PortalExceptionHandler {
    @ExceptionHandler(UsageNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String usageNotFound(UsageNotFoundException exception, Model model) {
        model.addAttribute("message", exception.getMessage());
        return "not-found";
    }
}
