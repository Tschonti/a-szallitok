package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.adevinta.leku.*
import com.google.android.material.datepicker.CalendarConstraints
import com.google.android.material.datepicker.CalendarConstraints.DateValidator
import com.google.android.material.datepicker.DateValidatorPointForward
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
//        if (binding.pickupPlace.editText?.text.toString().isEmpty()) {
//            binding.pickupPlace.editText?.error = "A felvétel helye nem lehet üres"
//            return false
//        }
//        if (binding.deliveryPlace.editText?.text.toString().isEmpty()) {
//            binding.deliveryPlace.editText?.error = "A kiszállítás helye nem lehet üres"
//            return false
//        }
        if (pickupDate.time > deliveryDate.time) {
            Toast.makeText(
                context,
                "A felvétel dátuma nem lehet későbbi, mint a kiszállítás dátuma",
                Toast.LENGTH_SHORT
            ).show()
            return false
        }
        return true
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && data != null) {
            Log.d("RESULT****", "OK")
            if (requestCode == 1) {
                val latitude = data.getDoubleExtra(LATITUDE, 0.0)
                Log.d("LATITUDE****", latitude.toString())
                val longitude = data.getDoubleExtra(LONGITUDE, 0.0)
                Log.d("LONGITUDE****", longitude.toString())

            }
        } else if (requestCode == 2) {
            val latitude = data!!.getDoubleExtra(LATITUDE, 0.0)
            Log.d("LATITUDE****", latitude.toString())
            val longitude = data.getDoubleExtra(LONGITUDE, 0.0)
            Log.d("LONGITUDE****", longitude.toString())

        }
        if (resultCode == Activity.RESULT_CANCELED) {
            Log.d("RESULT****", "CANCELLED")
        }
    }

    var pickupDate: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    var deliveryDate: Calendar = Calendar.getInstance(TimeZone.getTimeZone("UTC"))
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dateFormat = "yyyy.MM.dd"
        val format = SimpleDateFormat(dateFormat)
        binding.pickupDate.setOnClickListener {
            val dateValidator: DateValidator =
                DateValidatorPointForward.from(System.currentTimeMillis())
            val constraintsBuilder = CalendarConstraints.Builder().setValidator(dateValidator)
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Válassz dátumot")
                    .setCalendarConstraints(constraintsBuilder.build())
                    .build()

            datePicker.show(childFragmentManager, "DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener {
                pickupDate.timeInMillis = it
                binding.pickupDate.text = format.format(pickupDate.time)
            }
        }
        binding.deliveryDate.setOnClickListener {
            val dateValidator: DateValidator =
                DateValidatorPointForward.from(pickupDate.timeInMillis)
            val constraintsBuilder = CalendarConstraints.Builder().setValidator(dateValidator)
            val datePicker =
                MaterialDatePicker.Builder.datePicker()
                    .setTitleText("Válassz dátumot")
                    .setSelection(pickupDate.timeInMillis)
                    .setCalendarConstraints(constraintsBuilder.build())
                    .build()

            datePicker.show(childFragmentManager, "DATE_PICKER")
            datePicker.addOnPositiveButtonClickListener {
                deliveryDate.timeInMillis = it
                binding.deliveryDate.text = format.format(deliveryDate.time)
            }
        }

        binding.pickupPlace.setOnClickListener {
//            val locationPickerIntent = LocationPickerActivity.Builder()
//                .withLocation(41.4036299, 2.1743558)
//                .withGeolocApiKey("<PUT API KEY HERE>")
//                .withGooglePlacesApiKey("<PUT API KEY HERE>")
//                .withSearchZone("hu_HU")
//                .withDefaultLocaleSearchZone()
//                .shouldReturnOkOnBackPressed()
//                .withStreetHidden()
//                .withCityHidden()
//                .withZipCodeHidden()
//                .withSatelliteViewHidden()
//                .withGooglePlacesEnabled()
//                .withGoogleTimeZoneEnabled()
//                .withVoiceSearchHidden()
//                .withUnnamedRoadHidden()
//                .withSearchBarHidden()
//                .build(applicationContext)
//
//            startActivityForResult(locationPickerIntent, MAP_BUTTON_REQUEST_CODE)
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