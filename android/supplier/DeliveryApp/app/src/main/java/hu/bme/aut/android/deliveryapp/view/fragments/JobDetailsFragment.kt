package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val selectedJob: JobDetails? = arguments?.get("JOB") as JobDetails?
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

    fun initFragment(job: JobDetails?) {
        binding.tvTitle.text = job?.title
        binding.tvDescription.text = job?.description
        Glide.with(requireContext()).load(job?.imPaths).into(binding.ivProfileImage)
        binding.tvProfileRating.text = "0"
        binding.tvProfileName.text = job?.clientName
        binding.tvDate.text = "Delivery date: ${job?.deliveryDate?.subSequence(0, 10)}"
        binding.tvPrice.text = "Delivery cost: ${job?.deliveryCost.toString()}"
        val location = job?.deliveryLocation
        binding.tvLocation.text = "Delivery location:\n${location?.country}\n${location?.postalCode}, ${location?.city}\n${location?.address}"
    }
}