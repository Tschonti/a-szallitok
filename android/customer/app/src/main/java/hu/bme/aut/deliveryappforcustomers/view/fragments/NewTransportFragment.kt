package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.CurrentUserPlain
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentNewTransportBinding
import hu.bme.aut.deliveryappforcustomers.model.Capacity
import hu.bme.aut.deliveryappforcustomers.model.Coordinate
import hu.bme.aut.deliveryappforcustomers.model.Location
import hu.bme.aut.deliveryappforcustomers.repository.CurrentUser
import hu.bme.aut.deliveryappforcustomers.viewmodel.NewTransportViewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.submit.setOnClickListener {
            val testLocation = Location(
                "Hungary",
                Coordinate(0.0f, 0.0f),
                "example street 1",
                "Budapest",
                1111
            )
            //TODO implement proper location pickers

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
                    clientUser = CurrentUserPlain.user!!._id,
                    description = "no description",
                    createdAt = "2022.11.07", //TODO implement proper date
                    pickUpFrom = "example location",
                    pickUpUntil = "2022.11.18",
                )
            )
            Log.d("NewTransportFragment", "New transport created(?): $returnValue")
        }
    }
}