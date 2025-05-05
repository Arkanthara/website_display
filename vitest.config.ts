import { defineConfig } from "vitest/config";

export default defineConfig({
  test: {
    environment: "jsdom",
    // fileParallelism: false,
    testTimeout: 10000,
    isolate: true,
    globals: true,
    outputFile: "./result.html",
    // globalSetup: ["./app/tests/globalSetup.ts"],
    setupFiles: ["./app/tests/setup.ts"],
    coverage: {
      reporter: ["text", "lcov"],
      exclude: [
        "**/*.config.ts",
        "**/.react-router/**",
        "**/build/**",
        "**/html/**",
        "**/tests/**",
      ],
    },
  },
});
