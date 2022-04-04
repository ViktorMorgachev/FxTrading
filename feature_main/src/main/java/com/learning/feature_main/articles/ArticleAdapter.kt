package com.learning.feature_main.articles

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.learning.lessons.domain.entities.article.Article
import com.learning.lessons.features.R
import com.learning.lessons.features.databinding.ArticleItemBinding
import com.learning.lessons.features.databinding.ItemAnswerBinding

class ArticleAdapter(private val data: List<Article>, private val passedArticles: List<Int>,private val openArticleAction: (Article)->Unit): RecyclerView.Adapter<ArticleAdapter.ArticleHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleHolder {
        val itemBinding = ArticleItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleHolder(itemBinding, data = data)
    }

    override fun onBindViewHolder(holder: ArticleHolder, position: Int) {
        val data = data[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int = data.size


  inner  class ArticleHolder(private val itemBinding: ArticleItemBinding, val data: List<Article>) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(article: Article) {
            with(itemBinding){
                Glide.with(itemView.context)
                    .load(article.imageUrl).error(R.drawable.mock_video_image)
                    .into(ivPromoArticle)
                title.text = article.title
                if (passedArticles.contains(article.id)) {
                    articleItemRoot.setBackgroundColor(itemBinding.root.resources.getColor(R.color.completed_color_identifier))
                }
                articleItemRoot.setOnClickListener {
                    openArticleAction(article)
                }
                dateOfArticle.text = article.dateDescription
                difficultyItem.setDifficulty(article.difficulty)
            }
        }

    }
}