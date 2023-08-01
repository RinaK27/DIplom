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
@RunWith(AndroidJUnit4::class)
class EditingAnApplicationInOpenStatus {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun editingAnApplicationInOpenStatus() {
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
        val materialButton2 = onView(
            allOf(
                withId(R.id.filters_material_button),
                withContentDescription("Filter claim list menu button"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_list_claim_include),
                        0
                    ),
                    1
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialCheckBox = onView(
            allOf(
                withId(R.id.item_filter_in_progress), withText("In progress"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    2
                )
            )
        )
        materialCheckBox.perform(scrollTo(), click())

        val materialButton3 = onView(
            allOf(
                withId(R.id.claim_list_filter_ok_material_button), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    5
                )
            )
        )
        materialButton3.perform(scrollTo(), click())
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
        recyclerView.perform(actionOnItemAtPosition<ViewHolder>(3, click()))
        Thread.sleep(2000)
        val appCompatImageButton = onView(
            allOf(
                withId(R.id.edit_processing_image_button),
                withContentDescription("button settings"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0
                    ),
                    25
                ),
                isDisplayed()
            )
        )
        appCompatImageButton.perform(click())
        Thread.sleep(2000)
        val textView = onView(
            allOf(withId(R.id.custom_app_bar_title_text_view), withText("Editing"), isDisplayed())
        )
        textView.check(matches(withText("Editing")))

        val materialButton4 = onView(
            allOf(
                withId(R.id.cancel_button), withText("Cancel"), withContentDescription("Cancel"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0
                    ),
                    7
                )
            )
        )
        materialButton4.perform(scrollTo(), click())

        val materialButton5 = onView(
            allOf(
                withId(android.R.id.button1), withText("OK"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("android.widget.ScrollView")),
                        0
                    ),
                    3
                )
            )
        )
        materialButton5.perform(scrollTo(), click())
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
