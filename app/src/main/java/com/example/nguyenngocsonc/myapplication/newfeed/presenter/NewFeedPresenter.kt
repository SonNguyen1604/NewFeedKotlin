package com.example.nguyenngocsonc.myapplication.newfeed.presenter

import android.content.Context
import android.net.ConnectivityManager
import com.example.nguyenngocsonc.myapplication.interfaces.NewFeedServiceInterface
import com.example.nguyenngocsonc.myapplication.newfeed.view.INewFeedView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class NewFeedPresenter(private var context: Context, private var iNewFeedView: INewFeedView) : INewFeedPresenter {

    private val newFeedApi by lazy {
        NewFeedServiceInterface.create(context)
    }

    private var disposable: CompositeDisposable = CompositeDisposable()

    override fun doGetNewFeed() {
        if (!isNetWorkAvailable()) {
            iNewFeedView.onSetSwipeRefreshing()
        } else {
            disposable.add(newFeedApi.queryNewFeed().subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .doOnSubscribe {
                        iNewFeedView.showDialogLoading()
                    }
                    .doOnError {
                        iNewFeedView.hideDialogLoading()
                    }
                    .subscribe(
                            { result -> iNewFeedView.showNewFeedData(result) },
                            { error -> iNewFeedView.showFailFetchData(error.message) }
                    ))
        }
    }

    override fun isNetWorkAvailable(): Boolean {
        val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }
}
