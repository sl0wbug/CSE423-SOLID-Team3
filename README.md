# CSE423 Software Architecture Assignment

## Student Information

- **Project:** greenDAO
- **Course:** CSE423 – Software Architecture

---

## Selected GitHub Project

**Project Name:** greenDAO

**Original Repository:**
https://github.com/greenrobot/greenDAO

---

## Selected Java Files

The following Java files were selected from the greenDAO project for software architecture analysis and future refactoring using SOLID principles:

1. AbstractDao.java
2. AbstractDaoMaster.java
3. AbstractDaoSession.java
4. InternalUnitTestDaoAccess.java

---

## Repository Structure

```
CSE423-SOLID-Team3/
├── greenDAO/                             # Original multi-class system
│   ├── AbstractDao.java
│   ├── AbstractDaoMaster.java
│   ├── AbstractDaoSession.java
│   ├── InternalUnitTestDaoAccess.java
│   └── DESCRIPTION.md
├── refactored_greenDAO/                  # LLM-generated SOLID-compliant system
│   ├── api/                              # Abstractions & Interfaces (ISP/DIP)
│   │   ├── EntityRepository.java
│   │   ├── EntityMapper.java
│   │   ├── IdentityScopeCache.java
│   │   ├── TransactionManager.java
│   │   ├── DaoSession.java
│   │   ├── DaoMaster.java
│   │   └── UnitTestDaoAccess.java
│   └── impl/                             # Concrete Implementations (SRP/DI)
│       ├── BaseRepository.java
│       ├── DefaultEntityMapper.java
│       ├── DefaultIdentityScopeCache.java
│       ├── DatabaseTransactionManager.java
│       ├── DefaultDaoSession.java
│       ├── DefaultDaoMaster.java
│       └── DefaultUnitTestDaoAccess.java
└── TASK3_SOLID_COMPLIANCE.md            # Task 3 SOLID Compliance Evaluation Report
```

---

## TASK 3: SOLID Principles Compliance

### Evaluation Summary

| Metric | Original greenDAO Code | LLM-Generated System | Improvement |
| :--- | :---: | :---: | :---: |
| **SOLID Violation Count** | **24** | **0** | **-100% (-24 violations)** |
| **Dependency Inversion Score (DIS)** | **36.11%** | **93.33%** | **+57.22%** |
| **Responsibility Entanglement Index (REI)** | **3.50** | **1.00** | **Reduced to ideal (1.00)** |

Detailed evaluation breakdown, mathematical formulas, and principle-by-principle violation analysis are documented in [`TASK3_SOLID_COMPLIANCE.md`](file:///C:/Users/Endmin/Desktop/CSE423-SOLID-Team3/TASK3_SOLID_COMPLIANCE.md).