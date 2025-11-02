# Login Automation Script

Automated UI tests for the login page at `https://practicetestautomation.com/practice-test-login/` using Java, Maven, Selenium WebDriver, TestNG and ExtentReports. Tests capture failure screenshots and generate an HTML report.

## Repository
- Branch: `main`
- Remote: `origin` (example: `https://github.com/dilmi1108/LoginAutomationScript.git`)

## Project Structure
- `src/test/java` - Test classes (e.g. `tests/LoginPageTest.java`)
- `pom.xml` - Maven build file with dependencies
- `test-output/` - Generated test artifacts:
  - `test-output/ExtentReport.html` - Extent report

## Prerequisites
- JDK 11+ (ensure `JAVA_HOME` set)
- Maven 3.6+
- Chrome browser installed
- ChromeDriver available:
  - Option A: put matching `chromedriver` binary on `PATH`
  - Option B: use `io.github.bonigarcia:webdrivermanager` in `pom.xml` (recommended)

## Common Maven commands
- Run all tests:
  - `mvn test`
- Clean and run:
  - `mvn clean test`
- Run a single TestNG class:
  - `mvn -Dtest=tests.LoginPageTest test`

## Reports & Screenshots
- After tests complete, open `test-output/ExtentReport.html` in a browser.
- When tests fail, screenshots are saved to `test-output/screenshots/` and attached to the Extent report.

## Configuration notes
- ChromeDriver path can be configured via system property:
  - `-Dwebdriver.chrome.driver=C:\path\to\chromedriver.exe`
- Adjust timeouts and base URL in `src/test/java/tests/LoginPageTest.java` constants if needed.
- If using CI (GitHub Actions, Azure Pipelines, etc.), ensure a display (headless Chrome) or use Xvfb equivalent.

## Troubleshooting
- Driver start failures:
  - Verify Chrome version matches ChromeDriver version.
  - Use WebDriverManager to automatically manage the driver binary.
- Report not generated:
  - Confirm tests ran (console output).
  - Confirm process has write permission to create `test-output/`.
- Screenshots missing:
  - Screenshots are captured when a test fails; ensure failures occur and `test-output/screenshots/` exists or can be created.

## Adding/Updating Tests
- Create new TestNG test classes under `src/test/java`.
- Use existing patterns in `tests/LoginPageTest.java` for Extent report integration and screenshot capture on failure.

## CI / Headless
- For headless runs add Chrome options to start Chrome in headless mode in test setup.
- Ensure CI agents have matching Chrome and chromedriver or use WebDriverManager.

## License
- Add project license as required (e.g. `LICENSE` file).
