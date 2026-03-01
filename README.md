# 📇 Invoicer
A **professional GST-compliant invoice generator web application** built with **Spring Boot**, **Thymeleaf**, and **Maven**.  
Generates clean, print-ready A4 invoices with automatic tax calculation, dynamic item entries, proper Indian number formatting, and PDF export.

<hr>

## 🚀 Live Preview
Generate high-quality invoices from your browser with:
<br>
✔ Modern dashboard UI  
✔ Dynamic item rows  
✔ Proper GST calculation (CGST & SGST @ 9%)  
✔ Rounded amounts with Indian comma formatting  
✔ A4 print layout  
✔ PDF export  

<hr>

## 📋 Features

### 📌 Core Functionality
- Create GST invoices with both fixed and custom templates  
- Original / Duplicate invoice toggle  
- Dynamic item addition  
- Rounded figures with two decimal places  
- Format numbers in **Indian rupee style** (e.g., `30,839.30`)  
- Amount in words

### 🧮 Tax & Calculations
- Subtotal: Sum of (quantity × rate)  
- CGST @ 9%  
- SGST @ 9%  
- Grand Total (subtotal + taxes)  
- Total weight display

### 📄 Print & Export
- Print-ready A4 invoice format  
- Export to PDF (exact A4 sizing preserved)

### 🎨 User Interface
- Professional UI with modern layout  
- Styled with custom CSS  
- Responsive forms

<hr>

## 🛠 Stack
| Component  | Technology               |
|:-----------|:-------------------------|
| Backend    | Java / Spring Boot       |
| Frontend   | Thymeleaf                |
| Build Tool | Maven                    |
| UI         | HTML, CSS                |
| PDF Export | OpenHTMLToPDF            |
| Formatting | Indian Number Formatting |

<hr>

## 📁 Project Structure

Invoicer<br>
│<br>
├── src/main/java/com/example/invoicegenerator<br>
│ ├── controller<br>
│ ├── model<br>
│ ├── service<br>
│<br>
├── src/main/resources/templates<br>
│ ├── home.html<br>
│ ├── template1-form.html<br>
│ ├── template1-view.html<br>
│ └── template2-form.html<br>
│<br>
├── src/main/resources/static/css<br>
│ ├── professional.css<br>
│ └── invoice.css<br>
│<br>
└── pom.xml<br>

<hr>

## 📥 Getting Started
### 💡 Prerequisites
- Java 17+
- Maven
- IntelliJ (or any Java IDE)

### 🧠 Clone & Run
- git clone https://github.com/Bhuvanashri-sundarraj/Invoicer.git
- cd Invoicer
- mvn spring-boot:run
<br>
- Open in browser:
<br>
http://localhost:8080

## 👍 Usage

- Choose GST Template or Custom Template
- Enter invoice details, buyer details, and item list
- Click Generate Invoice
- Print or Download PDF

## 📈 Future Enhancements
✔ Store invoices in database (MySQL)<br>
✔ Auto invoice numbering<br>
✔ Invoice history dashboard<br>
✔ User authentication<br>
✔ Cloud deployment (Heroku/Render)<br>

## 👩‍💻 Author
Bhuvanashri Sundarraj
- GitHub: @Bhuvanashri-sundarraj<br>
Built with ⚙️ Spring Boot and 💡 a passion for clean invoice designs!
