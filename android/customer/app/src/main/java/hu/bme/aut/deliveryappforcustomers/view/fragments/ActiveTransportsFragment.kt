package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
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
import hu.bme.aut.deliveryappforcustomers.model.Reply
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.viewmodel.ActiveTransportsViewModel
import java.time.LocalTime

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

        binding.activeTransportsRecyclerView.adapter = adapter
        binding.swipeRefreshLayout.setOnRefreshListener {
            viewModel.getJobRequests().observe(
                viewLifecycleOwner
            ) { responseList ->
                requireActivity().runOnUiThread {
                    if (responseList != null) {
                        adapter.clear()
                        adapter.addTransports(responseList)
                        binding.swipeRefreshLayout.isRefreshing = false
                    }
                }
            }
        }
    }

    override fun onJobDetailAccepted(transporterCandidate: DeliveryWithUserAndStatus?) {
        Log.d("TRANSPORT", transporterCandidate.toString())
        adapter.removeTransportAt(adapter.getTransportPosition(transporterCandidate!!))
        Log.d("DELIVERYID", transporterCandidate.delivery._id!!)
        viewModel.reply(
            transporterCandidate.delivery._id,
            Reply(transporterCandidate.user!!._id, true)
        )
        Toast.makeText(context, "Job accepted", Toast.LENGTH_SHORT).show()
        HistoryFragment.historyList.add(
            HistoryFragment.Companion.HistoryItem(
                HistoryFragment.Companion.HistoryType.ACCEPTED_TRANSPORT,
                LocalTime.now()
            )
        )
    }

    override fun onJobDetailDeclined(transporterCandidate: DeliveryWithUserAndStatus?) {
        Log.d("TRANSPORT", "declined the ${transporterCandidate.toString()}")
        adapter.removeTransportAt(adapter.getTransportPosition(transporterCandidate!!))
        viewModel.reply(
            transporterCandidate.delivery._id!!,
            Reply(transporterCandidate.user!!._id, false)
        )
        Toast.makeText(context, "Job declined", Toast.LENGTH_SHORT).show()
        HistoryFragment.historyList.add(
            HistoryFragment.Companion.HistoryItem(
                HistoryFragment.Companion.HistoryType.DECLINED_TRANSPORT,
                LocalTime.now()
            )
        )
    }
}