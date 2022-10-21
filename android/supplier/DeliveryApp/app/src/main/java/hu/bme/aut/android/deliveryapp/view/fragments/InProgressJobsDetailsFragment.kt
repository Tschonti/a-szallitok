package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobsMapBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentInProgressDetailsBinding

class InProgressJobsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentInProgressDetailsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInProgressDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}