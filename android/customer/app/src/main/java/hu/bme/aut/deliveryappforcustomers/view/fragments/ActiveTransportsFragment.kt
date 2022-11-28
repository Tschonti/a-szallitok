package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hu.bme.aut.deliveryappforcustomers.adapter.ActiveTransportsAdapter
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentActiveTransportsBinding
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.viewmodel.ActiveTransportsViewModel
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

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

        adapter = ActiveTransportsAdapter(this)

        Log.d("USERID", "current user's id would be ${CurrentUser.user._id} (NOT YET USED)")

//        binding.swipeRefreshLayout.setOnRefreshListener {
//
//            viewModel.getJobRequests().observe(
//                viewLifecycleOwner
//            ) { responseList ->
//                render(responseList)
//            }
//        }
        binding.swipeRefreshLayout.setOnRefreshListener {
            GlobalScope.launch {
                val requestedJobs = GlobalScope.async {
                    viewModel.getJobRequests()
                }.await()
                if (requestedJobs.value == null) {
                    Log.e("activetransportsfragment", "requested jobs is null")
                    //TODO patch this
                    binding.swipeRefreshLayout.isRefreshing = false
                } else {
                    Log.i("activetransportsfragment", "requested jobs data arrived")
                    adapter.clear()
                    adapter.addTransports(requestedJobs.value!!)
                    binding.swipeRefreshLayout.isRefreshing = false
                }
            }
        }

        binding.activeTransportsRecyclerView.adapter = adapter
    }

    fun render(responseList: List<DeliveryWithUserAndStatus>?) {
        if (responseList == null) {
            Log.e("activetransportsfragment", "requested jobs is null")
            Toast.makeText(
                requireContext(),
                "Egyik fuvarodra sincs jelentkező",
                Toast.LENGTH_SHORT
            ).show()
            binding.swipeRefreshLayout.isRefreshing = false
        } else {
            Log.i("activetransportsfragment", "requested jobs data arrived")
            adapter.clear()
            adapter.addTransports(responseList)
            //binding.swipeRefreshLayout.isRefreshing = false
        }
    }

    override fun onJobDetailAccepted(transporterCandidate: DeliveryWithUserAndStatus?) {
        Log.d("TRANSPORT", transporterCandidate.toString())
        adapter.removeTransportAt(adapter.getTransportPosition(transporterCandidate!!))
        // TODO: send "accepted" to the server
    }

    override fun onJobDetailDeclined(transporterCandidate: DeliveryWithUserAndStatus?) {
        Log.d("TRANSPORT", "declined the ${transporterCandidate.toString()}")
        adapter.removeTransportAt(adapter.getTransportPosition(transporterCandidate!!))
        // TODO: send "declined" to the server function
    }

}