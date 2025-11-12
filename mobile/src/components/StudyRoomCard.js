import React from 'react';
import { StyleSheet, Text, View } from 'react-native';

const StudyRoomCard = ({ room, onPress }) => {
  return (
    <View style={[styles.card, room.bookmarked && styles.bookmarked]} onTouchEnd={onPress}>
      <View style={styles.headerRow}>
        <Text style={styles.title}>{room.title}</Text>
        <Text style={styles.focusMode}>{room.focusMode?.replace('_', ' ')}</Text>
      </View>
      <Text style={styles.nextSession}>Next session: {room.nextSession?.slice(0, 16).replace('T', ' ')}</Text>
      <View style={styles.progressRow}>
        <View style={styles.progressBar}>
          <View style={[styles.progressFill, { width: `${Math.round(room.completionRate * 100)}%` }]} />
        </View>
        <Text style={styles.progressText}>{Math.round(room.completionRate * 100)}%</Text>
      </View>
    </View>
  );
};

const styles = StyleSheet.create({
  card: {
    backgroundColor: '#ffffff',
    borderRadius: 12,
    padding: 16,
    marginBottom: 12,
    shadowColor: '#000',
    shadowOffset: { width: 0, height: 2 },
    shadowOpacity: 0.1,
    shadowRadius: 4,
    elevation: 3,
  },
  bookmarked: {
    borderColor: '#4c51bf',
    borderWidth: 1,
  },
  headerRow: {
    flexDirection: 'row',
    justifyContent: 'space-between',
    alignItems: 'center',
  },
  title: {
    fontSize: 18,
    fontWeight: '600',
    color: '#1a202c',
  },
  focusMode: {
    fontSize: 12,
    fontWeight: '500',
    color: '#4c51bf',
  },
  nextSession: {
    marginTop: 8,
    color: '#4a5568',
  },
  progressRow: {
    flexDirection: 'row',
    alignItems: 'center',
    marginTop: 12,
  },
  progressBar: {
    flex: 1,
    height: 8,
    backgroundColor: '#e2e8f0',
    borderRadius: 4,
    marginRight: 8,
  },
  progressFill: {
    height: 8,
    backgroundColor: '#4c51bf',
    borderRadius: 4,
  },
  progressText: {
    width: 48,
    textAlign: 'right',
    fontWeight: '600',
    color: '#2d3748',
  },
});

export default StudyRoomCard;
