package hu.bme.aut.android.deliveryapp.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.devhoony.lottieproegressdialog.LottieProgressDialog
import com.example.awesomedialog.*
import hu.bme.aut.android.deliveryapp.R
import hu.bme.aut.android.deliveryapp.databinding.FragmentAddVehicleBinding
import hu.bme.aut.android.deliveryapp.model.Capacity
import hu.bme.aut.android.deliveryapp.model.Vehicle
import hu.bme.aut.android.deliveryapp.view.LoadingDialogManager
import hu.bme.aut.android.deliveryapp.view.states.VehicleState
import hu.bme.aut.android.deliveryapp.viewmodel.AddVehicleFragmentViewModel


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

        loadingDialog = LoadingDialogManager.getLoadingDialog(requireContext())

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
        if (binding.etVehicleHeight.editText!!.text.isEmpty()) {
            binding.etVehicleHeight.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehicleYear.editText!!.text.isEmpty()) {
            binding.etVehicleYear.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehicleLocation.editText!!.text.isEmpty()) {
            binding.etVehicleLocation.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehicleWeight.editText!!.text.isEmpty()) {
            binding.etVehicleWeight.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehiclePlate.editText!!.text.isEmpty()) {
            binding.etVehiclePlate.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehicleType.editText!!.text.isEmpty()) {
            binding.etVehicleType.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehicleLength.editText!!.text.isEmpty()) {
            binding.etVehicleLength.editText?.error = getString(R.string.empty)
            allCorrect = false
        }
        if (binding.etVehicleWidth.editText!!.text.isEmpty()) {
            binding.etVehicleWidth.editText?.error = getString(R.string.empty)
            allCorrect = false
        }

        return allCorrect
    }

    private fun initInputText() {
        vehicle = arguments?.get("VEHICLE") as Vehicle?
        if (vehicle != null) {
            binding.etVehicleHeight.editText?.setText(vehicle?.maxCapacity?.height.toString())
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
                    .title(getString(R.string.success))
                    .body(getString(R.string.vehicle_added))
                    .icon(hu.bme.aut.android.deliveryapp.R.drawable.success)
                    .onPositive(getString(R.string.close))
            }
            is VehicleState.vehicleResponseError -> {
                loadingDialog.dismiss()
                findNavController().popBackStack()
                AwesomeDialog.build(requireActivity())
                    .title(getString(R.string.error))
                    .body(state.exceptionMsg)
                    .icon(hu.bme.aut.android.deliveryapp.R.drawable.error)
                    .onPositive(getString(R.string.close))
            }
        }
    }

}