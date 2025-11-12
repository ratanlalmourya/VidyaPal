import React, { useEffect, useState } from 'react';
import { Pressable, ScrollView, StyleSheet, Text, View } from 'react-native';
import { apiClient, endpoints } from '../api/client';
import SectionHeader from '../components/SectionHeader';

const StudyRoomScreen = ({ navigation, route }) => {
  const { roomId, user } = route.params;
  const [room, setRoom] = useState(null);

  useEffect(() => {
    apiClient
      .get(endpoints.studyRoom(roomId))
      .then((response) => setRoom(response.data))
      .catch((error) => console.log('Failed to load study room', error.message));
  }, [roomId]);

  if (!room) {
    return (
      <View style={styles.loadingContainer}>
        <Text style={styles.loadingText}>Loading study room…</Text>
      </View>
    );
  }

  return (
    <ScrollView style={styles.container} contentInsetAdjustmentBehavior="automatic">
      <Text style={styles.title}>{room.title}</Text>
      <Text style={styles.meta}>Focus mode: {room.focusMode?.replace('_', ' ')}</Text>
      <Text style={styles.meta}>Completion: {Math.round(room.completionRate * 100)}%</Text>

      <SectionHeader title="Materials" />
      {room.materials?.map((material) => (
        <View key={material.id} style={styles.materialCard}>
          <Text style={styles.materialTitle}>{material.title}</Text>
          <Text style={styles.materialMeta}>{material.type}</Text>
          <Text style={styles.materialDescription}>{material.description || material.content}</Text>
        </View>
      ))}

      <SectionHeader title="Practice" subtitle="Test yourself" />
      {room.quizzes?.map((quiz) => (
        <Pressable
          key={quiz.id}
          style={styles.quizCard}
          onPress={() => navigation.navigate('Quiz', { quizId: quiz.id, roomTitle: room.title })}
        >
          <Text style={styles.quizQuestion}>{quiz.question}</Text>
          <Text style={styles.quizMeta}>Tap to take quiz</Text>
        </Pressable>
      ))}

      <Pressable style={styles.cta} onPress={() => navigation.navigate('AiTutor', { user, room })}>
        <Text style={styles.ctaText}>Ask AI Tutor about this topic</Text>
      </Pressable>
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#f8fafc',
  },
  loadingContainer: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
    backgroundColor: '#f8fafc',
  },
  loadingText: {
    color: '#475569',
  },
  title: {
    fontSize: 24,
    fontWeight: '700',
    color: '#0f172a',
  },
  meta: {
    marginTop: 4,
    color: '#475569',
  },
  materialCard: {
    backgroundColor: '#fff',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
  },
  materialTitle: {
    fontSize: 16,
    fontWeight: '600',
    color: '#1e293b',
  },
  materialMeta: {
    color: '#6366f1',
    marginTop: 4,
    marginBottom: 8,
  },
  materialDescription: {
    color: '#475569',
  },
  quizCard: {
    backgroundColor: '#eef2ff',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
  },
  quizQuestion: {
    fontWeight: '700',
    color: '#312e81',
  },
  quizMeta: {
    marginTop: 8,
    color: '#4338ca',
  },
  cta: {
    marginTop: 16,
    backgroundColor: '#4c51bf',
    padding: 16,
    borderRadius: 12,
    alignItems: 'center',
  },
  ctaText: {
    color: '#fff',
    fontWeight: '700',
  },
});

export default StudyRoomScreen;
