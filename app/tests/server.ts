import { setupServer } from 'msw/node';
import { http, HttpResponse } from 'msw';

const mockData = [
  {
    time: new Date().toISOString(),
    serialNumber: "ABC123",
  },
  {
    time: new Date().toISOString(),
    serialNumber: "XYZ789",
  },
  {
    time: new Date().toISOString(),
    serialNumber: "LMN456",
  },
];
const tempMockData = [
  {
    time: new Date().toISOString(),
    serialNumber: "Gnyahaha",
  }
];

export const handlers = [
  http.get('http://localhost:8080/api/phony', () => HttpResponse.json(mockData)),
  http.get('http://localhost:8080/api/phony/findByTimeBetween', () => HttpResponse.json(tempMockData)),
  http.get('http://localhost:8080/api/phony/findBySerialNumber', () => HttpResponse.json(tempMockData)),

];

export const server = setupServer(...handlers);

export const waitForMSW = async () => {
  for (let i = 0; i < 10; i++) {
    try {
      const res = await fetch("http://localhost:8080/api/phony");
      if (res.ok) return true;
    } catch (_) {}
    await new Promise((r) => setTimeout(r, 50));
  }
  throw new Error("MSW did not initialize in time");
};
