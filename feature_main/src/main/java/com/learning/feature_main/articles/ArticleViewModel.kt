package com.learning.feature_main.articles

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.article.Article
import com.learning.lessons.domain.repositories.UserInfoRepository
import com.learning.lessons.domain.usecases.ArticleUseCase
import com.learning.lessons.domain.usecases.UserInfoUseCase
import com.learning.lessons.utils.utils.Logger
import com.learning.navigation.Router
import data.DataStoreHelper
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleViewModel  @Inject constructor(
    private val articleUseCase: ArticleUseCase, private val userInfoUseCase: UserInfoUseCase, private val dataStoreHelper: DataStoreHelper
) : ViewModel() {

    fun getData() = flow<State<Pair<List<Article>, List<Int>>>> {
        emit(State.LoadingState)
        try {
            dataStoreHelper.userID().collect {

                emit(State.DataState(articleUseCase.getArticles() to userInfoUseCase.getCompletedArticlesIds(it)))
            }
        } catch (e: Exception){
            Logger.log("ArticleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }


}