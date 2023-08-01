package ru.iteco.fmhandroid.ui


import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import io.qameta.allure.android.runners.AllureAndroidJUnit4
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.allOf
import org.hamcrest.Matchers.`is`
import org.hamcrest.TypeSafeMatcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import ru.iteco.fmhandroid.R

@LargeTest
@RunWith(AllureAndroidJUnit4::class)
class AddingACommentToAnApplication {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun addingACommentToAnApplication() {
        Thread.sleep(5000)
        val materialTextView = onView(
            allOf(
                withId(R.id.all_claims_text_view), withText("all claims"),
                childAtPosition(
                    allOf(
                        withId(R.id.container_list_claim_include_on_fragment_main),
                        childAtPosition(
                            withClassName(`is`("android.widget.LinearLayout")),
                            1
                        )
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialTextView.perform(click())
        Thread.sleep(2000)
        val recyclerView = onView(
            allOf(
                withId(R.id.claim_list_recycler_view),
                childAtPosition(
                    withId(R.id.all_claims_cards_block_constraint_layout),
                    4
                )
            )
        )
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(1, click()))
        Thread.sleep(2000)
        val appCompatImageButton = onView(
            allOf(
                withId(R.id.add_comment_image_button), withContentDescription("button add comment"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.comments_material_card_view),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
        Thread.sleep(2000)
        val button = onView(
            allOf(
                withId(R.id.cancel_button),
                withText("CANCEL"),
                withContentDescription("Cancel"),
                isDisplayed()
            )
        )
        button.check(matches(isDisplayed()))

        val materialButton2 = onView(
            allOf(
                withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0
                    ),
                    2
                )
            )
        )
        materialButton2.perform(scrollTo(), click())





    }

    private fun childAtPosition(
        parentMatcher: Matcher<View>, position: Int
    ): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position)
            }
        }
    }
}
