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
import hu.bme.aut.android.deliveryapp.view.LoadingDialogManager
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

        loadingDialog = LoadingDialogManager.getLoadingDialog(requireContext())

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
                binding.tvStorageSize.text = getString(R.string.vehicle_capacity, state.data.maxCapacity.width.toString(), state.data.maxCapacity.length.toString(), state.data.maxCapacity.height.toString())
                binding.tvStorageCapacity.text = getString(R.string.vehicle_capacity_weight, state.data.maxCapacity.weight.toString())
                binding.tvParkLocation.text = state.data.location
                loadingDialog.dismiss()
                vehicle = state.data
            }
            is VehicleState.vehicleResponseError -> {
                loadingDialog.dismiss()
                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.error))
                    .body(state.exceptionMsg)
                    .icon(R.drawable.error)
                    .onPositive(getString(R.string.close))
            }
        }
    }
}