package hu.bme.aut.deliveryappforcustomers.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.deliveryappforcustomers.R

class NewTransportFragment : Fragment() {

    companion object {
        fun newInstance() = NewTransportFragment()
    }

    private lateinit var viewModel: NewTransportViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_new_transport, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(NewTransportViewModel::class.java)
        // TODO: Use the ViewModel
    }

}