import React, { useEffect, useState } from 'react';
import { FlatList, RefreshControl, SafeAreaView, ScrollView, StyleSheet, Text, View } from 'react-native';
import { apiClient, endpoints } from '../api/client';
import StudyRoomCard from '../components/StudyRoomCard';
import QuickActionButton from '../components/QuickActionButton';
import SectionHeader from '../components/SectionHeader';

const DashboardScreen = ({ navigation, route }) => {
  const [user, setUser] = useState(route.params?.user);
  const [studyRooms, setStudyRooms] = useState([]);
  const [notifications, setNotifications] = useState([]);
  const [refreshing, setRefreshing] = useState(false);

  const loadData = async () => {
    if (!user) {
      return;
    }
    setRefreshing(true);
    try {
      const [roomsResponse, notificationResponse, profileResponse] = await Promise.all([
        apiClient.get(endpoints.studyRooms, { params: { userId: user.id } }),
        apiClient.get(endpoints.notifications(user.id)),
        apiClient.get(endpoints.profile(user.id)),
      ]);
      setStudyRooms(roomsResponse.data);
      setNotifications(notificationResponse.data);
      setUser(profileResponse.data);
    } catch (error) {
      console.log('Failed to load dashboard data', error.message);
    } finally {
      setRefreshing(false);
    }
  };

  useEffect(() => {
    loadData();
  }, []);

  const openRoom = (room) => navigation.navigate('StudyRoom', { roomId: room.id, user });
  const openTutor = () => navigation.navigate('AiTutor', { user });
  const openNotifications = () => navigation.navigate('Notifications', { notifications });

  return (
    <SafeAreaView style={styles.safeArea}>
      <ScrollView
        contentInsetAdjustmentBehavior="automatic"
        style={styles.container}
        refreshControl={<RefreshControl refreshing={refreshing} onRefresh={loadData} />}
      >
        <Text style={styles.header}>Welcome back, {user?.name?.split(' ')[0] || 'Learner'} 👋</Text>
        <Text style={styles.subHeader}>{user?.learningGoal}</Text>

        <SectionHeader title="Quick actions" subtitle="Jump back into your learning" />
        <ScrollView horizontal showsHorizontalScrollIndicator={false} style={styles.quickRow}>
          <QuickActionButton label="Ask AI Tutor" onPress={openTutor} />
          <QuickActionButton label="Latest Notifications" onPress={openNotifications} />
        </ScrollView>

        <SectionHeader title="Your study rooms" subtitle="Continue where you left off" />
        <FlatList
          data={studyRooms}
          keyExtractor={(item) => item.id.toString()}
          renderItem={({ item }) => <StudyRoomCard room={item} onPress={() => openRoom(item)} />}
          scrollEnabled={false}
          ListEmptyComponent={<Text style={styles.emptyState}>No study rooms yet. Create one with the AI tutor!</Text>}
        />

        <SectionHeader title="Reminders" subtitle="Stay on track" />
        {notifications.slice(0, 3).map((notification) => (
          <View key={notification.id} style={styles.notificationCard}>
            <Text style={styles.notificationTitle}>{notification.title}</Text>
            <Text style={styles.notificationMessage}>{notification.message}</Text>
          </View>
        ))}
      </ScrollView>
    </SafeAreaView>
  );
};

const styles = StyleSheet.create({
  safeArea: {
    flex: 1,
    backgroundColor: '#f1f5f9',
  },
  container: {
    paddingHorizontal: 20,
    paddingTop: 16,
  },
  header: {
    fontSize: 26,
    fontWeight: '700',
    color: '#1e293b',
  },
  subHeader: {
    color: '#475569',
    marginBottom: 16,
  },
  quickRow: {
    marginBottom: 24,
  },
  emptyState: {
    textAlign: 'center',
    color: '#94a3b8',
    marginVertical: 12,
  },
  notificationCard: {
    backgroundColor: '#fff',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
  },
  notificationTitle: {
    fontWeight: '700',
    color: '#1e293b',
    marginBottom: 4,
  },
  notificationMessage: {
    color: '#475569',
  },
});

export default DashboardScreen;
