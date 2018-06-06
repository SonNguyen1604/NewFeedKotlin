package com.example.nguyenngocsonc.myapplication.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.models.Story
import kotlinx.android.synthetic.main.item_story.view.*

class StoryAdapter(private var stories: ArrayList<Story>, private val context: Context) : RecyclerView.Adapter<StoryAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_story, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        var story = stories[position]
        Glide.with(context).load(story.storyImageUrl).into(holder.imgStory)
        holder.txtStory.text = story.fullName
    }

    override fun getItemCount(): Int {
        return stories.size
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val imgStory: ImageView = view.img_story
        val txtStory = view.txt_story!!
    }
}
