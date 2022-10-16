package hu.bme.aut.android.deliveryapp.view

import hu.bme.aut.android.deliveryapp.model.JobDetails

sealed class JobDetailState {
    object inProgress : JobDetailState()
    data class jobDetailsResponseSuccess(val data: List<JobDetails> ) : JobDetailState()
    data class jobDetailsResponseError(val exceptionMsg: String) : JobDetailState()
}