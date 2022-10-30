package hu.bme.aut.android.deliveryapp.view.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.deliveryapp.databinding.FragmentAddVehicleBinding
import hu.bme.aut.android.deliveryapp.model.Vehicle


class AddVehicleFragment : DialogFragment() {
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
        dialog!!.setTitle("Add new Vehicle")
        binding.btnAddVehicle.setOnClickListener {
            if (binding.etVehicleHeight.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleHeight.editText?.setError("Empty")
            }
            if (binding.etVehicleYear.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleYear.editText?.setError("Empty")
            }
            if (binding.etVehicleLocation.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleLocation.editText?.setError("Empty")
            }
            if (binding.etVehicleWeight.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleWeight.editText?.setError("Empty")
            }
            if (binding.etVehiclePlate.editText?.text?.isEmpty() ?: true) {
                binding.etVehiclePlate.editText?.setError("Empty")
            }
            if (binding.etVehicleType.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleType.editText?.setError("Empty")
            }
            if (binding.etVehicleLength.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleLength.editText?.setError("Empty")
            }
            if (binding.etVehicleWidth.editText?.text?.isEmpty() ?: true) {
                binding.etVehicleWidth.editText?.setError("Empty")
            }
            else {
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
    }
}