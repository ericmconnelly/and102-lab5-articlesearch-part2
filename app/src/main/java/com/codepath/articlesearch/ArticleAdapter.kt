package com.codepath.articlesearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

const val ARTICLE_EXTRA = "ARTICLE_EXTRA"
private const val TAG = "ArticleAdapter"

class ArticleAdapter(private val context: Context, private val articles: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_article, parent, false)
        return ViewHolder(view)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        // TODO: Get the individual article and bind to holder
        val article = articles[position]
        holder.bind(article)
    }

    override fun getItemCount() = articles.size

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {

        private val mediaImageView = itemView.findViewById<ImageView>(R.id.mediaImage)
        private val titleTextView = itemView.findViewById<TextView>(R.id.mediaTitle)
        private val abstractTextView = itemView.findViewById<TextView>(R.id.mediaAbstract)

        init {
            itemView.setOnClickListener(this)
        }

        fun bind(article: Article) {
            titleTextView.text = article.headline?.main
            abstractTextView.text = article.abstract

            Glide.with(context)
                .load(article.mediaImageUrl)
                .into(mediaImageView)
        }

        // TODO: Write a helper method to help set up the onBindViewHolder method

        override fun onClick(v: View?) {
            // TODO: Get selected article
            val article = articles[absoluteAdapterPosition]

            // TODO: Navigate to Details screen and pass selected article

            // We can create an Intent, using the current context, and the Class that we are navigating to.
            val intent = Intent(context, DetailActivity::class.java)

            // We can use .putExtra to pass along the article, with the first argument being the key for the data, and the next being article itself.
            intent.putExtra(ARTICLE_EXTRA, article)

            // We can then call startActivity with the current context, using the intent as its parameter.
            context.startActivity(intent)
        }
    }
}