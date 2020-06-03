package com.yousef.mvvmflightinfo.ui.main

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.Intents.*
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import androidx.test.rule.ActivityTestRule
import androidx.test.rule.GrantPermissionRule
import com.yousef.mvvmflightinfo.R
import com.yousef.mvvmflightinfo.ui.schedules.SchedulesActivity
import org.hamcrest.CoreMatchers.allOf
import org.hamcrest.CoreMatchers.startsWith
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class MainActivityUITest {

    @Rule
    @JvmField
    val rule = ActivityTestRule(MainActivity::class.java, true, true)

    @Rule
    @JvmField
    val rule2 = ActivityTestRule(SchedulesActivity::class.java, true, true)

    @Rule
    @JvmField
    var mGrantPermissionRule =
            GrantPermissionRule.grant(
                    "android.permission.ACCESS_FINE_LOCATION",
                    "android.permission.INTERNET")

    @Test
    fun testBtShowSchedulesAppearanceAndEditTexts(){
        init()
        onView(isRoot()).perform(waitFor(7000))
        onView(withId(R.id.btShowSchedules)).check(matches(isDisplayed()))

        onView(withId(R.id.etOrigin)).perform(ViewActions.click())
        onView(withId(R.id.etOrigin)).perform(ViewActions.typeTextIntoFocusedView("Fra"))
        onView(withId(R.id.root)).check(matches(allOf(
                hasDescendant(allOf(withId(R.id.etOrigin), withText(startsWith("Fra")))))))

        onView(withId(R.id.btShowSchedules)).perform(ViewActions.click())
        onView(isRoot()).perform(waitFor(2000))
        onView(withId(com.google.android.material.R.id.snackbar_text))
                .check(matches(withText(R.string.enter_destination)))

        onView(withId(R.id.etDestination)).perform(ViewActions.click())
        onView(withId(R.id.etDestination)).perform(ViewActions.typeTextIntoFocusedView("Ham"))
        release()
    }

    @Test
    @Throws(Exception::class)
    fun testShowSchedulesActivity() {
        init()
        onView(withId(R.id.etOrigin)).perform(ViewActions.click())
        onView(withId(R.id.etOrigin)).perform(ViewActions.typeTextIntoFocusedView("Fra"))
        onView(withId(R.id.etOrigin)).perform(ViewActions.click())
        onView(withId(R.id.etDestination)).perform(ViewActions.click())
        onView(withId(R.id.etDestination)).perform(ViewActions.typeTextIntoFocusedView("Ham"))
        onView(withId(R.id.etDestination)).perform(ViewActions.click())

        onView(withId(R.id.btShowSchedules)).perform(ViewActions.click())
        intended(hasComponent(SchedulesActivity::class.java.name))
        intended(hasComponent(MainActivity::class.java.name),times(0))
        release()
    }

    /**
     * Perform action of waiting for a specific time.
     */
    fun waitFor(millis: Long): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String {
                return "Wait for $millis milliseconds."
            }

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(millis)
            }
        }
    }
}
