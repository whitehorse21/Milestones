package com.ydl.android.data.remote.goals

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Single

interface GoalManager {
    companion object {
        const val FALLBACK: String = "FALLBACK"
    }

    fun createGoal(goal: Goal): Completable
    fun getGoalsIds(): Single<String>
    fun getGoals(): Flowable<Goal>
    fun getGoalForId(goalId: String): Flowable<Goal>
}