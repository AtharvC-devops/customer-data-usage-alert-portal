package edu.devops.usageportal;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

class HtmlUnitDriverSmokeTest {
    @Test
    @Timeout(value = 30, unit = java.util.concurrent.TimeUnit.SECONDS)
    void startsHeadlessBrowserAndReadsLocalMarkup() {
        WebDriver driver = new HtmlUnitDriver();
        try {
            driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));
            driver.get("data:text/html,<html><head><title>driver-ready</title></head><body>ready</body></html>");
            assertThat(driver.getTitle()).isEqualTo("driver-ready");
        } finally {
            driver.quit();
        }
    }
}
