package hu.bme.aut.android.deliveryapp.view.fragments

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentInProgressDetailsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryInProgress
import hu.bme.aut.android.deliveryapp.model.DeliveryStatus
import hu.bme.aut.android.deliveryapp.service.LocationTrackerService
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.InProgressJobsDetailsFragmentViewModel


class InProgressJobsDetailsFragment : Fragment(), RatingDialog.RateDialogSubmittedListener {
    private lateinit var binding: FragmentInProgressDetailsBinding

    private var selectedJob: DeliveryInProgress? = null

    private val viewModel: InProgressJobsDetailsFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    private lateinit var listener: RatingDialog.RateDialogSubmittedListener

    private lateinit var intentMyService: Intent

    companion object {
        private const val REQUEST_CODE_PERMISSIONS = 1001
        private val REQUIRED_PERMISSIONS = arrayOf(Manifest.permission.FOREGROUND_SERVICE, Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_BACKGROUND_LOCATION)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedJob = arguments?.get("JOB") as DeliveryInProgress?
        binding.tvStatusLabel.text = "Status: ${selectedJob?.delivery?.status}: ${selectedJob?.status}"

        listener = this

        loadingDialog = LottieProgressDialog(
            context = requireContext(),
            isCancel = true,
            dialogWidth = null,
            dialogHeight = null,
            animationViewWidth = null,
            animationViewHeight = null,
            fileName = LottieProgressDialog.SAMPLE_1,
            title = null,
            titleVisible = null
        )

        val fragment: JobDetailsFragment = childFragmentManager.findFragmentById(R.id.jobDetailsFragmentOnInProgress) as JobDetailsFragment
        fragment.initFragment(selectedJob?.delivery)

        if (selectedJob != null) {
            viewModel.getUserData(selectedJob!!.delivery.clientUser).observe(viewLifecycleOwner
            ) { userState ->
                renderData(userState)
            }

            if (selectedJob?.delivery?.status == DeliveryStatus.IN_TRANSIT) {
                binding.btnMarkAsInTransit.visibility = View.GONE
                binding.btnMarkAsReady.visibility = View.VISIBLE
            }
            else if (selectedJob?.delivery?.status == DeliveryStatus.ASSIGNED) {
                binding.btnMarkAsInTransit.visibility = View.VISIBLE
                binding.btnMarkAsReady.visibility = View.GONE
            }
            else {
                binding.btnMarkAsInTransit.visibility = View.GONE
                binding.btnMarkAsReady.visibility = View.GONE
            }
        }

        intentMyService = Intent(requireContext(), LocationTrackerService::class.java)
        binding.btnMarkAsReady.setOnClickListener {
            viewModel.markJobAsReady(selectedJob!!.delivery).observe(viewLifecycleOwner
            ) { deliveryState ->
                jobReadyRender(deliveryState)
            }
        }

        binding.btnMarkAsInTransit.setOnClickListener {
            viewModel.markDeliveryAsInTransit(selectedJob!!.delivery).observe(viewLifecycleOwner
            ) { deliveryState ->
                inTransitRender(deliveryState)
            }
        }

        binding.btnMarkAsInTransit.isEnabled = false
        if (allPermissionsGranted()) {
            binding.btnMarkAsInTransit.isEnabled = true
        } else {
            val permissionLauncher = registerForActivityResult(
                ActivityResultContracts.RequestPermission()
            ) { isGranted ->
                if (isGranted) {
                    binding.btnMarkAsInTransit.isEnabled = true
                }
                else {
                    AwesomeDialog.build(requireActivity())
                        .title("Error")
                        .body("You have to grant the permissions to start the delivery!")
                        .icon(R.drawable.error)
                        .onPositive("Close")
                }
            }

            permissionLauncher.launch(Manifest.permission.ACCESS_FINE_LOCATION)
        }

    }

    private fun allPermissionsGranted() = REQUIRED_PERMISSIONS.all {
        ContextCompat.checkSelfPermission(
            requireContext(), it
        ) == PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int, permissions: Array<String>, grantResults:
        IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_CODE_PERMISSIONS) {
            if (allPermissionsGranted()) {
                binding.btnMarkAsInTransit.isEnabled = true
            } else {
                binding.btnMarkAsInTransit.isEnabled = false
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body("You have to grant the permissions to start the delivery!")
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentInProgressDetailsBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun renderData(state: UserState) {
        when (state) {
            is UserState.inProgress -> {
                loadingDialog.show()
            }
            is UserState.userResponseSuccess -> {
                binding.btnCall.text = state.data.phoneNumber
                binding.btnSms.text = state.data.phoneNumber
                binding.btnEmail.text = state.data.email
                loadingDialog.dismiss()

                binding.btnCall.setOnClickListener {
                    val intent = Intent(Intent.ACTION_DIAL, Uri.fromParts("tel", state.data.phoneNumber, null))
                    startActivity(intent)
                }

                binding.btnSms.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("sms", state.data.phoneNumber, null))
                    startActivity(intent)
                }

                binding.btnEmail.setOnClickListener {
                    val intent = Intent(Intent.ACTION_SENDTO, Uri.fromParts("mailto", state.data.email, null))
                    startActivity(intent)
                }

            }
            is UserState.userResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

    private fun jobReadyRender(state: DeliveryState) {
        when (state) {
            is DeliveryState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()

                requireActivity().stopService(intentMyService)

                RatingDialog(listener).show(childFragmentManager, "RATE")
            }
            is DeliveryState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

    private fun inTransitRender(state: DeliveryState) {
        when (state) {
            is DeliveryState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Success")
                    .body("Delivery marked as in transit")
                    .icon(R.drawable.success)
                    .onPositive("Close")

                intentMyService.putExtra("DELIVERY_ID", state.data._id)
                requireActivity().startService(intentMyService)
            }
            is DeliveryState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

    override fun rateSubmitted(rating: Int) {
        AwesomeDialog.build(requireActivity())
            .title("Success")
            .body("Delivery marked as ready")
            .icon(R.drawable.success)
            .onPositive("Close")
        viewModel.rateClient(selectedJob!!.delivery._id, rating).observe(viewLifecycleOwner
        ) { deliveryState ->
            userRated(deliveryState)
        }

    }

    private fun userRated(state: DeliveryState) {
        when (state) {
            is DeliveryState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Success")
                    .body("User succesfully rated")
                    .icon(R.drawable.success)
                    .onPositive("Close")
            }
            is DeliveryState.deliveriesResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive("Close")
            }
        }
    }
}