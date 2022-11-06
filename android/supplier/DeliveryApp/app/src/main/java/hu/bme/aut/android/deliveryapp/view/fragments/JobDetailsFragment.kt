package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.JobDetailsFragmentViewModel

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding

    private val viewModel: JobDetailsFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedJob: Delivery? = arguments?.get("JOB") as Delivery?
        initFragment(selectedJob)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun initFragment(job: Delivery?) {
        binding.tvTitle.text = job?.title
        binding.tvDescription.text = job?.description
        binding.tvDate.text = "${job?.pickUpFrom?.subSequence(0, 10)} - ${job?.pickUpUntil?.subSequence(0, 10)}"
        binding.tvPrice.text = job?.price.toString()
        val locationSource = job?.sourceLocation
        binding.tvLocationSource.text = "${locationSource?.country}\n${locationSource?.postalCode}, ${locationSource?.city}\n${locationSource?.address}"
        val locationDestination = job?.destinationLocation
        binding.tvLocationDestination.text = "${locationDestination?.country}\n${locationDestination?.postalCode}, ${locationDestination?.city}\n${locationDestination?.address}"

        job?.clientUser?.let {
            viewModel.getUserData(it).observe(viewLifecycleOwner
            ) { userState ->
                render(userState)
            }
        }
    }

    private fun render(state: UserState) {
        when (state) {
            is UserState.inProgress -> {
            }
            is UserState.userResponseSuccess -> {
                Glide.with(requireContext()).load(state.data?.profilePictureUrl).into(binding.ivProfileImage)
                binding.tvProfileName.text = state.data.name
                binding.tvProfileRating.text = state.data.avgRating.toString()
            }
            is UserState.userResponseError -> {
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }
}