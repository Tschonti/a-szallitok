package hu.bme.aut.android.deliveryapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.JobDetailsRowBinding
import hu.bme.aut.android.deliveryapp.model.JobDetails

class JobDetailsAdapter(private val listener: OnJobSelectedListener) : RecyclerView.Adapter<JobDetailsAdapter.JobDetailsViewHolder>() {
    private val jobs: MutableList<JobDetails> = ArrayList()

    interface OnJobSelectedListener {
        fun onJobSelected(job: JobDetails?)
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

    fun addJob(newJob: JobDetails) {
        jobs.add(newJob)
        notifyItemInserted(jobs.size - 1)
    }

    fun addJobs(newJobs: List<JobDetails>) {
        jobs.clear()
        jobs.addAll(newJobs)
        notifyDataSetChanged()
    }

    fun removeJob(position: Int) {
        jobs.removeAt(position)
        notifyItemRemoved(position)
        if (position < jobs.size) {
            notifyItemRangeChanged(position, jobs.size - position)
        }
    }

    inner class JobDetailsViewHolder(private val itemView: View) : RecyclerView.ViewHolder(itemView) {
        var binding = JobDetailsRowBinding.bind(itemView)
        var item: JobDetails? = null

        init {
            binding.root.setOnClickListener { listener.onJobSelected(item) }
        }

        fun bind(newJob: JobDetails) {
            item = newJob
            binding.tvLocation.text = newJob.deliveryLocation.address
            binding.tvDate.text = newJob.deliveryDate
            binding.tvPrice.text = newJob.deliveryCost
        }
    }
}