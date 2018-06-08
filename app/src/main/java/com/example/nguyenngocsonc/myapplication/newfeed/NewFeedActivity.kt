package com.example.nguyenngocsonc.myapplication.newfeed

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.widget.LinearLayout
import android.widget.Toast
import com.afollestad.materialdialogs.MaterialDialog
import com.example.nguyenngocsonc.myapplication.R
import com.example.nguyenngocsonc.myapplication.adapters.FeedAdapter
import com.example.nguyenngocsonc.myapplication.adapters.StoryAdapter
import com.example.nguyenngocsonc.myapplication.models.DataFeed
import com.example.nguyenngocsonc.myapplication.newfeed.presenter.NewFeedPresenter
import com.example.nguyenngocsonc.myapplication.newfeed.view.INewFeedView
import kotlinx.android.synthetic.main.activity_new_feed.*


class NewFeedActivity : AppCompatActivity(), INewFeedView {
    private lateinit var dialogLoading: MaterialDialog
    private var iNewFeedPresenter = NewFeedPresenter(this@NewFeedActivity, this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_new_feed)
        dialogLoading = MaterialDialog.Builder(this)
                .cancelable(false)
                .canceledOnTouchOutside(false)
                .progress(true, 0)
                .content("Loading...")
                .build()
        dialogLoading.hide()

        iNewFeedPresenter.doGetNewFeed()

        swipeRefreshFeed.setOnRefreshListener {
            iNewFeedPresenter.doGetNewFeed()
        }
    }

    override fun showNewFeedData(dataFeed: DataFeed) {
        recycler_story.layoutManager = LinearLayoutManager(this@NewFeedActivity, LinearLayout.HORIZONTAL, false)

        val stories = dataFeed.stories
        recycler_story.adapter = StoryAdapter(stories, this@NewFeedActivity)

        recycler_feed.layoutManager = LinearLayoutManager(this@NewFeedActivity, LinearLayout.VERTICAL, false)

        val feeds = dataFeed.feeds
        recycler_feed.adapter = FeedAdapter(feeds, this@NewFeedActivity)
        dialogLoading.hide()
        if (swipeRefreshFeed.isRefreshing) {
            swipeRefreshFeed.isRefreshing = false
        }
    }

    override fun showDialogLoading() {
        runOnUiThread {
            dialogLoading.show()
        }
    }

    override fun hideDialogLoading() {
        runOnUiThread {
            dialogLoading.dismiss()
        }
    }

    override fun showFailFetchData(mess: String?) {
        Log.d("failed", mess)
    }

    override fun onSetSwipeRefreshing() {
        if (swipeRefreshFeed.isRefreshing) {
            swipeRefreshFeed.isRefreshing = false
        }
        Toast.makeText(this@NewFeedActivity, getString(R.string.internet_not_available), Toast.LENGTH_SHORT).show()
    }

    override fun onDestroy() {
        dialogLoading.dismiss()
        super.onDestroy()
    }
}
