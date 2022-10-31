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
import hu.bme.aut.android.deliveryapp.model.JobDetails
import hu.bme.aut.android.deliveryapp.view.states.DeliveryState
import hu.bme.aut.android.deliveryapp.view.states.UserState
import hu.bme.aut.android.deliveryapp.viewmodel.InProgressJobsDetailsFragmentViewModel


class InProgressJobsDetailsFragment : Fragment() {
    private lateinit var binding: FragmentInProgressDetailsBinding

    private var selectedJob: JobDetails? = null

    private val viewModel: InProgressJobsDetailsFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        selectedJob = arguments?.get("JOB") as JobDetails?

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
        fragment.initFragment(selectedJob)

        if (selectedJob != null) {
            selectedJob!!.clientId = "10"
            viewModel.getUserData(selectedJob!!.clientId).observe(viewLifecycleOwner
            ) { userState ->
                render(userState)
            }

            selectedJob!!.deliveryId = "10"
            viewModel.getDeliveryData(selectedJob!!.deliveryId).observe(viewLifecycleOwner
            ) { deliveryState ->
                render(deliveryState)
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

    private fun render(state: UserState) {
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

    private fun render(state: DeliveryState) {
        when (state) {
            is DeliveryState.inProgress -> {
                loadingDialog.show()
            }
            is DeliveryState.deliveriesResponseSuccess -> {
                loadingDialog.dismiss()
                binding.tvStatusLabel.text = "Status: ${state.data.status}"
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