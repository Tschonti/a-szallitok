package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.adapter.JobDetailsAdapter
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.LoadingDialogManager
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobsFragmentViewModel

class AvailableJobsFragment : Fragment(), JobDetailsAdapter.OnJobSelectedListener {

    private lateinit var binding: FragmentAvailableJobsBinding

    private val viewModel: AvailableJobsFragmentViewModel by viewModels()

    private lateinit var adapter: JobDetailsAdapter

    private lateinit var loadingDialog: LottieProgressDialog

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

        adapter = JobDetailsAdapter(this)

        viewModel.getAvailableJobs().observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }

        loadingDialog = LoadingDialogManager.getLoadingDialog(requireContext())

        binding.availableJobsRecyclerView.adapter = adapter

        binding.srlJobs.setOnRefreshListener {
            viewModel.getAvailableJobs().observe(viewLifecycleOwner
            ) { jobDetailState ->
                render(jobDetailState)
            }
        }
    }

    private fun render(state: DeliveryListState) {
        when (state) {
            is DeliveryListState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryListState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                binding.srlJobs.isRefreshing = false
                adapter.addJobs(state.data)
            }
            is DeliveryListState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                binding.srlJobs.isRefreshing = false
                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.error))
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive(getString(R.string.close))
            }
        }
    }

    override fun onJobSelected(job: Delivery?) {
        Log.d("JOB", job.toString())
        val b = Bundle()
        b.putSerializable("JOB", job)
        findNavController().navigate(R.id.action_availableJobsFragment_to_availableJobDetailsFragment, b)
    }

}