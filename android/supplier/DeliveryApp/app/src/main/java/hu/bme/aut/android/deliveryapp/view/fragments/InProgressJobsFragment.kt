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
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.adapter.JobDetailsAdapter
import hu.bme.aut.android.deliveryapp.databinding.FragmentHistoryJobsBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentInProgressJobsBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.view.JobDetailState
import hu.bme.aut.android.deliveryapp.viewmodel.HistoryJobsFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.InProgressJobsFragmentViewModel

class InProgressJobsFragment : Fragment(), JobDetailsAdapter.OnJobSelectedListener {

    private lateinit var binding: FragmentInProgressJobsBinding

    private val viewModel: InProgressJobsFragmentViewModel by viewModels()

    private lateinit var adapter: JobDetailsAdapter

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

        adapter = JobDetailsAdapter(requireContext(), this)

        viewModel.getJobsInProgress(10).observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }

        loadingDialog = LottieProgressDialog(
            context = requireContext(),
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_1,
            title = null,
            titleVisible = null
        )

        binding.inProgressJobsRecyclerView.adapter = adapter
    }

    private fun render(state: JobDetailState) {
        when (state) {
            is JobDetailState.inProgress -> {
                loadingDialog.show()
            }
            is JobDetailState.jobDetailsResponseSuccess -> {
                adapter.addJobs(state.data)
                loadingDialog.dismiss()
            }
            is JobDetailState.jobDetailsResponseError -> {
                loadingDialog.dismiss()
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onJobSelected(job: JobDetails?) {
        val b = Bundle()
        b.putSerializable("JOB", job)
        findNavController().navigate(R.id.action_inProgressJobsFragment_to_inProgressJobsDetailsFragment, b)
    }

}