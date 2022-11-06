package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentVehicleBinding
import hu.bme.aut.android.deliveryapp.model.Vehicle
import hu.bme.aut.android.deliveryapp.repository.CurrentUser
import hu.bme.aut.android.deliveryapp.view.states.VehicleState
import hu.bme.aut.android.deliveryapp.viewmodel.VehicleFragmentViewModel

class VehicleFragment : Fragment() {

    private lateinit var binding: FragmentVehicleBinding

    private val viewModel: VehicleFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    private lateinit var vehicle: Vehicle

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        if (CurrentUser.user.vehicle != null) {
            viewModel.getVehicleData(CurrentUser.user.vehicle!!).observe(
                viewLifecycleOwner
            ) { vehicleData ->
                render(vehicleData)
            }
            binding.layoutVehicle.visibility = View.VISIBLE
            binding.layoutNoVehicle.visibility = View.GONE
        }
        else {
            binding.layoutVehicle.visibility = View.GONE
            binding.layoutNoVehicle.visibility = View.VISIBLE
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

        binding.btnAddVehicle.setOnClickListener {
            findNavController().navigate(R.id.action_vehicleFragment_to_addVehicleFragment)
        }

        binding.btnEditVehicle.setOnClickListener {
            val b = Bundle()
            b.putSerializable("VEHICLE", vehicle)
            findNavController().navigate(R.id.action_vehicleFragment_to_addVehicleFragment, b)
        }
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
                binding.tvStorageSize.text = "width: ${state.data.maxCapacity.width} x length: ${state.data.maxCapacity.length} x height: ${state.data.maxCapacity.height}"
                binding.tvStorageCapacity.text = "${state.data.maxCapacity.weight} kg"
                binding.tvParkLocation.text = state.data.location
                loadingDialog.dismiss()
                vehicle = state.data
            }
            is VehicleState.vehicleResponseError -> {
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