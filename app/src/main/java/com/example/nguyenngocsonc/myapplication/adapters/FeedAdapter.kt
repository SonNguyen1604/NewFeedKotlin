package com.example.nguyenngocsonc.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.models.Feed
import kotlinx.android.synthetic.main.item_feed.view.*

class FeedAdapter(private var feeds: ArrayList<Feed>, private val context: Context) : RecyclerView.Adapter<FeedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_feed, parent, false))
    }

    override fun getItemCount(): Int {
        return feeds.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var feed = feeds[position]
        Glide.with(context).load(feed.avatarUrl).into(holder.imgAvatar)
        holder.txtName.text = feed.fullName
        holder.txtTime.text = feed.createAt
        holder.txtFeed.text = feed.feedContent
        Glide.with(context).load(feed.feedImages[0]).into(holder.imgFeed)
        holder.txtReactionCount.text = feed.reactionCount.toString()
        holder.txtCommentCount.text = feed.commentCount.toString() + " comments"
        holder.txtShareCount.text = feed.sharingCount.toString() + " sharing"
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgAvatar = view.img_avatar!!
        val txtName = view.txtName!!
        val txtTime = view.txt_time!!
        val txtFeed = view.txt_feed!!
        val imgFeed = view.img_feed!!
        val txtReactionCount = view.txt_reaction_count!!
        val txtCommentCount = view.txt_comment_count!!
        val txtShareCount = view.txt_share_count!!
    }
}
