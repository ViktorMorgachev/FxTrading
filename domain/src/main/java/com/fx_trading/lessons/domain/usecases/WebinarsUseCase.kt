package com.fx_trading.lessons.domain.usecases

import com.fx_trading.lessons.domain.entities.webinar.Webinar
import com.fx_trading.lessons.domain.repositories.WebinarRepository
import javax.inject.Inject

// TODO Тут описываем все случаи использования пользуясь тольоко теми функциями что имеем, переделать остальную логику в lessons, users, questions, вынести отдельно логику в UsersInfo
class WebinarsUseCase @Inject constructor(private val webinarsRepository: WebinarRepository) {

    suspend fun getWebinars(): List<Webinar>{
       return webinarsRepository.getWebinars()
    }

}