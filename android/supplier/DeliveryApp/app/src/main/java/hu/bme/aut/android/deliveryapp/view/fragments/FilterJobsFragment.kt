package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import hu.bme.aut.android.deliveryapp.databinding.FragmentFilterJobsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding

class FilterJobsFragment : Fragment() {
    private lateinit var binding: FragmentFilterJobsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentFilterJobsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}