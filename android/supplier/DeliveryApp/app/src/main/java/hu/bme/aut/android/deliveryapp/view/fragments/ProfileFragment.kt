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
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentProfileBinding
import hu.bme.aut.android.deliveryapp.model.User
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.view.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobsFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.ProfileFragmentViewModel

class ProfileFragment : Fragment() {

    private lateinit var binding: FragmentProfileBinding

    private val viewModel: ProfileFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getUserData(10).observe(viewLifecycleOwner
        ) { userState ->
            render(userState)
        }

        binding.btnVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_profileFragment_to_vehicleFragment)
        }

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
                Toast.makeText(context, "Loading Data", Toast.LENGTH_SHORT).show()
            }
            is UserState.userResponseSuccess -> {
                binding.tvName.text = "${state.data.firstName} ${state.data.lastName}"
                binding.tvRating.text = "Rating: TODO"
            }
            is UserState.userResponseError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}