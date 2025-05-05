import { beforeAll, beforeEach, afterEach, afterAll } from 'vitest';
import { server, waitForMSW } from './server';
import { cleanup } from '@testing-library/react';

beforeAll(() => server.listen());
afterEach(() => {
  cleanup();
  server.resetHandlers();
});
afterAll(() => server.close());
