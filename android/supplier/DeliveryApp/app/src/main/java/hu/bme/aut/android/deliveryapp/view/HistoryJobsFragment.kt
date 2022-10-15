package hu.bme.aut.android.deliveryapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentHistoryJobsBinding

class HistoryJobsFragment : Fragment() {

    private lateinit var binding: FragmentHistoryJobsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryJobsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

}