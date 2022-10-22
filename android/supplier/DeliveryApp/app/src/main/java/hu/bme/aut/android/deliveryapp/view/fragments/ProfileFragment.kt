package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentProfileBinding
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.view.UserState
import hu.bme.aut.android.deliveryapp.view.UserWithRatingState
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobsFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.ProfileFragmentViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserData(10).observe(viewLifecycleOwner
        ) { userState ->
            render(userState)
        }

        viewModel.getUserRating(10).observe(viewLifecycleOwner
        ) { userState ->
            render(userState)
        }

        binding.btnVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_vehicleFragment)
        }

        loadingDialog = LottieProgressDialog(
            context = requireContext(),
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_1,
            title = null,
            titleVisible = null
        )

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentProfileBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun render(state: UserState) {
        when (state) {
            is UserState.inProgress -> {
                loadingDialog.show()
            }
            is UserState.userResponseSuccess -> {
                Glide.with(requireContext()).load(state.data.profilePictureUrl).into(binding.ivProfileImage);
                binding.tvName.text = "Name: ${state.data.name}"
                loadingDialog.dismiss()
            }
            is UserState.userResponseError -> {
                loadingDialog.dismiss()
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun render(state: UserWithRatingState) {
        when (state) {
            is UserWithRatingState.inProgress -> {
            }
            is UserWithRatingState.userResponseSuccess -> {
                binding.tvRating.text = "Rating: ${state.data.avgRating}"
            }
            is UserWithRatingState.userResponseError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}