package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.util.Log
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
import hu.bme.aut.android.deliveryapp.adapter.JobDetailsAdapter
import hu.bme.aut.android.deliveryapp.databinding.FragmentAvailableJobsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
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

        adapter = JobDetailsAdapter(requireContext(), this)

        viewModel.getAvailableJobs(null, null,null, null).observe(viewLifecycleOwner
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
            fileName = LottieProgressDialog.SAMPLE_8,
            title = null,
            titleVisible = null
        )

        binding.availableJobsRecyclerView.adapter = adapter
    }

    private fun render(state: DeliveryListState) {
        when (state) {
            is DeliveryListState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryListState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                Log.i("DATA ARRIVED", state.data.toString())
                adapter.addJobs(state.data)
            }
            is DeliveryListState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

    override fun onJobSelected(job: Delivery?) {

        val b = Bundle()
        b.putSerializable("JOB", job)
        findNavController().navigate(R.id.action_availableJobsFragment_to_availableJobDetailsFragment, b)
    }

}