package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.adapter.DeliveriesInProgressAdapter
import hu.bme.aut.android.deliveryapp.adapter.JobDetailsAdapter
import hu.bme.aut.android.deliveryapp.databinding.FragmentInProgressJobsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryInProgress
import hu.bme.aut.android.deliveryapp.repository.CurrentUser
import hu.bme.aut.android.deliveryapp.view.LoadingDialogManager
import hu.bme.aut.android.deliveryapp.view.states.DeliveryInProgressState
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState
import hu.bme.aut.android.deliveryapp.viewmodel.InProgressJobsFragmentViewModel

class InProgressJobsFragment : Fragment(), DeliveriesInProgressAdapter.OnDeliveryInProgressSelectedListener {

    private lateinit var binding: FragmentInProgressJobsBinding

    private val viewModel: InProgressJobsFragmentViewModel by viewModels()

    private lateinit var adapter: DeliveriesInProgressAdapter

    private lateinit var loadingDialog: LottieProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInProgressJobsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = DeliveriesInProgressAdapter(this)

        viewModel.getJobsInProgress().observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }

        loadingDialog = LoadingDialogManager.getLoadingDialog(requireContext())

        binding.inProgressJobsRecyclerView.adapter = adapter

        binding.srlJobs.setOnRefreshListener {
            viewModel.getJobsInProgress().observe(viewLifecycleOwner
            ) { jobDetailState ->
                render(jobDetailState)
            }
        }
    }

    private fun render(state: DeliveryInProgressState) {
        when (state) {
            is DeliveryInProgressState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryInProgressState.deliveriesResponseSuccess -> {
                adapter.addDeliveries(state.data)
                loadingDialog.dismiss()
                binding.srlJobs.isRefreshing = false
            }
            is DeliveryInProgressState.deliveriesResponseError -> {
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

    override fun onDeliveryInProgressSelected(delivery: DeliveryInProgress?) {
        val b = Bundle()
        b.putSerializable("JOB", delivery)
        findNavController().navigate(R.id.action_inProgressJobsFragment_to_inProgressJobsDetailsFragment, b)
    }


}