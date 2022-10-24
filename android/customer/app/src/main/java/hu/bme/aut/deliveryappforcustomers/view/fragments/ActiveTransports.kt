package hu.bme.aut.deliveryappforcustomers.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.deliveryappforcustomers.R

class ActiveTransports : Fragment() {

    companion object {
        fun newInstance() = ActiveTransports()
    }

    private lateinit var viewModel: ActiveTransportsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_active_transports, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(ActiveTransportsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}