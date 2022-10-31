package hu.bme.aut.android.deliveryapp.view.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import hu.bme.aut.android.deliveryapp.databinding.FragmentAddVehicleBinding
import hu.bme.aut.android.deliveryapp.model.Vehicle


class AddVehicleFragment : Fragment() {
    private lateinit var binding: FragmentAddVehicleBinding

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

        binding.btnAddVehicle.setOnClickListener {

            if (checkInputFields()) {
                val vehicle = Vehicle(
                    binding.etVehicleHeight.editText?.text.toString().toFloat(),
                    "IMG",
                    binding.etVehicleYear.editText?.text.toString().toInt(),
                    binding.etVehicleLocation.editText?.text.toString(),
                    "ID",
                    binding.etVehicleWeight.editText?.text.toString().toFloat(),
                    binding.etVehiclePlate.editText?.text.toString(),
                    binding.etVehicleType.editText?.text.toString(),
                    binding.etVehicleLength.editText?.text.toString().toFloat(),
                    binding.etVehicleWidth.editText?.text.toString().toFloat(),
                )
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
        val vehicle: Vehicle? = arguments?.get("VEHICLE") as Vehicle?
        if (vehicle != null) {
            binding.etVehicleHeight.editText?.setText(vehicle?.maxHeight.toString())
            Glide.with(requireContext()).load(vehicle?.pictureUrl).into(binding.ivVehicleImage);
            binding.etVehicleYear.editText?.setText(vehicle?.yearOfManufacturing.toString())
            binding.etVehicleLocation.editText?.setText(vehicle?.location.toString())
            binding.etVehicleWeight.editText?.setText(vehicle?.maxWeight.toString())
            binding.etVehiclePlate.editText?.setText(vehicle?.plateNumber.toString())
            binding.etVehicleType.editText?.setText(vehicle?.type.toString())
            binding.etVehicleLength.editText?.setText(vehicle?.maxLength.toString())
            binding.etVehicleWidth.editText?.setText(vehicle?.maxWidth.toString())
        }
    }
}