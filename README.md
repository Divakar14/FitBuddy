# 🏋️ FitBuddy

> **A comprehensive microservices-based fitness tracking platform with AI-powered recommendations**

FitBuddy is an intelligent fitness tracking application that allows users to log their daily activities and receive personalized AI-powered fitness recommendations. The application is built using a microservices architecture with Spring Boot, Spring Cloud, and modern frontend technologies.

---

## 📋 Table of Contents

- [Overview](#overview)
- [Key Features](#key-features)
- [Architecture](#architecture)
- [Tech Stack](#tech-stack)
- [Prerequisites](#prerequisites)
- [Project Structure](#project-structure)
- [Installation & Setup](#installation--setup)
- [Running the Application](#running-the-application)
- [API Documentation](#api-documentation)
- [Configuration](#configuration)
- [Database Schema](#database-schema)
- [Key Components](#key-components)
- [Future Enhancements](#future-enhancements)
- [Contributing](#contributing)
- [License](#license)

---

## 🎯 Overview

FitBuddy is a full-stack fitness tracking platform designed to help users monitor their physical activities and receive intelligent recommendations based on their workout history. The application leverages advanced AI technologies to analyze user activities and provide personalized fitness insights.

### What Makes FitBuddy Special?

- **Microservices Architecture**: Scalable, independent services for better maintainability
- **AI-Powered Recommendations**: Real-time recommendations using Google Gemini API
- **Multi-Database Support**: MongoDB for activity data, MySQL for user management
- **Asynchronous Processing**: RabbitMQ for event-driven architecture
- **Secure Authentication**: Keycloak OAuth2 integration
- **Service Discovery**: Netflix Eureka for dynamic service registration
- **API Gateway**: Spring Cloud Gateway for centralized routing
- **Modern Frontend**: React + Vite with Material-UI components

---

## ✨ Key Features

### User Management
- 🔐 **Secure Authentication**: OAuth2 integration with Keycloak
- 👤 **User Registration**: Easy sign-up process with email verification
- 🔑 **Token-Based Security**: JWT token-based authorization

### Activity Tracking
- 📝 **Activity Logging**: Track various types of fitness activities (Running, Walking, Cycling, etc.)
- 📊 **Activity Metrics**: Record duration, calories burned, and additional metrics
- 🗂️ **Activity History**: View all past activities with detailed information
- 🔍 **Activity Search**: Retrieve specific activities by ID

### AI Recommendations
- 🤖 **Gemini AI Integration**: Powered by Google's Gemini API
- 💡 **Personalized Insights**: Get tailored fitness recommendations based on activity patterns
- ⚡ **Real-Time Processing**: Asynchronous message processing via RabbitMQ
- 📈 **Activity-Based Recommendations**: Get specific recommendations for each activity

### System Architecture
- 🏗️ **Service Discovery**: Automatic service registration and discovery with Eureka
- 🚪 **API Gateway**: Centralized entry point with routing and security
- 📨 **Event Processing**: Asynchronous activity processing for recommendations
- ⚙️ **Centralized Configuration**: Dynamic configuration via Config Server

---

## 🏗️ Architecture

### System Architecture Diagram

```
┌─────────────────────────────────────────────────────────────┐
│              Frontend (React + Vite)                        │
│              - Activity Management                          │
│              - User Authentication                          │
│              - Recommendation Display                       │
└────────────────────┬────────────────────────────────────────┘
                     │
                     ▼
┌─────────────────────────────────────────────────────────────┐
│              API Gateway (Port 8080)                        │
│              - Keycloak OAuth2 Security                     │
│              - Request Routing                              │
│              - Service Discovery Integration                │
└────────────────────┬────────────────────────────────────────┘
                     │
          ┌──────────┼──────────┐
          ▼          ▼          ▼
    ┌─────────┐ ┌─────────┐ ┌─────────┐
    │ User    │ │Activity │ │   AI    │
    │Service  │ │Service  │ │Service  │
    │(8081)   │ │(8082)   │ │(8083)   │
    └────┬────┘ └────┬────┘ └────┬────┘
         │           │           │
         ▼           ▼           ▼
    ┌─────────┐ ┌───────── ┐ ┌─────────┐
    │ MySQL   │ │MongoDB   │ │MongoDB  │
    │         │ │(Activity)│ │(Recs)   │
    └─────────┘ └───────── ┘ └─────────┘
         ▲           ▲           ▲
         └───────────┼───────────┘
                     │
              ┌──────▼──────┐
              │  RabbitMQ   │
              │  Messaging  │
              └─────────────┘

┌─────────────────────────────────────────────────────────────┐
│         Eureka Service Discovery (Port 8761)                │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│      Config Server (Port 8888)                              │
│      - Centralized Configuration Management                 │
└─────────────────────────────────────────────────────────────┘

┌─────────────────────────────────────────────────────────────┐
│         Keycloak (Port 8181)                                │
│         - OAuth2 Authentication & Authorization             │
└─────────────────────────────────────────────────────────────┘
```

### Microservices Details

| Service | Port | Purpose | Database |
|---------|------|---------|----------|
| **Gateway** | 8080 | API Gateway, OAuth2 Security, Routing | N/A |
| **User Service** | 8081 | User management, registration, validation | MySQL |
| **Activity Service** | 8082 | Activity tracking, logging | MongoDB |
| **AI Service** | 8083 | AI recommendations, Gemini integration | MongoDB |
| **Eureka Server** | 8761 | Service discovery and registration | N/A |
| **Config Server** | 8888 | Centralized configuration | Git/Local |

---

## 🛠️ Tech Stack

### Backend
- **Java 21**: Latest Java version with modern features
- **Spring Boot 4.0.3 / 3.4.3**: Rapid application development framework
- **Spring Cloud 2025.1.1 / 2024.0.0**: Microservices framework
  - **Spring Cloud Gateway**: API Gateway and routing
  - **Spring Cloud Config**: Centralized configuration management
  - **Netflix Eureka**: Service discovery
- **Spring Data MongoDB**: MongoDB integration
- **Spring Data JPA**: ORM for MySQL
- **Spring WebFlux**: Reactive programming
- **Spring Security OAuth2**: OAuth2 resource server
- **Spring AMQP**: RabbitMQ integration

### Frontend
- **React 19.2.4**: UI library
- **Vite 8.0.4**: Build tool and dev server
- **Material-UI (MUI) 9.0.0**: UI component library
- **Redux Toolkit 2.11.2**: State management
- **Axios 1.15.0**: HTTP client
- **React Router 7.14.0**: Client-side routing
- **React OAuth2 Code PKCE 1.24.0**: OAuth2 authentication

### Infrastructure & Messaging
- **RabbitMQ**: Message broker for asynchronous communication
- **MongoDB**: NoSQL database for activities and recommendations
- **MySQL**: Relational database for user management
- **Keycloak**: OAuth2 and OpenID Connect identity provider
- **Netflix Eureka**: Service registry and discovery
- **Google Gemini API**: AI-powered recommendations

### Tools & Build
- **Maven**: Java build and dependency management
- **Git**: Version control

---

## 📋 Prerequisites

### System Requirements
- **Java 21**: Download from [oracle.com](https://www.oracle.com/java/technologies/downloads/#java21)
- **Maven 3.9+**: Download from [maven.apache.org](https://maven.apache.org/download.cgi)
- **Node.js 18+**: Download from [nodejs.org](https://nodejs.org/)
- **npm/yarn**: Package manager for Node.js

### External Services
- **MySQL 8.0+**: User database
- **MongoDB 7.0+**: Activity and recommendation data
- **RabbitMQ 3.12+**: Message broker
- **Keycloak 24+**: OAuth2 identity provider
- **Google Gemini API**: AI recommendations (get API key from [Google AI Studio](https://aistudio.google.com/))

### Development Tools
- **Git**: Version control
- **IDE**: IntelliJ IDEA, VS Code, or Eclipse
- **Postman/Thunder Client**: API testing (optional)

---

## 📁 Project Structure

```
FitBuddy/
├── activityservice/              # Activity tracking microservice
│   ├── src/main/java/com/fitness/activityservice/
│   │   ├── controller/           # REST endpoints
│   │   ├── service/              # Business logic
│   │   ├── model/                # Entity classes
│   │   ├── repository/           # Database access
│   │   ├── dto/                  # Data transfer objects
│   │   └── config/               # Configuration classes
│   ├── pom.xml                   # Maven dependencies
│   └── src/main/resources/
│       └── application.yaml      # Application configuration
│
├── userservice/                  # User management microservice
│   ├── src/main/java/com/fitness/userservice/
│   │   ├── controller/           # REST endpoints
│   │   ├── service/              # Business logic
│   │   ├── model/                # Entity classes
│   │   ├── repository/           # Database access
│   │   └── dto/                  # Data transfer objects
│   ├── pom.xml                   # Maven dependencies
│   └── src/main/resources/
│
├── aiservice/                    # AI recommendations microservice
│   ├── src/main/java/com/fitness/aiservice/
│   │   ├── controller/           # REST endpoints
│   │   ├── service/              # AI processing & Gemini integration
│   │   ├── model/                # Entity classes
│   │   ├── config/               # RabbitMQ & Mongo configuration
│   │   └── listener/             # Message listeners
│   ├── pom.xml                   # Maven dependencies
│   └── src/main/resources/
│
├── gateway/                      # API Gateway
│   ├── src/main/java/com/fitness/gateway/
│   │   ├── config/               # Security & routing configuration
│   │   ├── filter/               # Custom filters
│   │   └── user/                 # User service integration
│   ├── pom.xml                   # Maven dependencies
│   └── src/main/resources/
│
├── eureka/                       # Service Discovery Server
│   ├── src/main/java/
│   ├── pom.xml                   # Maven dependencies
│   └── src/main/resources/
│
├── configserver/                 # Centralized Config Server
│   ├── src/main/java/
│   ├── src/main/resources/
│   │   ├── application.yaml      # Config server configuration
│   │   └── config/               # Service configurations
│   │       ├── activity-service.yaml
│   │       ├── ai-service.yaml
│   │       ├── api-gateway.yaml
│   │       └── user-service.yml
│   └── pom.xml                   # Maven dependencies
│
├── fitbuddy-frontend/            # React + Vite Frontend
│   ├── src/
│   │   ├── components/           # React components
│   │   │   ├── ActivityForm.jsx      # Add activity form
│   │   │   ├── ActivityList.jsx      # Display activities
│   │   │   └── ActivityDetail.jsx    # Activity details
│   │   ├── services/
│   │   │   └── api.js               # API service layer
│   │   ├── store/
│   │   │   ├── authSlice.js         # Redux auth state
│   │   │   └── store.js             # Redux store configuration
│   │   ├── App.jsx               # Main app component
│   │   ├── main.jsx              # Entry point
│   │   └── authConfig.js         # Keycloak OAuth config
│   ├── vite.config.js            # Vite configuration
│   ├── package.json              # NPM dependencies
│   └── index.html                # HTML entry point
│
└── README.md                     # This file
```

---

## 🚀 Installation & Setup

### Step 1: Prerequisites Installation

#### Install External Services

**MySQL (macOS with Homebrew)**
```bash
brew install mysql
brew services start mysql
mysql_secure_installation
```

**MongoDB (macOS with Homebrew)**
```bash
brew install mongodb-community
brew services start mongodb-community
```

**RabbitMQ (macOS with Homebrew)**
```bash
brew install rabbitmq
brew services start rabbitmq-server
# Access RabbitMQ Management UI at http://localhost:15672 (guest/guest)
```

**Keycloak (Docker recommended)**
```bash
docker run -p 8181:8080 \
  -e KEYCLOAK_ADMIN=admin \
  -e KEYCLOAK_ADMIN_PASSWORD=admin \
  quay.io/keycloak/keycloak:latest \
  start-dev
```

> Access Keycloak at http://localhost:8181

### Step 2: Clone and Navigate to Project

```bash
# Clone the repository
git clone https://github.com/Divakar14/FitBuddy.git
cd FitBuddy
```

### Step 3: Build All Services

```bash
# Build Configuration Server
cd configserver
mvn clean install
cd ..

# Build Eureka Server
cd eureka
mvn clean install
cd ..

# Build API Gateway
cd gateway
mvn clean install
cd ..

# Build User Service
cd userservice
mvn clean install
cd ..

# Build Activity Service
cd activityservice
mvn clean install
cd ..

# Build AI Service
cd aiservice
mvn clean install
cd ..

# Build Frontend
cd fitbuddy-frontend
npm install
npm run build
cd ..
```

---

## ▶️ Running the Application

### Step 1: Set Environment Variables

```bash
# For Gemini API (AI Service)
export GEMINI_API_URL=https://generativelanguage.googleapis.com/v1beta/models
export GEMINI_API_KEY=your_gemini_api_key_here
```

### Step 2: Start Services (in order)

**Terminal 1 - Config Server**
```bash
cd configserver
mvn spring-boot:run
# Runs on http://localhost:8888
```

**Terminal 2 - Eureka Server**
```bash
cd eureka
mvn spring-boot:run
# Runs on http://localhost:8761
# Access UI at http://localhost:8761/
```

**Terminal 3 - User Service**
```bash
cd userservice
mvn spring-boot:run
# Runs on http://localhost:8081
```

**Terminal 4 - Activity Service**
```bash
cd activityservice
mvn spring-boot:run
# Runs on http://localhost:8082
```

**Terminal 5 - AI Service**
```bash
cd aiservice
mvn spring-boot:run
# Runs on http://localhost:8083
```

**Terminal 6 - API Gateway**
```bash
cd gateway
mvn spring-boot:run
# Runs on http://localhost:8080
```

**Terminal 7 - Frontend**
```bash
cd fitbuddy-frontend
npm run dev
# Runs on http://localhost:5173
```

### Step 3: Access the Application

- **Frontend**: http://localhost:5173
- **API Gateway**: http://localhost:8080
- **Eureka Dashboard**: http://localhost:8761
- **Keycloak**: http://localhost:8181
- **RabbitMQ**: http://localhost:15672

---

## 📚 API Documentation

### Base URL
```
http://localhost:8080/api
```

### User Service APIs

#### Register User
```http
POST /api/users/register
Content-Type: application/json

{
  "email": "user@example.com",
  "password": "securePassword123",
  "firstName": "John",
  "lastName": "Doe"
}

Response: 200 OK
{
  "id": "user-123",
  "email": "user@example.com",
  "firstName": "John",
  "lastName": "Doe",
  "registeredDate": "2024-04-12T10:30:00Z"
}
```

#### Validate User
```http
GET /api/users/{userId}/validate
Authorization: Bearer {token}

Response: 200 OK
true
```

### Activity Service APIs

#### Track Activity
```http
POST /api/activities
Content-Type: application/json
Authorization: Bearer {token}
X-User-ID: {userId}

{
  "type": "RUNNING",
  "duration": 45,
  "caloriesBurned": 450,
  "additionalMetrics": {
    "distance": "7.5km",
    "avgPace": "6:00/km"
  }
}

Response: 201 Created
{
  "id": "activity-123",
  "userId": "user-123",
  "type": "RUNNING",
  "duration": 45,
  "caloriesBurned": 450,
  "timestamp": "2024-04-12T10:30:00Z",
  "additionalMetrics": {
    "distance": "7.5km",
    "avgPace": "6:00/km"
  }
}
```

#### Get User Activities
```http
GET /api/activities
Authorization: Bearer {token}
X-User-ID: {userId}

Response: 200 OK
[
  {
    "id": "activity-123",
    "userId": "user-123",
    "type": "RUNNING",
    "duration": 45,
    "caloriesBurned": 450,
    "timestamp": "2024-04-12T10:30:00Z"
  }
]
```

#### Get Activity by ID
```http
GET /api/activities/{activityId}
Authorization: Bearer {token}

Response: 200 OK
{
  "id": "activity-123",
  "userId": "user-123",
  "type": "RUNNING",
  "duration": 45,
  "caloriesBurned": 450,
  "timestamp": "2024-04-12T10:30:00Z"
}
```

### AI Service APIs

#### Get User Recommendations
```http
GET /api/recommendations/user/{userId}
Authorization: Bearer {token}

Response: 200 OK
[
  {
    "id": "rec-123",
    "userId": "user-123",
    "recommendation": "Excellent running performance! Maintain consistent pace and gradually increase distance for better endurance.",
    "generatedDate": "2024-04-12T10:35:00Z"
  }
]
```

#### Get Activity-Specific Recommendation
```http
GET /api/recommendations/activity/{activityId}
Authorization: Bearer {token}

Response: 200 OK
{
  "id": "rec-124",
  "activityId": "activity-123",
  "userId": "user-123",
  "recommendation": "Great running session! To prevent injuries, ensure you have proper warm-up before runs.",
  "generatedDate": "2024-04-12T10:35:00Z"
}
```

---

## ⚙️ Configuration

### Configuration Server

The Config Server manages all service configurations. Update configurations in:
```
configserver/src/main/resources/config/
```

#### Activity Service Configuration
File: `activity-service.yaml`
```yaml
spring:
  data:
    mongodb:
      uri: mongodb://localhost:27017/fitness_activity
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

server:
  port: 8082

rabbitmq:
  exchange:
    name: fitness.exchange
  queue:
    name: activity.queue
  routing:
    key: activity.tracking
```

#### AI Service Configuration
File: `ai-service.yaml`
```yaml
gemini:
  api:
    url: ${GEMINI_API_URL}
    key: ${GEMINI_API_KEY}
```

#### User Service Configuration
File: `user-service.yml`
```yaml
spring:
  datasource:
    url: jdbc:mysql://localhost:3306/fitness_user_db
    username: root
    password: Admin@2026
  jpa:
    hibernate:
      ddl-auto: update
```

#### API Gateway Configuration
File: `api-gateway.yaml`
```yaml
spring:
  security:
    oauth2:
      resourceserver:
        jwt:
          jwk-set-uri: http://localhost:8181/realms/fitness-oauth2/protocol/openid-connect/certs
  cloud:
    gateway:
      routes:
        - id: user-service
          uri: lb://USER-SERVICE
          predicates:
            - Path=/api/users/**
        - id: activity-service
          uri: lb://ACTIVITY-SERVICE
          predicates:
            - Path=/api/activities/**
        - id: ai-service
          uri: lb://AI-SERVICE
          predicates:
            - Path=/api/recommendations/**
```

---

## 🗄️ Database Schema

### MySQL - User Service

```sql
CREATE DATABASE fitness_user_db;

CREATE TABLE users (
  id VARCHAR(255) PRIMARY KEY,
  email VARCHAR(255) UNIQUE NOT NULL,
  password VARCHAR(255) NOT NULL,
  first_name VARCHAR(100),
  last_name VARCHAR(100),
  created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);
```

### MongoDB - Activity Service

```javascript
// Collection: activities
{
  "_id": ObjectId,
  "userId": String,
  "type": String,  // RUNNING, WALKING, CYCLING, etc.
  "duration": Number,
  "caloriesBurned": Number,
  "additionalMetrics": {
    "distance": String,
    "avgPace": String,
    "elevation": Number
  },
  "timestamp": ISODate,
  "createdAt": ISODate
}
```

### MongoDB - AI Service

```javascript
// Collection: recommendations
{
  "_id": ObjectId,
  "userId": String,
  "activityId": String,
  "recommendation": String,
  "generatedDate": ISODate,
  "model": String,  // "GEMINI"
  "createdAt": ISODate
}
```

---

## 🔑 Key Components

### API Gateway (Spring Cloud Gateway)
- Routes requests to appropriate microservices
- Handles OAuth2 security validation
- Implements Keycloak JWT verification
- Syncs user information from Keycloak to User Service

### User Service
- User registration and authentication
- User validation and profile management
- MySQL database for persistent storage
- Eureka service registration

### Activity Service
- Activity CRUD operations
- MongoDB for fast, flexible data storage
- RabbitMQ integration for activity events
- Publishes activity events for AI processing

### AI Service
- Listens to activity events from RabbitMQ
- Integrates with Google Gemini API
- Generates personalized recommendations
- Stores recommendations in MongoDB
- Exposes REST API for fetching recommendations

### Frontend
- React components for UI
- Redux for state management
- OAuth2 authentication flow with Keycloak
- API integration for activities and recommendations
- Material-UI for professional UI design

---

## 📊 Event Flow

### Activity Tracking to Recommendation Flow

1. **User Logs Activity**
   - Frontend sends activity data to API Gateway
   - Gateway validates token and routes to Activity Service

2. **Activity Service Processes Request**
   - Stores activity in MongoDB
   - Publishes `activity.created` event to RabbitMQ

3. **AI Service Listens to Events**
   - `ActivityMessageListener` receives activity event
   - Extracts activity data and user information

4. **Generate Recommendation**
   - `RecommendationService` calls `ActivityAIService`
   - `ActivityAIService` calls `GeminiService`
   - Gemini API generates AI-powered recommendation

5. **Store and Expose Recommendation**
   - Recommendation stored in MongoDB
   - Accessible via `/api/recommendations` endpoints

---

## 🔒 Security Architecture

### Authentication Flow
1. User logs in via Keycloak OAuth2
2. Frontend receives JWT token
3. Token sent with each API request
4. API Gateway validates JWT via Keycloak
5. User ID extracted and passed to services via `X-User-ID` header

### Authorization
- OAuth2 resource server configuration
- JWT token validation
- Per-service authorization checks
- Keycloak realm configuration for role-based access

---

## 🚀 Future Enhancements

### Short Term
- [ ] Add more activity types (Swimming, Yoga, Strength Training)
- [ ] Implement activity history graphs and charts
- [ ] Add social features (friend connections, leaderboards)
- [ ] Push notifications for achievements
- [ ] Mobile app (React Native)

### Medium Term
- [ ] Advanced analytics and insights
- [ ] Integration with wearable devices (Fitbit, Apple Watch)
- [ ] Meal planning recommendations
- [ ] Workout plan generation
- [ ] Multi-language support

### Long Term
- [ ] Machine learning models for personalized recommendations
- [ ] Real-time activity tracking
- [ ] Video-based workout guidance
- [ ] Community challenges and events
- [ ] Integration with health records

---

## 🤝 Contributing

Contributions are welcome! Please follow these steps:

1. **Fork the repository**
   ```bash
   git clone https://github.com/YOUR_USERNAME/FitBuddy.git
   cd FitBuddy
   ```

2. **Create a feature branch**
   ```bash
   git checkout -b feature/your-feature-name
   ```

3. **Make your changes**
   - Follow the existing code style
   - Add tests for new functionality
   - Update documentation as needed

4. **Commit your changes**
   ```bash
   git commit -m "Add feature: description of your feature"
   ```

5. **Push to your fork**
   ```bash
   git push origin feature/your-feature-name
   ```

6. **Create a Pull Request**
   - Describe your changes in detail
   - Reference any related issues

### Code Style Guidelines
- Java: Follow Google Java Style Guide
- JavaScript/React: Follow Airbnb React Style Guide
- YAML: Use 2-space indentation
- Comments: Use clear, concise comments

---

## 📝 License

This project is licensed under the MIT License - see the LICENSE file for details.

---

## 👨‍💻 Author

**Divakar TR**
- GitHub: [@Divakar14](https://github.com/Divakar14)
- Project Repository: [FitBuddy](https://github.com/Divakar14/FitBuddy)

---

## 🆘 Troubleshooting

### Common Issues

#### Port Already in Use
```bash
# Find process using port
lsof -i :PORT_NUMBER

# Kill the process
kill -9 PID
```

#### MongoDB Connection Issues
```bash
# Check if MongoDB is running
mongosh

# If not, start MongoDB
brew services start mongodb-community
```

#### RabbitMQ Connection Issues
```bash
# Check RabbitMQ status
brew services list

# Restart RabbitMQ
brew services restart rabbitmq-server
```

#### Gemini API Not Working
- Verify API key in environment variables
- Check API quota in Google AI Studio
- Ensure network connectivity

#### Keycloak Not Accessible
```bash
# If using Docker, check container
docker ps

# Access logs
docker logs CONTAINER_ID
```

---

## 📞 Support

For issues, questions, or feedback:
1. Check existing GitHub issues
2. Create a new issue with detailed description
3. Include steps to reproduce the problem
4. Share error logs and configuration details

---

## 🙏 Acknowledgments

- Spring Framework and Spring Boot team
- Google Gemini API
- Keycloak community
- React and Vite communities
- All open-source contributors

---

**Last Updated**: April 12, 2026
**Version**: 1.0.0
