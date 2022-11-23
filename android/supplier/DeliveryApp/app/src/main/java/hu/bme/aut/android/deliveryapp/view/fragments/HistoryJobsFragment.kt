package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.adapter.JobDetailsAdapter
import hu.bme.aut.android.deliveryapp.databinding.FragmentHistoryJobsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.LoadingDialogManager
import hu.bme.aut.android.deliveryapp.view.states.DeliveryInProgressState
import hu.bme.aut.android.deliveryapp.viewmodel.HistoryJobsFragmentViewModel


class HistoryJobsFragment : Fragment(), JobDetailsAdapter.OnJobSelectedListener {

    private lateinit var binding: FragmentHistoryJobsBinding

    private val viewModel: HistoryJobsFragmentViewModel by viewModels()

    private lateinit var adapter: JobDetailsAdapter

    private lateinit var loadingDialog: LottieProgressDialog


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentHistoryJobsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = JobDetailsAdapter(this)

        viewModel.getUserHistory().observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }

        loadingDialog = LoadingDialogManager.getLoadingDialog(requireContext())

        binding.historyRecyclerView.adapter = adapter
    }

    private fun render(state: DeliveryInProgressState) {
        when (state) {
            is DeliveryInProgressState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryInProgressState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                adapter.addJobs(state.data.map { dip -> dip.delivery })
            }
            is DeliveryInProgressState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.error))
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive(getString(R.string.close))
            }
        }
    }

    override fun onJobSelected(job: Delivery?) {
        val b = Bundle()
        b.putSerializable("JOB", job)
        findNavController().navigate(R.id.action_historyJobsFragment_to_jobDetailsFragment, b)
    }
}