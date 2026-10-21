package edu.devops.usageportal.service;

public class UsageNotFoundException extends RuntimeException {
    public UsageNotFoundException(long id) {
        super("Usage record " + id + " was not found");
    }
}
