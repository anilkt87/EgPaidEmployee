package com.egpaid.employeeapp

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.doesNotExist
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.filters.LargeTest
import androidx.test.internal.runner.junit4.AndroidJUnit4ClassRunner
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.rule.ActivityTestRule
import com.egpaid.employeeapp.signin.view.adapter.PersonAdapter
import com.egpaid.employeeapp.signin.view.view.SigninActivity
import junit.framework.Assert
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@LargeTest
@RunWith(
        AndroidJUnit4ClassRunner::class
)
class SigninActivityTest {
    @get:Rule
    var activityRule = ActivityTestRule(SigninActivity::class.java)

    @Test
    fun useAppContext() {
        val appContext = InstrumentationRegistry.getInstrumentation().getTargetContext()
        Assert.assertEquals("com.anil.gorestapp", appContext.getPackageName())
    }


    @Test
    fun container_IsDisplayed() {


        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withId(R.id.toolbar)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)))
        onView(withText("asdasdsa")).check(doesNotExist())
    }


    @Test
    fun recyclerViewScrollToPositionTest() {
            tapRecyclerViewItem(R.id.recyclerView,15)


    }

    fun tapRecyclerViewItem(recyclerViewId: Int, position: Int) {
        onView(withId(recyclerViewId)).perform(scrollToPosition<PersonAdapter.ViewHolder>(position))
    }


}