package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.adapter.JobDetailsAdapter
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobsBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobsFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobsMapFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.HistoryJobsFragmentViewModel

class AvailableJobsFragment : Fragment(), JobDetailsAdapter.OnJobSelectedListener {

    private lateinit var binding: FragmentAvailableJobsBinding

    private val viewModel: AvailableJobsFragmentViewModel by viewModels()

    private val adapter: JobDetailsAdapter = JobDetailsAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentAvailableJobsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getAvailableJobs(null, null,null, null).observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }

        binding.availableJobsRecyclerView.adapter = adapter
    }

    private fun render(state: JobDetailState) {
        when (state) {
            is JobDetailState.inProgress -> {
                Toast.makeText(context, "Loading Data", Toast.LENGTH_SHORT).show()
            }
            is JobDetailState.jobDetailsResponseSuccess -> {
                Log.d("AVAILABLE_JOBS", state.data.toString())
                adapter.addJobs(state.data)
            }
            is JobDetailState.jobDetailsResponseError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onJobSelected(job: JobDetails?) {
        TODO("Not yet implemented")
    }

}