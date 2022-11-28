package hu.bme.aut.deliveryappforcustomers.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.databinding.ItemPendingDeliveryBinding
import hu.bme.aut.deliveryappforcustomers.model.DeliveryWithUserAndStatus

class ActiveTransportsAdapter(
    private val listener: onTransportSelectedListener
) : RecyclerView.Adapter<ActiveTransportsAdapter.ActiveTransportsViewHolder>() {
    private val transporterCandidates: MutableList<DeliveryWithUserAndStatus> = ArrayList()

    interface onTransportSelectedListener {
        fun onJobDetailAccepted(transporterCandidate: DeliveryWithUserAndStatus?)
        fun onJobDetailDeclined(transporterCandidate: DeliveryWithUserAndStatus?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveTransportsViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_pending_delivery, parent, false)
        return ActiveTransportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActiveTransportsViewHolder, position: Int) {
        val item = transporterCandidates[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = transporterCandidates.size

    fun getTransportPosition(transporterCandidate: DeliveryWithUserAndStatus): Int {
        return transporterCandidates.indexOf(transporterCandidate)
    }

//    fun addTransport(newTransport: Delivery) {
//        transports.add(newTransport)
//        notifyItemInserted(transports.size - 1)
//    }

    fun addTransports(deliveryWithUserAndStatusList: List<DeliveryWithUserAndStatus>) {
        transporterCandidates.clear()
        transporterCandidates.addAll(deliveryWithUserAndStatusList.map { it })
        notifyDataSetChanged()
    }

    fun removeTransportAt(position: Int) {
        transporterCandidates.removeAt(position)
        notifyItemRemoved(position)
        if (position < transporterCandidates.size) {
            notifyItemRangeChanged(position, transporterCandidates.size - position)
        }
    }

    fun clear() {
        transporterCandidates.clear()
        notifyDataSetChanged()
    }


    inner class ActiveTransportsViewHolder(private val itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var binding = ItemPendingDeliveryBinding.bind(itemView)
        var item: DeliveryWithUserAndStatus? = null

        init {
            binding.btnAccept.setOnClickListener { listener.onJobDetailAccepted(item) }
            binding.btnDecline.setOnClickListener { listener.onJobDetailDeclined(item) }
        }

        fun bind(newCandidate: DeliveryWithUserAndStatus) {
            item = newCandidate
            binding.tvTransporterName.text = newCandidate.delivery.title
            binding.tvPrice.text = newCandidate.user?.name
        }
    }
}
