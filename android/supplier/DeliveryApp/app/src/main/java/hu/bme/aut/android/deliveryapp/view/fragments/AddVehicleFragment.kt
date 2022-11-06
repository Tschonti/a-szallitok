package hu.bme.aut.android.deliveryapp.view.fragments

import android.R
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.databinding.FragmentAddVehicleBinding
import hu.bme.aut.android.deliveryapp.model.Capacity
import hu.bme.aut.android.deliveryapp.model.Vehicle
import hu.bme.aut.android.deliveryapp.view.states.DeliveryListState
import hu.bme.aut.android.deliveryapp.view.states.VehicleState
import hu.bme.aut.android.deliveryapp.viewmodel.AddVehicleFragmentViewModel
import hu.bme.aut.android.deliveryapp.viewmodel.AvailableJobDetailsFragmentViewModel


class AddVehicleFragment : Fragment() {
    private lateinit var binding: FragmentAddVehicleBinding

    private val viewModel: AddVehicleFragmentViewModel by viewModels()

    private lateinit var loadingDialog: LottieProgressDialog

    private var vehicle: Vehicle? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddVehicleBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.toolbarMenu.inflateMenu(hu.bme.aut.android.deliveryapp.R.menu.add_vehicle_menu)
        binding.toolbarMenu.setOnMenuItemClickListener {
            when (it.itemId) {
                hu.bme.aut.android.deliveryapp.R.id.close -> {
                    findNavController().popBackStack()
                    true
                }
                else ->
                    true
            }
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

        binding.btnAddVehicle.setOnClickListener {

            if (checkInputFields()) {
                val v = Vehicle(
                    maxCapacity = Capacity(
                        weight = binding.etVehicleWeight.editText?.text.toString().toFloat(),
                        height = binding.etVehicleHeight.editText?.text.toString().toFloat(),
                        length = binding.etVehicleLength.editText?.text.toString().toFloat(),
                        width = binding.etVehicleWidth.editText?.text.toString().toFloat(),
                    ),
                    pictureUrl = null,
                    yearOfManufacturing = binding.etVehicleYear.editText?.text.toString().toInt(),
                    location = binding.etVehicleLocation.editText?.text.toString(),
                    _id = null,
                    plateNumber = binding.etVehiclePlate.editText?.text.toString(),
                    type = binding.etVehicleType.editText?.text.toString(),
                )

                if (vehicle == null) {
                    viewModel.addVehicle(v).observe(viewLifecycleOwner
                    ) { jobDetailState ->
                        render(jobDetailState)
                    }
                }
                else {
                    viewModel.updateVehicle(vehicle!!._id!!, v).observe(viewLifecycleOwner
                    ) { jobDetailState ->
                        render(jobDetailState)
                    }
                }


            }
        }

        initInputText()
    }

    private fun checkInputFields(): Boolean {
        var allCorrect = true
        if (binding.etVehicleHeight.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleHeight.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehicleYear.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleYear.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehicleLocation.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleLocation.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehicleWeight.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleWeight.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehiclePlate.editText?.text?.isEmpty() ?: true) {
            binding.etVehiclePlate.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehicleType.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleType.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehicleLength.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleLength.editText?.setError("Empty")
            allCorrect = false
        }
        if (binding.etVehicleWidth.editText?.text?.isEmpty() ?: true) {
            binding.etVehicleWidth.editText?.setError("Empty")
            allCorrect = false
        }

        return allCorrect
    }

    private fun initInputText() {
        vehicle = arguments?.get("VEHICLE") as Vehicle?
        if (vehicle != null) {
            binding.etVehicleHeight.editText?.setText(vehicle?.maxCapacity?.height.toString())
            Glide.with(requireContext()).load(vehicle?.pictureUrl).into(binding.ivVehicleImage);
            binding.etVehicleYear.editText?.setText(vehicle?.yearOfManufacturing.toString())
            binding.etVehicleLocation.editText?.setText(vehicle?.location.toString())
            binding.etVehicleWeight.editText?.setText(vehicle?.maxCapacity?.weight.toString())
            binding.etVehiclePlate.editText?.setText(vehicle?.plateNumber.toString())
            binding.etVehicleType.editText?.setText(vehicle?.type.toString())
            binding.etVehicleLength.editText?.setText(vehicle?.maxCapacity?.length.toString())
            binding.etVehicleWidth.editText?.setText(vehicle?.maxCapacity?.length.toString())
        }
    }

    private fun render(state: VehicleState) {
        when (state) {
            is VehicleState.inProgress -> {
                loadingDialog.show()
            }
            is VehicleState.vehicleResponseSuccess -> {
                loadingDialog.dismiss()
                findNavController().popBackStack()
                AwesomeDialog.build(requireActivity())
                    .title("Success")
                    .body("Vehicle successfully added")
                    .icon(hu.bme.aut.android.deliveryapp.R.drawable.success)
                    .onPositive("Close")
            }
            is VehicleState.vehicleResponseError -> {
                loadingDialog.dismiss()
                findNavController().popBackStack()
                AwesomeDialog.build(requireActivity())
                    .title("Error")
                    .body(state.exceptionMsg)
                    .icon(hu.bme.aut.android.deliveryapp.R.drawable.error)
                    .onPositive("Close")
            }
        }
    }

}