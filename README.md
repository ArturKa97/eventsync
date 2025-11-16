# EventSync – Prototype Application

**Live Demo**  
🌐 Frontend: http://eventsyncbucket.s3-website.eu-central-1.amazonaws.com  
📘 Swagger UI (Backend): http://eventsync-env.eba-zbnkv3xm.eu-central-1.elasticbeanstalk.com/swagger-ui/index.html

---

## 📌 About

EventSync is a simple experimental prototype designed to:

- Create events  
- Add feedback to events  
- Automatically analyze feedback sentiment  
- Display overall sentiment statistics per event  

Feedback is processed using the **RoBERTa sentiment model**:  
https://huggingface.co/cardiffnlp/twitter-roberta-base-sentiment  
The backend uses this model to classify each feedback entry as **positive, neutral, or negative** and stores the results.

---

## 🛠️ Technology Stack

### **Backend**
- **Language:** Java (Spring Boot)
- **Java Version:** 17 (compatible with 16+)
- **Database:** H2 (used for development, testing, and production for simplicity)

### **Frontend**
- **Framework:** React

### **Hosting**
- **Backend:** AWS Elastic Beanstalk  
- **Frontend:** AWS S3 Static Website Hosting

---

## 🚀 How to Run the Project

### **Backend Setup**

You can run the backend using Maven or the included Maven wrapper.

In the root directory /backend.

mvn spring-boot:run 


### **Configuration Steps**
- **Database Configuration:**

The env.properties files are not included in the repository for security reasons.
You need to add/edit application.properties with the following:

-spring.datasource.username=${H2_USERNAME}   ---    # H2 database username (default: sa),

-spring.datasource.password=${H2_PASSWORD}  ---    # H2 database password (default: empty),

-huggingface.api.token=${HF_TOKEN}  ---    # Register at https://huggingface.co for an API key,

-huggingface.model.url=https://huggingface.co/cardiffnlp/twitter-roberta-base-sentiment,

-server.port=${PORT:5000}   ---    # Adjust or remove (default Spring Boot port is 8080),


In SecurityConfig.java:

Modify CORS mappings, methods, and allowed endpoints according to your setup or you can leave it as it is (currently allowing everything).

- **Frontend Configuration:**
  
Update the const HTTP value in:

eventsync\eventsync-ui\src\api\index.js
Ensure it matches your backend URL port (if ran locally default is localhost:8080) and CORS configuration.

