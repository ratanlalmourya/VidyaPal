# VidyaPal

VidyaPal is a full-stack learning companion that pairs a React Native mobile app with a Spring Boot backend. The app focuses on personalised learning journeys, AI assisted tutoring, and practice quizzes derived from the provided user stories.

## Project structure

```
.
├── backend/   # Spring Boot REST API with H2 in-memory database
└── mobile/    # React Native (Expo) mobile client
```

## Backend (Spring Boot)

### Prerequisites

- Java 21+
- Apache Maven 3.9+

### Run locally

```bash
cd backend
mvn spring-boot:run
```

The API becomes available at `http://localhost:8080/api`. An in-memory H2 database is pre-populated with demo users, study rooms, materials, quizzes, and notifications.

### Useful endpoints

- `POST /api/users/login` – Dummy authentication
- `GET /api/study-rooms?userId=1` – Study rooms for a learner
- `POST /api/ai-tutor/prompt` – Mock AI tutor response
- `POST /api/quizzes/{id}/submit` – Quiz grading with feedback

## Mobile app (React Native)

### Prerequisites

- Node.js 18+
- npm 9+
- Expo CLI (`npm install -g expo-cli`), or use `npx expo start`

### Run locally

1. Ensure the backend is running on `http://localhost:8080`.
2. In a new terminal:

```bash
cd mobile
npm start
```

3. Follow the Expo instructions to launch the app on an emulator or device.

### Environment variables

The mobile client reads `EXPO_PUBLIC_API_URL`. If the backend runs on a different host/port, set it before starting Expo:

```bash
EXPO_PUBLIC_API_URL="http://10.0.2.2:8080" npm start
```

## Testing

- Backend: `cd backend && mvn test`
- Mobile: `cd mobile && npm test`

## End-to-end flow

1. Launch the backend (`mvn spring-boot:run`).
2. Start the mobile client (`npm start`).
3. Sign in with the seeded learner (`priya@vidyapal.ai` / `password`).
4. Explore dashboards, study rooms, AI tutor prompts, and quizzes powered by the backend data.

## Notes

- The project avoids committing build artifacts or binaries.
- The H2 database seeds meaningful dummy data aligned with the provided user stories.
