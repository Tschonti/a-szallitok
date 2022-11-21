package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.adapter.ActiveTransportsAdapter
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser.user
import hu.bme.aut.deliveryappforcustomers.viewmodel.ActiveTransportsViewModel
import retrofit2.Response.error

class ActiveTransportsFragment : Fragment() {

    private lateinit var binding: ActiveTransportsFragmentBinding

    private val viewModel: ActiveTransportsViewModel by viewModels()

    private lateinit var adapter: ActiveTransportsAdapter

    //private lateinit var loadingDialog: LottieProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active_transports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

      // adapter = JobDetailsAdapter(requireContext(), this)

        //TODO change to valid id
        viewModel.getActiveTransports(0/*CurrentUser.user._id*/).observe(viewLifecycleOwner
        ) { jobDetailState ->
           // render(jobDetailState)
        }

        //binding.availableJobsRecyclerView.adapter = adapter
    }

   /* private fun render(state: TransportListState) {
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
    }*/

/*    override fun onTransportSelected(job: Delivery?) {
     *//*   Log.d("JOB", job.toString())
        val b = Bundle()
        b.putSerializable("JOB", job)
        findNavController().navigate(R.id.action_availableJobsFragment_to_availableJobDetailsFragment, b)*//*
        // TODO: implement function
    }*/

}