package hu.bme.aut.deliveryappforcustomers

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import hu.bme.aut.deliveryappforcustomers.databinding.ActivityLoginBinding
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.view.fragments.HistoryFragment
import hu.bme.aut.deliveryappforcustomers.view.states.UserState
import hu.bme.aut.deliveryappforcustomers.viewmodel.LoginViewModel
import java.time.LocalTime

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleAuth: ActivityResultLauncher<Intent>

    private val viewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        HistoryFragment.historyList.add(
            HistoryFragment.Companion.HistoryItem(
                HistoryFragment.Companion.HistoryType.OPENED_APP,
                LocalTime.now()
            )
        )
        binding = ActivityLoginBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val googleSignInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(R.string.client_id)).requestEmail().build()

        FirebaseApp.initializeApp(this)

        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(this, googleSignInOptions)

        val currentUser = auth.currentUser
        if (currentUser != null) {
            actionForAuthUser()
        }

        googleAuth =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { response ->
                if (response.resultCode == Activity.RESULT_OK) {
                    val result: GoogleSignInResult? = response.data?.let {
                        Auth.GoogleSignInApi.getSignInResultFromIntent(
                            it
                        )
                    }

                    if (result!!.isSuccess) {
                        Log.d("Auth", "ok")
                        val idToken = result.signInAccount?.idToken
                        auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                        actionForAuthUser()
                    } else {
                        Toast.makeText(this, "Login Unsuccessful", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }
        binding.signInButton.setOnClickListener {
            googleAuth.launch(googleSignInClient.signInIntent)
        }
    }

    private fun actionForAuthUser() {
        Log.d("LOGIN", "ACTION")
        auth.currentUser?.getIdToken(true)?.addOnSuccessListener {
            CurrentUser.token = it.token!!
            Log.d("TOKEN", it.token!!)
            viewModel.loginUser(it.token!!).observe(
                this
            ) { state ->
                when (state) {
                    is UserState.userResponseSuccess -> {
                        CurrentUser.user = state.data
                        val intent = Intent(this, ContainerActivity::class.java)
                        startActivity(intent)
                        finish()
                    }
                    is UserState.userResponseError -> {
                        Log.d("LoginFragment error", "uh oh, something went wrong")
                        Toast.makeText(this, "login failed", Toast.LENGTH_SHORT).show()
                    }
                    else -> {
                        Log.d("LoginFragment", "placeholder for loading bar")
                    }
                }
            }
        }
    }
}