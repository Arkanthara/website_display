import React from 'react';
import { describe, expect, test } from 'vitest';
import routes from '../routes';

describe('Routes Configuration', () => {
  test('has correct routes defined', () => {
    expect(routes).toBeDefined();
    expect(routes.length).toBeGreaterThan(0);
  });
});
