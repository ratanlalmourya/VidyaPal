import React, { useEffect, useState } from 'react';
import { Alert, Pressable, StyleSheet, Text, View } from 'react-native';
import { apiClient, endpoints } from '../api/client';

const QuizScreen = ({ route }) => {
  const { quizId, roomTitle } = route.params;
  const [quiz, setQuiz] = useState(null);
  const [selected, setSelected] = useState(null);
  const [result, setResult] = useState(null);

  useEffect(() => {
    apiClient
      .get(endpoints.quiz(quizId))
      .then((response) => setQuiz(response.data))
      .catch((error) => Alert.alert('Error', error.message));
  }, [quizId]);

  const submit = async () => {
    if (selected === null) {
      Alert.alert('Select an option', 'Choose one of the answers before submitting.');
      return;
    }
    const { data } = await apiClient.post(endpoints.quizSubmit(quizId), {
      selectedOptionIndex: selected,
    });
    setResult(data);
  };

  if (!quiz) {
    return (
      <View style={styles.loading}>
        <Text>Loading quiz…</Text>
      </View>
    );
  }

  return (
    <View style={styles.container}>
      <Text style={styles.title}>{roomTitle}</Text>
      <Text style={styles.question}>{quiz.question}</Text>
      {quiz.options?.map((option, index) => (
        <Pressable
          key={index}
          style={[styles.option, selected === index && styles.optionSelected]}
          onPress={() => setSelected(index)}
        >
          <Text style={styles.optionText}>{option}</Text>
        </Pressable>
      ))}

      <Pressable style={styles.submitButton} onPress={submit}>
        <Text style={styles.submitText}>Submit</Text>
      </Pressable>

      {result && (
        <View style={[styles.resultCard, result.correct ? styles.correct : styles.incorrect]}>
          <Text style={styles.resultTitle}>{result.correct ? 'Correct 🎉' : 'Almost there'}</Text>
          <Text style={styles.resultMessage}>{result.explanation}</Text>
          <Text style={styles.resultTitle}>Suggested next steps</Text>
          {result.recommendations.map((item, index) => (
            <Text key={index} style={styles.recommendation}>• {item}</Text>
          ))}
        </View>
      )}
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 20,
    backgroundColor: '#f8fafc',
  },
  loading: {
    flex: 1,
    justifyContent: 'center',
    alignItems: 'center',
  },
  title: {
    color: '#475569',
  },
  question: {
    fontSize: 20,
    fontWeight: '700',
    color: '#1e293b',
    marginVertical: 16,
  },
  option: {
    backgroundColor: '#fff',
    padding: 16,
    borderRadius: 12,
    borderWidth: 1,
    borderColor: '#cbd5f5',
    marginBottom: 12,
  },
  optionSelected: {
    borderColor: '#4c51bf',
    backgroundColor: '#eef2ff',
  },
  optionText: {
    color: '#1e293b',
  },
  submitButton: {
    backgroundColor: '#4338ca',
    padding: 16,
    borderRadius: 12,
    alignItems: 'center',
    marginTop: 8,
  },
  submitText: {
    color: '#fff',
    fontWeight: '700',
  },
  resultCard: {
    marginTop: 20,
    padding: 16,
    borderRadius: 12,
  },
  correct: {
    backgroundColor: '#dcfce7',
  },
  incorrect: {
    backgroundColor: '#fee2e2',
  },
  resultTitle: {
    fontWeight: '700',
    color: '#1e293b',
    marginTop: 8,
  },
  resultMessage: {
    marginTop: 4,
    color: '#334155',
  },
  recommendation: {
    marginTop: 4,
    color: '#334155',
  },
});

export default QuizScreen;
