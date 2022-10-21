package hu.bme.aut.android.deliveryapp.view.fragments

import android.R
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding


class MenuFragment : Fragment() {
    private lateinit var binding: FragmentMenuBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMenu.inflateMenu(hu.bme.aut.android.deliveryapp.R.menu.main_menu)
        binding.toolbarMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                hu.bme.aut.android.deliveryapp.R.id.profile -> {
                    findNavController().navigate(hu.bme.aut.android.deliveryapp.R.id.action_menuFragment_to_profileFragment)
                    true
                }
                else ->
                    true
            }
        }

        binding.cvSearchJob.setOnClickListener {
            findNavController().navigate(hu.bme.aut.android.deliveryapp.R.id.action_menuFragment_to_availableJobsFragment)
        }

        binding.cvMapJob.setOnClickListener {
            findNavController().navigate(hu.bme.aut.android.deliveryapp.R.id.action_menuFragment_to_availableJobsMapFragment)
        }

        binding.cvInProgressJob.setOnClickListener {
            findNavController().navigate(hu.bme.aut.android.deliveryapp.R.id.action_menuFragment_to_inProgressJobsFragment)
        }

        binding.cvHistoryJob.setOnClickListener {
            findNavController().navigate(hu.bme.aut.android.deliveryapp.R.id.action_menuFragment_to_historyJobsFragment)
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