package com.example.nguyenngocsonc.myapplication.newfeed

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.LinearLayout
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.adapters.FeedAdapter
import com.example.nguyenngocsonc.myapplication.adapters.StoryAdapter
import com.example.nguyenngocsonc.myapplication.models.Feed
import com.example.nguyenngocsonc.myapplication.models.Story
import kotlinx.android.synthetic.main.activity_new_feed.*

class NewFeedActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)

        recycler_story.layoutManager = LinearLayoutManager(this@NewFeedActivity, LinearLayout.HORIZONTAL, false)

        val stories = ArrayList<Story>()
        recycler_story.adapter = StoryAdapter(stories, this@NewFeedActivity)

        recycler_feed.layoutManager = LinearLayoutManager(this@NewFeedActivity, LinearLayout.VERTICAL, false)
        val feeds = ArrayList<Feed>()
        recycler_feed.adapter = FeedAdapter(feeds, this@NewFeedActivity)
    }
}
