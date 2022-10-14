import { initializeApp } from "firebase/app";

const firebaseConfig = {
  apiKey: "AIzaSyCqBy6rZaM0p_3_MRrKanwmsyl65QVEhVw",    // TODO .env-be
  authDomain: "a-szallitok-fb.firebaseapp.com",
  projectId: "a-szallitok-fb",
  storageBucket: "a-szallitok-fb.appspot.com",
  messagingSenderId: "145119984927",
  appId: "1:145119984927:web:35808fdd9a6a33786e042a"
};

export const app = initializeApp(firebaseConfig);