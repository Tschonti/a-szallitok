import { initializeApp } from "firebase/app";
import { API_KEY } from "../util/Constants";

const firebaseConfig = {
  apiKey: API_KEY,
  authDomain: 'the-transporters-fb.firebaseapp.com',
  projectId: 'the-transporters-fb',
  storageBucket: 'the-transporters-fb.appspot.com',
  messagingSenderId: '408596429373',
  appId: '1:408596429373:web:e29d3124ccf72e51aac854'
};

export const app = initializeApp(firebaseConfig);

