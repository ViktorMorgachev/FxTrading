package com.learning.feature_main.article

import androidx.lifecycle.ViewModel
import com.learning.common.State
import com.learning.common.createState
import com.learning.lessons.domain.entities.article.Article
import com.learning.lessons.domain.usecases.ArticleUseCase
import com.learning.lessons.utils.utils.Logger
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class ArticleViewModel @Inject constructor(
    private val articleUseCase: ArticleUseCase
) : ViewModel() {

    fun getArticleByID(articleID: Int) = flow<State<Article>> {
        emit(State.LoadingState)
        try {
            val article = articleUseCase.getArticleByID(articleID)
            emit(createState(article))
        } catch (e: Exception){
            Logger.log("ExampleViewModel", exception = e)
            emit(State.ErrorState(e))
        }
    }

}