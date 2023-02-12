package com.erbe.nowinandroid.core.firebase

import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.analytics.ktx.logEvent
import javax.inject.Inject

class AnalyticImpl @Inject constructor(
    private val firebaseAnalytics: FirebaseAnalytics
) : Analytic {

    override fun firebaseAnalytic() {
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.SELECT_ITEM) {
            param(FirebaseAnalytics.Param.ITEM_ID, "id-ok")
            param(FirebaseAnalytics.Param.ITEM_NAME, "name-ok")
            param(FirebaseAnalytics.Param.CONTENT_TYPE, "image-ok")
        }
    }
}