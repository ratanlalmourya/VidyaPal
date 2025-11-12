import React from 'react';
import renderer from 'react-test-renderer';
import App from '../App';

describe('App root', () => {
  it('renders without crashing', () => {
    expect(() => renderer.create(<App />)).not.toThrow();
  });
});
