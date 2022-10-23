package hu.bme.aut.android.deliveryapp.view.fragments

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
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.google.android.gms.auth.api.Auth
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInClient
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.google.android.gms.auth.api.signin.GoogleSignInResult
import com.google.firebase.FirebaseApp
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.GoogleAuthProvider
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentLoginBinding
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.LoginFragmentViewModel


class LoginFragment : Fragment() {
    private lateinit var binding: FragmentLoginBinding

    private lateinit var auth: FirebaseAuth
    private lateinit var googleSignInClient: GoogleSignInClient
    private lateinit var googleAuth: ActivityResultLauncher<Intent>
    private lateinit var loadingDialog: LottieProgressDialog


    private val viewModel: LoginFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLoginBinding.inflate(layoutInflater, container, false);
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val googleSignInOptions = GoogleSignInOptions.Builder(
            GoogleSignInOptions.DEFAULT_SIGN_IN
        ).requestIdToken(getString(R.string.client_id))
            .requestEmail()
            .build()

        FirebaseApp.initializeApp(requireContext())

        loadingDialog = LottieProgressDialog(
            context = requireContext(),
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_8,
            title = null,
            titleVisible = null
        )

        auth = FirebaseAuth.getInstance()
        googleSignInClient= GoogleSignIn.getClient(requireContext(), googleSignInOptions);

        val currentUser = auth.currentUser
        if(currentUser != null){
            actionForAuthUser()
        }

        googleAuth = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                val result: GoogleSignInResult? = result.data?.let {
                    Auth.GoogleSignInApi.getSignInResultFromIntent(
                        it
                    )
                }
                if (result!!.isSuccess) {
                    val idToken = result?.signInAccount?.idToken
                    auth.signInWithCredential(GoogleAuthProvider.getCredential(idToken, null))
                    actionForAuthUser()
                } else {
                    Toast.makeText(requireContext(), "Login Unsuccessful", Toast.LENGTH_SHORT).show()
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
        auth.currentUser?.getIdToken(false)?.addOnSuccessListener {
            viewModel.loginUser(it.toString()).observe(viewLifecycleOwner
            ) { state ->
                render(state)
            }
        }
    }

    private fun render(state: UserState) {
        when (state) {
            is UserState.inProgress -> {
                loadingDialog.show()
            }
            is UserState.userResponseSuccess -> {
                loadingDialog.dismiss()
                findNavController().navigate(R.id.action_loginFragment_to_menuFragment)
            }
            is UserState.userResponseError -> {
                loadingDialog.dismiss()
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

}