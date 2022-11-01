package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.repository.CurrentUser
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.JobDetailsFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.ProfileFragmentViewModel

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding

    private val viewModel: JobDetailsFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedJob: JobDetails? = arguments?.get("JOB") as JobDetails?
        initFragment(selectedJob)

        viewModel.getUserData(CurrentUser.user._id).observe(viewLifecycleOwner
        ) { userState ->
            render(userState)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    fun initFragment(job: JobDetails?) {
        binding.tvTitle.text = job?.title
        binding.tvDescription.text = job?.description
        Glide.with(requireContext()).load(job?.imPaths).into(binding.ivJobImage)
        binding.tvProfileRating.text = "0"
        binding.tvProfileName.text = job?.clientName
        binding.tvDate.text = job?.deliveryDate?.subSequence(0, 10)
        binding.tvPrice.text = job?.deliveryCost.toString()
        val location = job?.deliveryLocation
        binding.tvLocation.text = "${location?.country}\n${location?.postalCode}, ${location?.city}\n${location?.address}"
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