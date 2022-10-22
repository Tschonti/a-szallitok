package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobDetailsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails

class AvailableJobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAvailableJobDetailsBinding

    private lateinit var selectedJob: JobDetails

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedJob = arguments?.get("JOB") as JobDetails
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