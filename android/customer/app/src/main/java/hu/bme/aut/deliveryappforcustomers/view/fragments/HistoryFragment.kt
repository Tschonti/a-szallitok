package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentHistoryBinding
import hu.bme.aut.deliveryappforcustomers.databinding.ItemHistoryBinding
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus
import java.time.LocalTime
import java.time.format.DateTimeFormatter

class HistoryFragment : Fragment() {

    companion object {

        enum class HistoryType {
            NEW_TRANSPORT, ACCEPTED_TRANSPORT, DECLINED_TRANSPORT, CHECKED_MAP, OPENED_APP
        }

        class HistoryItem(val type: HistoryType, val time: LocalTime)

        //a megnyitás óta történt eseményeket tartalmazó lista
        var historyList: MutableList<HistoryItem> = mutableListOf()
    }
    //private lateinit var viewModel: HistoryViewModel

    private lateinit var adapter: HistoryAdapter

    private lateinit var binding: FragmentHistoryBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHistoryBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = HistoryAdapter(historyList)

        binding.rvHistory.adapter = adapter
    }
}

class HistoryAdapter(private val historyList: MutableList<HistoryFragment.Companion.HistoryItem>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_history, parent, false)
        return HistoryViewHolder(view)
    }

    override fun onBindViewHolder(holder: HistoryViewHolder, position: Int) {
        val historyItem = historyList[position]
        holder.bind(historyItem)
    }

    override fun getItemCount() = historyList.size

    inner class HistoryViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var binding = ItemHistoryBinding.bind(itemView)
        var item: DeliveryWithUserAndStatus? = null

        fun bind(newEvent: HistoryFragment.Companion.HistoryItem) {
            binding.tvEventName.text = newEvent.type.toString()
            binding.tvEventTime.text = newEvent.time.format(DateTimeFormatter.ofPattern("HH:mm"))
        }
    }
}
