import admin from 'firebase-admin';

export const app = admin.initializeApp({
  credential: admin.credential.applicationDefault()
})