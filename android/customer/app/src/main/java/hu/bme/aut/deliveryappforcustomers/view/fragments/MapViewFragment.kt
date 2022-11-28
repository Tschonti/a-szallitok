package hu.bme.aut.deliveryappforcustomers.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import hu.bme.aut.deliveryappforcustomers.R
import hu.bme.aut.deliveryappforcustomers.viewmodel.MapViewViewModel
import java.time.LocalTime

class MapViewFragment : Fragment() {

    companion object {
        fun newInstance() = MapViewFragment()
    }

    //private lateinit var viewModel: MapViewViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        HistoryFragment.historyList.add(
        HistoryFragment.Companion.HistoryItem(
            HistoryFragment.Companion.HistoryType.CHECKED_MAP,
            LocalTime.now()
        )
    )
        return inflater.inflate(R.layout.fragment_map_view, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //viewModel = ViewModelProvider(this).get(MapViewViewModel::class.java)
        // TODO: Use the ViewModel
    }

}