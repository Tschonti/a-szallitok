package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import hu.bme.aut.android.deliveryapp.databinding.FragmentVehicleBinding
import hu.bme.aut.android.deliveryapp.repository.CurrentUser
import hu.bme.aut.android.deliveryapp.view.states.VehicleState
import hu.bme.aut.android.deliveryapp.viewmodel.VehicleFragmentViewModel

class VehicleFragment : Fragment() {

    private lateinit var binding: FragmentVehicleBinding

    private val viewModel: VehicleFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //TODO: Set if there is a vehicle
        //CurrentUser.user.vehicleId = "4"
        if (CurrentUser.user.vehicleId != null) {
            viewModel.getVehicleData(CurrentUser.user.vehicleId!!).observe(
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
            AddVehicleFragment().show(childFragmentManager, "NEW_VEHICLE")
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
                Glide.with(requireContext()).load(state.data.pictureUrl).into(binding.ivVehicleImage);
                binding.tvType.text = state.data.type
                binding.tvYear.text = state.data.yearOfManufacturing.toString()
                binding.tvStorageSize.text = "width: ${state.data.maxWidth} x length: ${state.data.maxLength} x height: ${state.data.maxHeight}"
                binding.tvStorageCapacity.text = "${state.data.maxWeight} kg"
                binding.tvParkLocation.text = state.data.location
                loadingDialog.dismiss()
            }
            is VehicleState.vehicleResponseError -> {
                loadingDialog.dismiss()
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            }
        }
    }
}