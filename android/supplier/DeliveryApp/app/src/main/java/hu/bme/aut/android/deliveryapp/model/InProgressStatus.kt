package hu.bme.aut.android.deliveryapp.model

import java.io.Serializable

enum class InProgressStatus : Serializable {
    ACCEPTED,
    REJECTED,
    PENDING,
}