package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.android.material.datepicker.MaterialDatePicker
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentNewTransportBinding
import hu.bme.aut.deliveryappforcustomers.model.Capacity
import hu.bme.aut.deliveryappforcustomers.model.Coordinate
import hu.bme.aut.deliveryappforcustomers.model.Location
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.viewmodel.NewTransportViewModel
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class NewTransportFragment : Fragment() {

    private lateinit var binding: FragmentNewTransportBinding
    private val viewModel: NewTransportViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentNewTransportBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    private fun validate(): Boolean {
        if (binding.transportName.editText?.text.toString().isEmpty()) {
            binding.transportName.editText?.error = "A fuvar neve nem lehet üres"
            return false
        }
        if (binding.maxPrice.editText?.text.toString().isEmpty()) {
            binding.maxPrice.editText?.error = "A fuvar ára nem lehet üres"
            return false
        }
        if (binding.description.editText?.text.toString().isEmpty()) {
            binding.description.editText?.error = "A fuvar leírása nem lehet üres"
            return false
        }
        if (binding.weight.editText?.text.toString().isEmpty()) {
            binding.weight.editText?.error = "A fuvar súlya nem lehet üres"
            return false
        }
        if (binding.width.editText?.text.toString().isEmpty()) {
            binding.width.editText?.error = "A fuvar szélessége nem lehet üres"
            return false
        }
        if (binding.height.editText?.text.toString().isEmpty()) {
            binding.height.editText?.error = "A fuvar magassága nem lehet üres"
            return false
        }
        if (binding.length.editText?.text.toString().isEmpty()) {
            binding.length.editText?.error = "A fuvar hossza nem lehet üres"
            return false
        }
        if (binding.width.editText?.text.toString().isEmpty()) {
            binding.width.editText?.error = "A fuvar szélessége nem lehet üres"
            return false
        }
        if (binding.pickupPlace.editText?.text.toString().isEmpty()) {
            binding.pickupPlace.editText?.error = "A felvétel helye nem lehet üres"
            return false
        }
        if (binding.deliveryPlace.editText?.text.toString().isEmpty()) {
            binding.deliveryPlace.editText?.error = "A kiszállítás helye nem lehet üres"
            return false
        }
        return true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateFormat = "yyyy.MM.dd"
        val format = SimpleDateFormat(dateFormat)
        binding.pickupDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Válassz dátumot")
                    .build()
            datePicker.show(childFragmentManager, "DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.timeInMillis = it
                binding.pickupDate.text = format.format(calendar.time)
            }
        }
        binding.deliveryDate.setOnClickListener {
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Válassz dátumot")
                    .build()
            datePicker.show(childFragmentManager, "DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener {
                val calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
                calendar.timeInMillis = it
                binding.deliveryDate.text = format.format(calendar.time)
            }
        }

        binding.submit.setOnClickListener {
            val testLocation = Location(
                "Hungary",
                Coordinate(0.0f, 0.0f),
                "example street 1",
                "Budapest",
                1111
            )
            if (!validate()) return@setOnClickListener
            val returnValue = viewModel.createNewTransport(
                Delivery(
                    title = binding.transportName.editText?.text.toString(),
                    price = binding.maxPrice.editText?.text.toString().toFloat(),
                    capacity = Capacity(
                        width = binding.width.editText?.text.toString().toFloat(),
                        height = binding.height.editText?.text.toString().toFloat(),
                        length = binding.length.editText?.text.toString().toFloat(),
                        weight = binding.weight.editText?.text.toString().toFloat()
                    ),
                    sourceLocation = testLocation,
                    destinationLocation = testLocation,
                    clientUser = CurrentUser.user._id,
                    description = binding.description.editText?.text.toString(),
                    createdAt = LocalDate.now().format(DateTimeFormatter.ofPattern(dateFormat)),
                    pickUpFrom = binding.pickupDate.text.toString(),
                    pickUpUntil = binding.pickupDate.text.toString(),
                )
            )
            Log.d("NewTransportFragment", "New transport created: {${returnValue}}")
            Toast.makeText(context, "Sikeres létrehozás!", Toast.LENGTH_SHORT).show()
        }
    }
}