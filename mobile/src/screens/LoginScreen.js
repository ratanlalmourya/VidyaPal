import React, { useState } from 'react';
import { Alert, Pressable, StyleSheet, Text, TextInput, View } from 'react-native';
import { apiClient, endpoints } from '../api/client';
import { useApi } from '../hooks/useApi';

const LoginScreen = ({ navigation }) => {
  const [email, setEmail] = useState('priya@vidyapal.ai');
  const [password, setPassword] = useState('password');
  const { execute, loading } = useApi((payload) => apiClient.post(endpoints.login, payload));

  const onSubmit = async () => {
    try {
      const { data } = await execute({ email, password });
      navigation.replace('Dashboard', { user: data });
    } catch (err) {
      Alert.alert('Login failed', 'Please check your credentials and try again.');
    }
  };

  return (
    <View style={styles.container}>
      <Text style={styles.logo}>VidyaPal</Text>
      <Text style={styles.subtitle}>Personalised AI powered study coach</Text>
      <View style={styles.formGroup}>
        <Text style={styles.label}>Email</Text>
        <TextInput
          style={styles.input}
          autoCapitalize="none"
          value={email}
          onChangeText={setEmail}
          keyboardType="email-address"
        />
      </View>
      <View style={styles.formGroup}>
        <Text style={styles.label}>Password</Text>
        <TextInput style={styles.input} secureTextEntry value={password} onChangeText={setPassword} />
      </View>
      <Pressable style={[styles.button, loading && styles.disabled]} onPress={onSubmit} disabled={loading}>
        <Text style={styles.buttonText}>{loading ? 'Signing in…' : 'Sign In'}</Text>
      </Pressable>
      <Text style={styles.helper}>Try switching users from the dashboard menu.</Text>
    </View>
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 24,
    justifyContent: 'center',
    backgroundColor: '#f8fafc',
  },
  logo: {
    fontSize: 32,
    fontWeight: '800',
    color: '#312e81',
    textAlign: 'center',
  },
  subtitle: {
    textAlign: 'center',
    color: '#475569',
    marginBottom: 32,
    marginTop: 12,
  },
  formGroup: {
    marginBottom: 16,
  },
  label: {
    fontWeight: '600',
    marginBottom: 6,
    color: '#1e293b',
  },
  input: {
    backgroundColor: '#fff',
    borderRadius: 8,
    borderColor: '#cbd5f5',
    borderWidth: 1,
    paddingHorizontal: 14,
    paddingVertical: 12,
  },
  button: {
    backgroundColor: '#4c51bf',
    paddingVertical: 14,
    borderRadius: 8,
    marginTop: 8,
  },
  disabled: {
    opacity: 0.7,
  },
  buttonText: {
    textAlign: 'center',
    color: '#fff',
    fontWeight: '700',
    fontSize: 16,
  },
  helper: {
    marginTop: 16,
    textAlign: 'center',
    color: '#64748b',
  },
});

export default LoginScreen;
