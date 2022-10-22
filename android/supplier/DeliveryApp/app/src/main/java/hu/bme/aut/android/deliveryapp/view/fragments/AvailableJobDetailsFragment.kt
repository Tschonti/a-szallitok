package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobDetailsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails

class AvailableJobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAvailableJobDetailsBinding

    private var selectedJob: JobDetails? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedJob = arguments?.get("JOB") as JobDetails?

        val fragment: JobDetailsFragment = childFragmentManager.findFragmentById(R.id.jobDetailsFragmentOnAvailableJob) as JobDetailsFragment
        fragment.initFragment(selectedJob)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAvailableJobDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}