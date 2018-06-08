package com.example.nguyenngocsonc.myapplication.newfeed.view

import com.example.nguyenngocsonc.myapplication.models.DataFeed

interface INewFeedView {
    fun onSetSwipeRefreshing()
    fun showNewFeedData(dataFeed: DataFeed)
    fun showFailFetchData(mess: String?)
    fun showDialogLoading()
    fun hideDialogLoading()
}
