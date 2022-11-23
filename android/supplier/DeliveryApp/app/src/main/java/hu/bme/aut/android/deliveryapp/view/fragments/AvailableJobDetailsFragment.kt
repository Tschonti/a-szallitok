package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobDetailsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentJobDetailsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobDetailsFragmentViewModel

class AvailableJobDetailsFragment : Fragment() {

    private lateinit var binding: FragmentAvailableJobDetailsBinding

    private var selectedJob: Delivery? = null

    private val viewModel: AvailableJobDetailsFragmentViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedJob = arguments?.get("JOB") as Delivery?

        val fragment: JobDetailsFragment = childFragmentManager.findFragmentById(R.id.jobDetailsFragmentOnAvailableJob) as JobDetailsFragment
        fragment.initFragment(selectedJob)

        binding.btnMakeContract.setOnClickListener {
            viewModel.requestJob(selectedJob!!._id).observe(viewLifecycleOwner
            ) { deliveryState ->
                render(deliveryState)
            }
        }

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAvailableJobDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun render(state: DeliveryState) {
        when (state) {
            is DeliveryState.inProgress -> {
            }
            is DeliveryState.deliveriesResponseSuccess -> {
                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.success))
                    .body(getString(R.string.in_pending))
                    .icon(R.drawable.success)
                    .onPositive(getString(R.string.close))
            }
            is DeliveryState.deliveriesResponseError -> {
                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.error))
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive(getString(R.string.close))
            }
        }
    }
}