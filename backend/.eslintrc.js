module.exports = {
  env: {
    browser: true,
    es2021: true,
  },
  extends: 'standard-with-typescript',
  overrides: [
  ],
  parserOptions: {
    ecmaVersion: 2018,
    project: './tsconfig.json',
    sourceType: 'module',
  },
  rules: {
    'max-len': [2, { code: 120 },],
    semi: [2, 'always',],
    'comma-dangle': ['error', {
      arrays: 'always',
      objects: 'always-multiline',
      imports: 'always-multiline',
      exports: 'never',
      functions: 'never',
    },],
    '@typescript-eslint/semi': 'off',
  },
};
