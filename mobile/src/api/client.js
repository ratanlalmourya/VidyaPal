import axios from 'axios';

const API_BASE_URL = process.env.EXPO_PUBLIC_API_URL || 'http://localhost:8080';

export const apiClient = axios.create({
  baseURL: `${API_BASE_URL}/api`,
  timeout: 5000,
});

export const endpoints = {
  login: '/users/login',
  register: '/users/register',
  profile: (id) => `/users/${id}`,
  studyRooms: '/study-rooms',
  studyRoom: (id) => `/study-rooms/${id}`,
  aiPrompt: '/ai-tutor/prompt',
  quiz: (id) => `/quizzes/${id}`,
  quizSubmit: (id) => `/quizzes/${id}/submit`,
  notifications: (userId) => `/notifications/user/${userId}`,
};
