package hu.bme.aut.deliveryappforcustomers.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.databinding.ItemPendingDeliveryBinding

class ActiveTransportsAdapter(private val context: Context, private val listener: onTransportSelectedListener)
    : RecyclerView.Adapter<ActiveTransportsAdapter.ActiveTransportsViewHolder>() {
    //private val transports: MutableList<Delivery> = ArrayList()
    private val jobDetails: MutableList<JobDetails> = ArrayList()

    interface onTransportSelectedListener {
        fun onJobDetailAccepted(jobDetail: JobDetails?)
        fun onJobDetailDeclined(jobDetail: JobDetails?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ActiveTransportsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_pending_delivery, parent, false)
        return ActiveTransportsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ActiveTransportsViewHolder, position: Int) {
        val item = jobDetails[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = jobDetails.size

    fun getTransportPosition(jobDetail: JobDetails): Int{
        return jobDetails.indexOf(jobDetail)
    }

//    fun addTransport(newTransport: Delivery) {
//        transports.add(newTransport)
//        notifyItemInserted(transports.size - 1)
//    }

    fun addTransports(newJobDetails: List<JobDetails>) {
        jobDetails.clear()
        jobDetails.addAll(newJobDetails)
        notifyDataSetChanged()
    }

    fun removeTransportAt(position: Int) {
        jobDetails.removeAt(position)
        notifyItemRemoved(position)
        if (position < jobDetails.size) {
            notifyItemRangeChanged(position, jobDetails.size - position)
        }
    }


    inner class ActiveTransportsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = ItemPendingDeliveryBinding.bind(itemView)
        var item: JobDetails? = null

        init {
            binding.btnAccept.setOnClickListener { listener.onJobDetailAccepted(item) }
            binding.btnDecline.setOnClickListener { listener.onJobDetailDeclined(item) }
        }

        fun bind(newJobDetail: JobDetails) {
            item = newJobDetail
            binding.tvTransporterName.text = newJobDetail.name
            binding.tvPrice.text = newJobDetail.deliveryCost
            //TODO set image
        }
    }
}
