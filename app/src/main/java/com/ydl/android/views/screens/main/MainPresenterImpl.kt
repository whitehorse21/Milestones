package com.ydl.android.views.screens.main

import com.ydl.android.base.BaseRxPresenter
import com.ydl.android.data.remote.goals.Goal
import com.ydl.android.data.remote.goals.GoalManager
import com.ydl.android.data.remote.goals.Mode
import com.ydl.android.data.remote.session.SessionManager
import com.ydl.android.utils.getCompletedCount
import javax.inject.Inject

class MainPresenterImpl
@Inject constructor(
        private val goalManager: GoalManager,
        private val sessionManager: SessionManager
) : BaseRxPresenter(), MainContract.Presenter {

    override fun logOut() {
        view.showProgressDialog()
        manage(sessionManager.logout()
                .subscribe({
                    view.hideProgressDialog()
                    view.navigateToSplashScreen()
                }, { throwable ->
                    view.showInAppError(throwable.message!!)
                }))
    }

    private val mList = ArrayList<Goal>()

    private fun checkHasGoals() {
        manage(goalManager.getGoalsIds().doOnNext({
            if (it.isEmpty()) {
                view.createNewGoal()
            }
        }).subscribe())
    }

    override fun subscribe() {
        checkHasGoals()
        manage(goalManager.getGoals(Mode.ALL).doOnNext({
            mList.add(it)
            view.setCurrentGoals(mList.getCompletedCount())
            view.setTotalGoals(mList.size)
        }).subscribe())
    }

    private lateinit var view: MainContract.View

    override fun setView(view: MainContract.View) {
        this.view = view
    }

}