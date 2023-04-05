/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{html,ts}",
  ],
  theme: {
    extend: { width: {
      '64': '16rem', // Change the width to the desired value
    },
      colors: {
        'bgcolor': '#EDF5FF',
        'whiteColor' : '#FFFFFF',
        'btnColor' : '#3B82F6',
      },
    },
  },
  plugins: [],
}
