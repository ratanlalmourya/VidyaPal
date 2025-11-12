import React from 'react';
import { NavigationContainer } from '@react-navigation/native';
import { createNativeStackNavigator } from '@react-navigation/native-stack';
import LoginScreen from './src/screens/LoginScreen';
import DashboardScreen from './src/screens/DashboardScreen';
import StudyRoomScreen from './src/screens/StudyRoomScreen';
import AiTutorScreen from './src/screens/AiTutorScreen';
import QuizScreen from './src/screens/QuizScreen';
import NotificationsScreen from './src/screens/NotificationsScreen';

const Stack = createNativeStackNavigator();

const App = () => {
  return (
    <NavigationContainer>
      <Stack.Navigator>
        <Stack.Screen name="Login" component={LoginScreen} options={{ headerShown: false }} />
        <Stack.Screen name="Dashboard" component={DashboardScreen} options={{ title: 'Dashboard' }} />
        <Stack.Screen name="StudyRoom" component={StudyRoomScreen} options={{ title: 'Study Room' }} />
        <Stack.Screen name="AiTutor" component={AiTutorScreen} options={{ title: 'AI Tutor' }} />
        <Stack.Screen name="Quiz" component={QuizScreen} options={{ title: 'Quiz' }} />
        <Stack.Screen name="Notifications" component={NotificationsScreen} options={{ title: 'Notifications' }} />
      </Stack.Navigator>
    </NavigationContainer>
  );
};

export default App;
