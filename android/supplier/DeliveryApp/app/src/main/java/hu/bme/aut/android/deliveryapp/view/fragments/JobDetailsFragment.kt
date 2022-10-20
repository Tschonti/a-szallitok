package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentMenuBinding

class JobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentJobDetailsBinding

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentJobDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }
}