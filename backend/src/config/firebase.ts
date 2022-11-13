import * as firebase from 'firebase-admin'
import dotenv from 'dotenv'

dotenv.config()

const params = {
  type: 'service_account',
  projectId: 'the-transporters-fb',
  privateKeyId: process.env.PRIVATE_KEY_ID,
  privateKey: process.env.PRIVATE_KEY?.replace(/\\n/g, '\n'),
  clientEmail: 'firebase-adminsdk-y08ow@the-transporters-fb.iam.gserviceaccount.com',
  clientId: process.env.CLIENT_ID,
  authUri: 'https://accounts.google.com/o/oauth2/auth',
  tokenUri: 'https://oauth2.googleapis.com/token',
  authProviderX509CertUrl: 'https://www.googleapis.com/oauth2/v1/certs',
  clientC509CertUrl:
  // eslint-disable-next-line max-len
  'https://www.googleapis.com/robot/v1/metadata/x509/firebase-adminsdk-y08ow%40the-transporters-fb.iam.gserviceaccount.com'
}
export const app = firebase.initializeApp({
  credential: firebase.credential.cert(params)
})
