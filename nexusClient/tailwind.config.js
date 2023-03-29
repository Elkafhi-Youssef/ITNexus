/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: {
      colors: {
        'bgcolor': '#EDF5FF',
        'whiteColor' : '#FFFFFF',
        'btnColor' : '#3B82F6',
      },
    },
  },
  plugins: [],
}
