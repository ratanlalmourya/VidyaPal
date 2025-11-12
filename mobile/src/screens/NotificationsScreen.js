import React from 'react';
import { FlatList, StyleSheet, Text, View } from 'react-native';

const NotificationsScreen = ({ route }) => {
  const { notifications = [] } = route.params || {};

  return (
    <FlatList
      style={styles.container}
      data={notifications}
      keyExtractor={(item) => item.id.toString()}
      renderItem={({ item }) => (
        <View style={[styles.card, !item.read && styles.unread]}>
          <Text style={styles.title}>{item.title}</Text>
          <Text style={styles.message}>{item.message}</Text>
        </View>
      )}
      ListEmptyComponent={<Text style={styles.empty}>No notifications yet.</Text>}
    />
  );
};

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#f8fafc',
    padding: 20,
  },
  card: {
    backgroundColor: '#fff',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
  },
  unread: {
    borderColor: '#4338ca',
    borderWidth: 1,
  },
  title: {
    fontWeight: '700',
    color: '#1e293b',
    marginBottom: 6,
  },
  message: {
    color: '#475569',
  },
  empty: {
    textAlign: 'center',
    color: '#94a3b8',
    marginTop: 40,
  },
});

export default NotificationsScreen;
