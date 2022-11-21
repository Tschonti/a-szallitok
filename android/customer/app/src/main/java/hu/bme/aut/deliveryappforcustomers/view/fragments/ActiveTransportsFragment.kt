package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.deliveryappforcustomers.adapter.ActiveTransportsAdapter
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentActiveTransportsBinding
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.viewmodel.ActiveTransportsViewModel

class ActiveTransportsFragment : Fragment(), ActiveTransportsAdapter.onTransportSelectedListener {

    private lateinit var binding: FragmentActiveTransportsBinding

    private val viewModel: ActiveTransportsViewModel by viewModels()

    private lateinit var adapter: ActiveTransportsAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentActiveTransportsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = ActiveTransportsAdapter(requireContext(), this)

        //TODO change to valid id - CurrentUser.user._id
        Log.d("USERID", "current user's id would be ${CurrentUser.user._id} (NOT YET USED)")

        binding.swipeRefreshLayout.setOnRefreshListener() {
            viewModel.getActiveTransports(0).observe(viewLifecycleOwner) { jobDetailState ->
                render(jobDetailState)
            }
        }

        binding.activeTransportsRecyclerView.adapter = adapter
    }

    private fun render(state: JobDetailState/*DeliveryListState volt*/) {
        /*when (state) {
            is DeliveryListState.inProgress -> {
                //loadingDialog.show()
            }
            is DeliveryListState.deliveriesResponseSuccess -> {
                //loadingDialog.dismiss()
                Log.i("DATA ARRIVED", state.data.toString())
                adapter.addTransports(state.data)
            }
            is DeliveryListState.deliveriesResponseError -> {
                //loadingDialog.dismiss()
                Log.e("ERROR", state.exceptionMsg)
            }
        }*/
        when (state) {
            is JobDetailState.inProgress -> {
                //loadingDialog.show()
            }
            is JobDetailState.jobDetailsResponseSuccess -> {
                //loadingDialog.dismiss()
                Log.i("DATA ARRIVED", state.data.toString())
                adapter.addTransports(state.data)
            }
            is JobDetailState.jobDetailsResponseError -> {
                //loadingDialog.dismiss()
                Log.e("ERROR", state.exceptionMsg)
            }
        }
    }

    override fun onJobDetailAccepted(jobDetail: JobDetails?) {
        Log.d("TRANSPORT", jobDetail.toString())
        adapter.removeTransportAt(adapter.getTransportPosition(jobDetail!!))
        // TODO: send "accepted" to the server
    }

    override fun onJobDetailDeclined(jobDetail: JobDetails?) {
        Log.d("TRANSPORT", "declined the ${jobDetail.toString()}")
        adapter.removeTransportAt(adapter.getTransportPosition(jobDetail!!))
        // TODO: send "declined" to the server function
    }

}