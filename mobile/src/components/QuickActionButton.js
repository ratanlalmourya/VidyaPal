import React from 'react';
import { Pressable, StyleSheet, Text } from 'react-native';

const QuickActionButton = ({ label, onPress }) => (
  <Pressable style={styles.button} onPress={onPress}>
    <Text style={styles.label}>{label}</Text>
  </Pressable>
);

const styles = StyleSheet.create({
  button: {
    backgroundColor: '#4c51bf',
    paddingVertical: 12,
    paddingHorizontal: 16,
    borderRadius: 8,
    marginRight: 12,
  },
  label: {
    color: '#fff',
    fontWeight: '600',
  },
});

export default QuickActionButton;
