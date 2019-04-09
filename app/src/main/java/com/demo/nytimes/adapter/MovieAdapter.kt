package com.demo.nytimes.adapter
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.demo.nytimes.R
import com.demo.nytimes.models.CustomArticleModel
import com.demo.nytimes.viewHolder.AritcleViewHolder
import com.squareup.picasso.Picasso
import com.demo.nytimes.util.CircleTransform


class MovieAdapter constructor(
    private val context: Context,
    private val movies: List<CustomArticleModel>,
    private val picasso: Picasso = Picasso.with(context)
) :
    RecyclerView.Adapter<AritcleViewHolder>() {

    private val layoutInflater = LayoutInflater.from(context)

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): AritcleViewHolder =
        AritcleViewHolder(layoutInflater.inflate(R.layout.item_most_popular, p0, false))

    override fun getItemCount() = movies.size

    override fun onBindViewHolder(holder: AritcleViewHolder, position: Int) {
        val customArticleModel = movies[position]
        val leftUrl = customArticleModel.imgUrl
        Picasso.with(context).load(leftUrl).transform(CircleTransform()).into(holder.iv_rounded)
        holder.tv_title.text = customArticleModel.title
        holder.tv_reporter.text = customArticleModel.byline
        holder.tv_date.text = customArticleModel.date


    }
}