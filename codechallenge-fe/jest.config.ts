/* eslint-disable */
export default {
  clearMocks: true,
  displayName: 'codechallange-fe',
  testEnvironment: 'jsdom',
  preset: './jest.preset.js',
  setupFilesAfterEnv: ['<rootDir>/src/test-setup.ts'],
  coverageDirectory: './coverage/apps/codechallange-fe',
  transform: {
    "node_modules/nanoid/.+\\.(j|t)s?$": "babel-jest",
    "node_modules/flatpickr/.+\\.(j|t)s?$": "babel-jest",
    '^.+\\.(ts|mjs|js|html)$': [
      'jest-preset-angular',
      {
        tsconfig: '<rootDir>/tsconfig.spec.json',
        stringifyContentPathRegex: '\\.(html|svg)$',
      },
    ],
  },
  transformIgnorePatterns: [
    'node_modules/(?!.*\\.mjs$|nanoid|flatpickr)'
],
  snapshotSerializers: [
    'jest-preset-angular/build/serializers/no-ng-attributes',
    'jest-preset-angular/build/serializers/ng-snapshot',
    'jest-preset-angular/build/serializers/html-comment',
  ],
};
