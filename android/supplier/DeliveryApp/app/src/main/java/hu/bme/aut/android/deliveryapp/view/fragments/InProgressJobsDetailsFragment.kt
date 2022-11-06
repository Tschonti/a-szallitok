package hu.bme.aut.android.deliveryapp.view.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentInProgressDetailsBinding
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.android.deliveryapp.model.DeliveryInProgress
import hu.bme.aut.android.deliveryapp.model.DeliveryStatus
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.InProgressJobsDetailsFragmentViewModel


class InProgressJobsDetailsFragment : Fragment(), RatingDialog.RateDialogSubmittedListener {
    private lateinit var binding: FragmentInProgressDetailsBinding

    private var selectedJob: DeliveryInProgress? = null

    private val viewModel: InProgressJobsDetailsFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    private lateinit var listener: RatingDialog.RateDialogSubmittedListener

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
        viewModel.rateClient(selectedJob!!.delivery.clientUser, rating).observe(viewLifecycleOwner
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