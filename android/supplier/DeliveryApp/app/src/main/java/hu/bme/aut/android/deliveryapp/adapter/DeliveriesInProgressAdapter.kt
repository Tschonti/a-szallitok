package hu.bme.aut.android.deliveryapp.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.JobDetailsRowBinding
import hu.bme.aut.android.deliveryapp.databinding.JobDetailsStatusRowBinding
import hu.bme.aut.android.deliveryapp.databinding.JobInProgressRowBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryInProgress
import hu.bme.aut.android.deliveryapp.model.InProgressStatus

class DeliveriesInProgressAdapter(private val context: Context, private val listener: OnDeliveryInProgressSelectedListener) : RecyclerView.Adapter<DeliveriesInProgressAdapter.DeliveriesInProgressViewHolder>() {
    private val deliveries: MutableList<DeliveryInProgress> = ArrayList()

    interface OnDeliveryInProgressSelectedListener {
        fun onDeliveryInProgressSelected(delivery: DeliveryInProgress?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeliveriesInProgressAdapter.DeliveriesInProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_in_progress_row, parent, false)
        return DeliveriesInProgressViewHolder(view)
    }

    override fun onBindViewHolder(holder: DeliveriesInProgressAdapter.DeliveriesInProgressViewHolder, position: Int) {
        val item = deliveries[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = deliveries.size

    fun addDelivery(newJob: DeliveryInProgress) {
        deliveries.add(newJob)
        notifyItemInserted(deliveries.size - 1)
    }

    fun addDeliveries(newJobs: List<DeliveryInProgress>) {
        deliveries.clear()
        deliveries.addAll(newJobs)
        notifyDataSetChanged()
    }

    fun removeDelivery(position: Int) {
        deliveries.removeAt(position)
        notifyItemRemoved(position)
        if (position < deliveries.size) {
            notifyItemRangeChanged(position, deliveries.size - position)
        }
    }

    inner class DeliveriesInProgressViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = JobInProgressRowBinding.bind(itemView)
        var item: DeliveryInProgress? = null

        init {
            binding.root.setOnClickListener { listener.onDeliveryInProgressSelected(item) }
        }

        fun bind(newDelivery: DeliveryInProgress) {
            item = newDelivery
            binding.tvTitle.text = newDelivery.delivery.title
            binding.tvDescription.text = newDelivery.delivery.description
            binding.tvStatus.text = newDelivery.status.toString()
            binding.tvPrice.text = newDelivery.delivery.price.toString()

            if (newDelivery.status == InProgressStatus.PENDING)
                binding.tvStatus.setBackgroundColor(Color.YELLOW)
            else if (newDelivery.status == InProgressStatus.ACCEPTED)
                binding.tvStatus.setBackgroundColor(Color.GREEN)
            else if (newDelivery.status == InProgressStatus.REJECTED)
                binding.tvStatus.setBackgroundColor(Color.GREEN)
        }
    }
}