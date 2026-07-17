# DAXP Core Test & Integration Environment 🧪

Welcome to the official integration and test sandbox for **DAXP (Data & Attribute eXchange Protocol)**. This repository serves as a fully functional, Spring Boot-based reference implementation designed to demonstrate DAXP's core engine capabilities, protocol syntax, and microservice integration.

For full protocol specification and theoretical fundamentals, please visit [daxprotocol.org](https://daxprotocol.org).

---

## 🚀 Quick Start

### 1. Prerequisites
* **Java 17** or higher
* **Maven 3.8+**

### 2. Run the Application
Clone the repository and spin up the local test server:

```bash
git clone git@github.com:roberthoma/daxp-core-test.git

```
The server will start locally on `http://localhost:8080`.

---

## 📡 Testing the Protocol (API Endpoints)

All DAXP test payloads should be sent via **POST** requests to the single unified endpoint:
👉 `http://localhost:8080/daxp`

Use `text/plain` or `application/octet-stream` as the content type since DAXP bypasses the heavy processing overhead typical of JSON.

### 📋 Test Scenarios & Example Payloads

Below are the 7 core functional test cases demonstrating dictionary requests, CRUD operations, and advanced state modifiers (like Force Null).

#### 1. Dictionary Request (`$:DIC_REQ`)
Requests the distributed schema/dictionary definition from the server.
```text
DAXP|V=v0.7|$:1=$:DIC_REQ|$:9=123|
```

#### 2. About Protocol Request (`$:ABOUT_REQ`)
Fetches general information about the current protocol implementation and server metadata.
```text
DAXP|V=v0.7|EN=UTF-8|CX=XYZ|$:1=$:ABOUT_REQ|$:9=123|
```

#### 3. Standard CRM Request Base
Initiating a dynamic CRM context operation request.
```text
DAXP|V=v0.7|EN=UTF-8|CX=CRM|$:1=CRM.REQ|$:9=123|
```

#### 4. CRM Request with Qualified Data Field
Passing a specific tag (`2001=5`) explicitly bound to the CRM context (`CRM:`).
```text
DAXP|V=v0.7|EN=UTF-8|$:1=CRM.REQ|CRM:2001=5|$:9=123|
```

#### 5. CRM Delete Operation
Executing a delete operation targeting a specific record ID within the CRM context.
```text
DAXP|V=v0.7|EN=UTF-8|CX=CRM|$:1=CRM.DELETE|2001=3|$:9=123|
```

#### 6. Complex CRM Update
Demonstrates inline context switching, numeric payload tag optimization, and heterogeneous field updates.
```text
DAXP|V=v0.7|EN=UTF-8|CX=CRM|$:1=CRM.UPDATE|CRM:2001=5|CRM:2005=Szczecin321|2007=ORGANIZATION|$:9=123|
```

#### 7. Create New CRM Record with Advanced Modifiers (Force Null `^N`)
A powerful showcase of DAXP's hardware-friendly streaming. Notice the `^N` modifier – it instructs the database layer to explicitly force a field to `NULL` without sending bulky text tokens.
```text
DAXP|V=0.7beta|EN=UTF-8|CX=CRM|\(:1=CRM.NEW\vert{}\):5=I|\(:6=2000\vert{}2002=Krysia\vert{}2003^N\vert{}2004=krysia@wp.pl\vert{}2005^N\vert{}2006=+48 453646\vert{}2007^N\vert{}\):9=70|
```

---

## 🛠️ How to Test via cURL

You can execute any of the cases above directly from your terminal using `curl`:

```bash
curl -X POST http://localhost:8080/daxp \
  -H "Content-Type: text/plain" \
  -d "DAXP|V=v0.7|\$:1=\(:DIC_REQ\vert{}\):9=123|"
```

---

## 🤝 Contributing & Community

This test suite is open for community contributions! We are actively seeking enterprise engineers to help expand this integration test ecosystem.

### Current Roadmap for This Test Repo:
1. **Performance Harness:** Integrating `BenchmarkDotNet` and JMeter scripts to visually prove DAXP vs JSON throughput advantages.
2. **Oracle PL/SQL Test Cases:** Creating mock db layers to stream these raw payloads directly into database pipelines.

If you encounter any bugs, have questions about payload parsing behavior, or want to add test cases for a new business domain (e.g., Telecom or Banking), feel free to open an **Issue** or submit a **Pull Request**.

---
License: [MIT](LICENSE) (or insert your license here)
