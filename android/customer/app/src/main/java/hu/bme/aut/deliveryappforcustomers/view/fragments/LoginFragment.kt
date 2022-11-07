package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.deliveryappforcustomers.CurrentUserPlain
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.api.RetrofitClient
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentLoginBinding
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.view.states.UserState
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding;

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleAuth: ActivityResultLauncher<Intent>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val googleSignInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(R.string.client_id)).requestEmail().build()

        FirebaseApp.initializeApp(requireContext())

        auth = FirebaseAuth.getInstance()
        googleSignInClient = GoogleSignIn.getClient(requireContext(), googleSignInOptions);

        val currentUser = auth.currentUser
        if (currentUser != null) {
            actionForAuthUser()
        }

        googleAuth =
            registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
                if (result.resultCode == Activity.RESULT_OK) {
                    val result: GoogleSignInResult? = result.data?.let {
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
                        Toast.makeText(requireContext(), "Login Unsuccessful", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            }



        binding.signInButton.setOnClickListener {
            googleLogin()
        }
    }

    private fun googleLogin() {
        googleAuth.launch(googleSignInClient.signInIntent)
    }

    private fun actionForAuthUser() {
        Log.d("Auth", "ok")
        auth.currentUser?.getIdToken(true)?.addOnSuccessListener {
            CurrentUser.token = it.token!!
            Log.d("TOKEN", it.token!!)
            val api = RetrofitClient.api
            api.loginUser("Bearer " + it.token!!).enqueue(object : Callback<User> {
                override fun onResponse(
                    call: Call<User>, response: Response<User>
                ) {
                    if (response.isSuccessful) {
                        //TODO move to the menu
                        Log.d("Login", "Successful login: ${response.body()}")
                        CurrentUser.user = response.body()!!
                    } else {
                        //TODO handle the error properly
                        Log.d("Login", "login unsuccessful, error: ${response.errorBody()}")
                    }
                }

                override fun onFailure(call: Call<User>, throwable: Throwable) {
                    Log.d("ERROR", "e: " + throwable.message.toString())
                }
            })
        }
    }

    private fun render(state: UserState) {
        when (state) {
            is UserState.userResponseSuccess -> {
                CurrentUser.user = state.data
            }
            is UserState.userResponseError -> {
                Log.d("LoginFragment error", "uh oh, something went wrong")
            }
            else -> {
                Log.d("LoginFragment", "placeholder for loading bar")
            }
        }
    }
}