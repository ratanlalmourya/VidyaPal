import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

const SectionHeader = ({ title, subtitle }) => (
  <View style={styles.container}>
    <Text style={styles.title}>{title}</Text>
    {subtitle ? <Text style={styles.subtitle}>{subtitle}</Text> : null}
  </View>
);

const styles = StyleSheet.create({
  container: {
    marginBottom: 12,
  },
  title: {
    fontSize: 20,
    fontWeight: '700',
    color: '#1a202c',
  },
  subtitle: {
    marginTop: 4,
    color: '#4a5568',
  },
});

export default SectionHeader;
