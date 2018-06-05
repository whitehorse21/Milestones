package com.ydl.android.utils

import com.ydl.android.data.remote.goals.Milestone
import org.joda.time.DateTime

fun List<Milestone>.getLatestDate(): DateTime {
    return this.maxBy { milestone -> milestone.dueDateInDateTime.millis }!!.dueDateInDateTime
}

fun List<Milestone>.getCompletedMilestonesCount(): Int {
    return this.count { it.completed }
}

fun List<Milestone>.checkMissed(): Boolean {
    return this.map { !it.completed && it.dueDateInDateTime.isBeforeNow }.contains(true)
}

fun List<Milestone>.getNextMilestone(): Milestone {
    return this.first { !it.completed }
}