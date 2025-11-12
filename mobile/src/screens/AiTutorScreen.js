import React, { useState } from 'react';
import { Pressable, ScrollView, StyleSheet, Text, TextInput, View } from 'react-native';
import { apiClient, endpoints } from '../api/client';

const AiTutorScreen = ({ route }) => {
  const { room } = route.params || {};
  const [prompt, setPrompt] = useState(room ? `Explain ${room.title}` : 'Help me plan my next study session');
  const [response, setResponse] = useState(null);
  const [loading, setLoading] = useState(false);

  const askTutor = async () => {
    setLoading(true);
    try {
      const { data } = await apiClient.post(endpoints.aiPrompt, {
        prompt,
        studyRoomId: room?.id,
      });
      setResponse(data);
    } catch (error) {
      setResponse({
        summary: 'Unable to connect to the AI tutor right now.',
        keyPoints: ['Please check that the backend server is running.'],
        followUpQuestions: [],
      });
    } finally {
      setLoading(false);
    }
  };

  return (
    <ScrollView style={styles.container}>
      <Text style={styles.title}>AI Tutor</Text>
      <Text style={styles.subtitle}>Ask contextual questions and get targeted guidance.</Text>
      <TextInput
        value={prompt}
        onChangeText={setPrompt}
        style={styles.input}
        multiline
        numberOfLines={4}
        placeholder="What do you need help with?"
      />
      <Pressable style={styles.button} onPress={askTutor}>
        <Text style={styles.buttonText}>{loading ? 'Thinking…' : 'Ask Vidya'}</Text>
      </Pressable>

      {response && (
        <View style={styles.responseCard}>
          <Text style={styles.responseTitle}>Summary</Text>
          <Text style={styles.responseText}>{response.summary}</Text>

          <Text style={styles.responseTitle}>Key points</Text>
          {response.keyPoints?.map((point, index) => (
            <Text key={index} style={styles.responseList}>• {point}</Text>
          ))}

          {response.followUpQuestions?.length ? (
            <>
              <Text style={styles.responseTitle}>Ask next</Text>
              {response.followUpQuestions.map((question, index) => (
                <Pressable key={index} onPress={() => setPrompt(question)}>
                  <Text style={styles.followUp}>👉 {question}</Text>
                </Pressable>
              ))}
            </>
          ) : null}
        </View>
      )}
    </ScrollView>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#f8fafc',
  },
  title: {
    fontSize: 24,
    fontWeight: '700',
    color: '#1e293b',
  },
  subtitle: {
    marginTop: 4,
    marginBottom: 16,
    color: '#475569',
  },
  input: {
    backgroundColor: '#fff',
    borderRadius: 12,
    padding: 16,
    minHeight: 120,
    textAlignVertical: 'top',
    borderWidth: 1,
    borderColor: '#e2e8f0',
  },
  button: {
    backgroundColor: '#4c51bf',
    padding: 16,
    borderRadius: 12,
    alignItems: 'center',
    marginTop: 12,
  },
  buttonText: {
    color: '#fff',
    fontWeight: '700',
  },
  responseCard: {
    marginTop: 20,
    backgroundColor: '#fff',
    borderRadius: 12,
    padding: 16,
  },
  responseTitle: {
    fontWeight: '700',
    color: '#1e293b',
    marginTop: 12,
  },
  responseText: {
    marginTop: 6,
    color: '#475569',
  },
  responseList: {
    marginTop: 4,
    color: '#334155',
  },
  followUp: {
    marginTop: 8,
    color: '#4338ca',
    fontWeight: '600',
  },
});

export default AiTutorScreen;
