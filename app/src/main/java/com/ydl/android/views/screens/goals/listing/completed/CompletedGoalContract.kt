package com.ydl.android.views.screens.goals.listing.completed

import com.ydl.android.base.BasePresenter
import com.ydl.android.base.BaseView
import com.ydl.android.data.remote.goals.Goal


interface CompletedGoalContract {

    interface View : BaseView {

        fun showInAppError(message: String)

        fun addGoal(it: Goal)

    }

    interface Presenter : BasePresenter<View>
}