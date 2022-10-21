package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import hu.bme.aut.android.deliveryapp.databinding.FragmentProfileBinding
import hu.bme.aut.android.deliveryapp.databinding.FragmentVehicleBinding
import hu.bme.aut.android.deliveryapp.view.UserState
import hu.bme.aut.android.deliveryapp.view.VehicleState
import hu.bme.aut.android.deliveryapp.viewmodel.ProfileFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.VehicleFragmentViewModel

class VehicleFragment : Fragment() {

    private lateinit var binding: FragmentVehicleBinding

    private val viewModel: VehicleFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getVehicleData(10).observe(viewLifecycleOwner
        ) { vehicleData ->
            render(vehicleData)
        }

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

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentVehicleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun render(state: VehicleState) {
        when (state) {
            is VehicleState.inProgress -> {
                loadingDialog.show()
            }
            is VehicleState.vehicleResponseSuccess -> {
                binding.tvType.text = state.data.type
                binding.tvYear.text = state.data.yearOfManufacturing.toString()
                binding.tvStorageSize.text = "width: ${state.data.maxWidth} x length: ${state.data.maxLength} x height: ${state.data.maxHeight}"
                binding.tvStorageCapacity.text = "Capacity"
                binding.tvParkLocation.text = state.data.location
                binding.tvMinCost.text = "Min cost"
                loadingDialog.dismiss()
            }
            is VehicleState.vehicleResponseError -> {
                loadingDialog.dismiss()
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}