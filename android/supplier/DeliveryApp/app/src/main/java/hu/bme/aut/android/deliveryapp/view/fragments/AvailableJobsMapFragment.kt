package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobsMapFragmentViewModel

class AvailableJobsMapFragment : Fragment() {

    private val viewModel: AvailableJobsMapFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    private lateinit var googleMap: GoogleMap

    private val jobList: MutableList<Delivery> = mutableListOf()

    private val callback = OnMapReadyCallback { googleMap ->
        this.googleMap = googleMap
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_available_jobs_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment?
        mapFragment?.getMapAsync(callback)

        viewModel.getAvailableJobs().observe(viewLifecycleOwner
        ) { jobDetailState ->
            render(jobDetailState)
        }

        loadingDialog = LottieProgressDialog(
            context = requireContext(),
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_8,
            title = null,
            titleVisible = null
        )
    }

    private fun render(state: DeliveryListState) {
        when (state) {
            is DeliveryListState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryListState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                state.data.forEach {
                    jobList.add(it)
                    addMarker(it)
                }
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(LatLng(jobList[0].sourceLocation!!.coordinate.latitude.toDouble(), jobList[0].destinationLocation!!.coordinate.longitude.toDouble()), 10f))
            }
            is DeliveryListState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

    private fun addMarker(job: Delivery) {
        val markerOptions = MarkerOptions()
        markerOptions.position(LatLng(job.sourceLocation!!.coordinate.latitude.toDouble(), job.sourceLocation.coordinate.longitude.toDouble()))
        markerOptions.title(job.title)
        googleMap.addMarker(markerOptions)
    }
}