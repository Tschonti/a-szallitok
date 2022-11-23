package hu.bme.aut.android.deliveryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.JobDetailsRowBinding
import hu.bme.aut.android.deliveryapp.model.Delivery

class JobDetailsAdapter(private val listener: OnJobSelectedListener) : RecyclerView.Adapter<JobDetailsAdapter.JobDetailsViewHolder>() {
    private val jobs: MutableList<Delivery> = ArrayList()

    interface OnJobSelectedListener {
        fun onJobSelected(job: Delivery?)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): JobDetailsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.job_details_row, parent, false)
        return JobDetailsViewHolder(view)
    }

    override fun onBindViewHolder(holder: JobDetailsViewHolder, position: Int) {
        val item = jobs[position]
        holder.bind(item)
    }

    override fun getItemCount(): Int = jobs.size

    fun addJobs(newJobs: List<Delivery>) {
        jobs.clear()
        jobs.addAll(newJobs)
        notifyDataSetChanged()
    }

    inner class JobDetailsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = JobDetailsRowBinding.bind(itemView)
        var item: Delivery? = null

        init {
            binding.root.setOnClickListener { listener.onJobSelected(item) }
        }

        fun bind(newJob: Delivery) {
            item = newJob
            binding.tvTitle.text = newJob.title
            binding.tvLocation.text = newJob.sourceLocation?.city
            binding.tvDate.text = newJob.pickUpUntil?.subSequence(0, 10)
            binding.tvPrice.text = newJob.price.toString()
        }
    }
}