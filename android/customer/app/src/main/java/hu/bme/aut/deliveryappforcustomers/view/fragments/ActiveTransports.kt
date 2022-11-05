package hu.bme.aut.deliveryappforcustomers.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentActiveTransportsBinding
import hu.bme.aut.deliveryappforcustomers.view.JobDetailState
import hu.bme.aut.deliveryappforcustomers.viewmodel.ActiveTransportsViewModel

class ActiveTransports : Fragment() {

    companion object {
        fun newInstance() = ActiveTransports()
    }

    //private lateinit var viewModel: ActiveTransportsViewModel
    private lateinit var binding: FragmentActiveTransportsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active_transports, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*viewModel.getActiveTransports(10).observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }*/
    }

    private fun render(state: JobDetailState) {
        when (state) {
            is JobDetailState.inProgress -> {
                Toast.makeText(context, "Loading Data", Toast.LENGTH_SHORT).show()
            }
            is JobDetailState.jobDetailsResponseSuccess -> {
                binding.tvText.text = state.data.toString()
            }
            is JobDetailState.jobDetailsResponseError -> {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(ActiveTransportsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}