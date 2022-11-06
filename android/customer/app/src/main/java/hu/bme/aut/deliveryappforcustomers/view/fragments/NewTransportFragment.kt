package hu.bme.aut.deliveryappforcustomers.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import hu.bme.aut.android.deliveryapp.model.Delivery
import hu.bme.aut.deliveryappforcustomers.databinding.FragmentNewTransportBinding
import hu.bme.aut.deliveryappforcustomers.model.Capacity
import hu.bme.aut.deliveryappforcustomers.model.DeliveryStatus
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
            viewModel.createNewTransport(Delivery(
                title = binding.transportName.editText?.text.toString(),
                price = binding.maxPrice.editText?.text.toString().toFloat(),
                capacity = Capacity(
                    width = binding.width.editText?.text.toString().toFloat(),
                    height = binding.height.editText?.text.toString().toFloat(),
                    length = binding.length.editText?.text.toString().toFloat(),
                    weight = binding.weight.editText?.text.toString().toFloat()
                ),
                sourceLocation = null,
                destinationLocation = null,
                clientUser = CurrentUser.token,
                transporterUser = null,
                status = DeliveryStatus.UNASSIGNED,
                clientRating = null,
                description = "no description",
                transporterRating = 0.0f,
                createdAt = "2021-05-01T00:00:00.000Z",
                updatedAt = "not updated yet",
                _id = "id",
                pickUpFrom = "pick up from",
                pickUpUntil = "pick up until",
                pictureUrl = "picture url"
            ))
        }
    }

}