import { initializeApp } from "firebase/app";
import { getAuth } from "firebase/auth";

const firebaseConfig = {
  apiKey: process.env.GOOGLE_APIKEY,
  authDomain: "a-szallitok-fb.firebaseapp.com",
  projectId: "a-szallitok-fb",
  storageBucket: "a-szallitok-fb.appspot.com",
  messagingSenderId: "145119984927",
  appId: "1:145119984927:web:35808fdd9a6a33786e042a"
};

const app = initializeApp(firebaseConfig);
const auth = getAuth(app);

