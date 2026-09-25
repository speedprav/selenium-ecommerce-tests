# Selenium E-Commerce Test Suite

**Tiny Project — Software Testing and Automation**
Parul University · BCA Honors

## Team

| Name | Enrollment No. | Contribution |
|---|---|---|
| Pravinkumar Choudhary (Praveen) | 2305101270266 | Framework setup, LoginTest, CheckoutTest, repo |
| Anurag Kumar | 2305101270020 | CartTest, test data design |
| Anurag Pareekh | 2305101270021 | Execution, reporting, documentation |

## What this is

An automated regression suite for [SauceDemo](https://www.saucedemo.com), a public e-commerce
demo site, built with **Selenium WebDriver + Java + TestNG + Maven**, using the **Page Object
Model** so locators are isolated from test logic.

## Coverage

- **Login** — valid login, invalid password, locked-out user
- **Cart** — add-to-cart badge count, item removal
- **Checkout** — empty-form validation, full order completion

## Tech stack

- Java 17
- Selenium WebDriver 4.21.0
- TestNG 7.10.2
- Maven
- WebDriverManager (auto-downloads the matching ChromeDriver — no manual driver setup)

## Project structure

```
selenium-ecommerce-tests/
├── pom.xml
├── testng.xml
├── src/
│   ├── main/java/pages/       # Page Object classes
│   │   ├── BasePage.java
│   │   ├── LoginPage.java
│   │   ├── InventoryPage.java
│   │   ├── CartPage.java
│   │   └── CheckoutPage.java
│   └── test/java/tests/       # Test classes
│       ├── LoginTest.java
│       ├── CartTest.java
│       └── CheckoutTest.java
└── screenshots/                # Execution evidence
```

## Prerequisites

- Java 17+
- Maven 3.8+
- Google Chrome installed

## How to run

```bash
git clone https://github.com/speedprav/selenium-ecommerce-tests.git
cd selenium-ecommerce-tests
mvn test
```

This runs all tests defined in `testng.xml` and prints a pass/fail summary. A full HTML report
is generated automatically at:

```
target/surefire-reports/index.html
```

To watch the browser execute during a live demo, make sure the `--headless=new` line in each
test's `setUp()` stays commented out (it is, by default).

## Test cases

| ID | Description |
|---|---|
| TC_01 | Valid login redirects to the Products page |
| TC_02 | Invalid password shows the correct error banner |
| TC_03 | Locked-out user is blocked with the expected error message |
| TC_04 | Adding a product updates the cart badge count correctly |
| TC_05 | Removing a product from the cart updates the cart contents |
| TC_06 | Checkout form rejects submission when required fields are empty |
| TC_07 | Valid checkout data completes the order and shows confirmation |

## Notes

Full report (Problem Identification → Implementation → Demonstration → Evaluation →
Outcome Assessment) is submitted separately as the Tiny Project PDF.
