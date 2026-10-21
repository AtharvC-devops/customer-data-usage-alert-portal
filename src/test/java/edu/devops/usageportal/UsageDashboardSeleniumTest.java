package edu.devops.usageportal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import static org.assertj.core.api.Assertions.assertThat;

class UsageDashboardSeleniumTest {
    @Test
    @Timeout(value = 30, unit = TimeUnit.SECONDS)
    void dashboardMarkupRendersAndUsageFormAcceptsInput() throws IOException {
        String dashboard = readDashboardTemplate();
        WebDriver driver = new HtmlUnitDriver();
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.get(asDataUrl(dashboard));

            assertThat(driver.getTitle()).isEqualTo("Usage Alert Portal");
            assertThat(driver.getPageSource()).contains("Usage, in view.", "Customer usage", "Usage alerts");

            driver.findElement(By.id("customer-id")).sendKeys("CUS-SELENIUM");
            driver.findElement(By.id("customer-name")).sendKeys("Selenium Customer");
            assertThat(driver.findElement(By.id("customer-name")).getAttribute("value"))
                    .isEqualTo("Selenium Customer");
            assertThat(driver.findElement(By.id("add-usage")).isDisplayed()).isTrue();
        } finally {
            driver.quit();
        }
    }

    private String readDashboardTemplate() throws IOException {
        try (InputStream template = getClass().getClassLoader().getResourceAsStream("templates/dashboard.html")) {
            if (template == null) {
                throw new IOException("Dashboard template was not available on the test classpath");
            }
            return new String(template.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String asDataUrl(String html) {
        String encoded = Base64.getEncoder().encodeToString(html.getBytes(StandardCharsets.UTF_8));
        return "data:text/html;charset=UTF-8;base64," + encoded;
    }
}
