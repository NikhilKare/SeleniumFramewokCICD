# Selenium E-Commerce Automation Framework 

Automated end-to-end testing suite for an e-commerce web application, built with **Java + Selenium WebDriver + TestNG** and integrated into a **Jenkins** CI/CD pipeline.  

---

## ✨ Key Features
| Area               | Highlights                                                                                   |
|--------------------|----------------------------------------------------------------------------------------------|
| **Framework**      | Page Object Model (POM), Data-Driven via JSON, utilities for waits/logging/screenshot capture|
| **CI/CD**          | Declarative Jenkins Pipeline triggered on push & PR; parallel browser stages                 |
| **Reporting**      | ExtentReports (HTML), Console logs, JUnit XML for Jenkins test trend graphs                  |
| **Scalability**    | Modular Maven project structure and cross-platform WebDriverManager integrations             |
| **Quality Gates**  | Unit tests, flaky-test retry analyzer                                                        |
| Browser            | Chrome                                                                                       |
---

# 🚀 Selenium Automation Framework (CICD Ready)

This is a **Java-based Selenium Test Automation Framework** designed for scalable and maintainable test automation.  
It follows **Page Object Model (POM)** with TestNG, Extent Reports, and integrates seamlessly with CI/CD pipelines like **Jenkins**.

---

## ✨ Features
- ✅ Page Object Model (POM) design
- ✅ TestNG for test management
- ✅ Data-driven testing (JSON)
- ✅ Cross-browser support (via WebDriverManager)
- ✅ Extent Reports with screenshots on failure
- ✅ Retry mechanism for flaky tests
- ✅ Jenkins declarative pipeline (parallel execution)
- ✅ Maven for build & dependency management

---

## 📂 Project Structure

SeleniumFramewokCICD
┣ 📂 src
┃ ┣ 📂 main/java/com/framework/base → Base classes & utilities
┃ ┣ 📂 main/java/com/framework/pages → Page Object Model (POM) classes
┃ ┣ 📂 test/java/com/framework/tests → TestNG test cases
┣ 📂 testSuits → TestNG suite XMLs
┣ 📂 reports → Extent reports (generated)
┣ 📄 pom.xml → Maven dependencies
┣ 📄 Jenkinsfile → CI/CD pipeline
┣ 📄 README.md
