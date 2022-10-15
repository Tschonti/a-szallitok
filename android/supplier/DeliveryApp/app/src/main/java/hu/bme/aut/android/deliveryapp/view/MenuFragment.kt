package hu.bme.aut.android.deliveryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding
class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cvSearchJob.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_availableJobsFragment)
        }

        binding.cvMapJob.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_availableJobsMapFragment)
        }

        binding.cvInProgressJob.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_inProgressJobsFragment)
        }

        binding.cvHistoryJob.setOnClickListener {
            findNavController().navigate(R.id.action_menuFragment_to_historyJobsFragment)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMenuBinding.inflate(layoutInflater, container, false)
        return binding.root
    }


}