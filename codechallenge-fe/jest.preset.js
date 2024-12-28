
module.exports = {
    coverageReporters: [
        'clover',
        'json',
        'lcov',
        'html',
        ['text', { skipFull: true }],
    ],
    coveragePathIgnorePatterns: [
        'generated_sources',
        'node_modules',
        'test-config',
        'interfaces',
        '.module.ts',
        '.mock.ts'],
};
