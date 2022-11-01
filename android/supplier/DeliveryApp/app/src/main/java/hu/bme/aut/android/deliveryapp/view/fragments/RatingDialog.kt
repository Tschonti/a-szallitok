package hu.bme.aut.android.deliveryapp.view.fragments

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import hu.bme.aut.android.deliveryapp.databinding.DialogRatingBinding


class RatingDialog(val listener: RateDialogSubmittedListener) : DialogFragment() {

    interface RateDialogSubmittedListener {
        fun rateSubmitted(rating: Int)
    }

    private lateinit var binding: DialogRatingBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DialogRatingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnSubmitRating.setOnClickListener {
            listener.rateSubmitted(binding.ratingBar.numStars)
            dismiss()
        }
    }
}