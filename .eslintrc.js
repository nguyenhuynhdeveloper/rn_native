module.exports = {
  root: true,
  extends: '@react-native-community',

  rules: {
    'no-unused-vars': 'off', // Cảnh báo khi khai báo mà không sử dụng
    'no-shadow': 'off',
    'react-native/no-inline-styles': 0,
    // 'prettier/prettier': 0,    // comment lại để có  tính năng saveautofomat
    'react-hooks/rules-of-hooks': 'error',
    'react-hooks/exhaustive-deps': 'off',
  },
};
