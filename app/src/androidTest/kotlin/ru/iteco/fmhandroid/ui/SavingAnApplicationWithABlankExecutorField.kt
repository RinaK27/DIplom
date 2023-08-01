package ru.iteco.fmhandroid.ui


import android.view.View
import android.view.ViewGroup
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.*
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
class SavingAnApplicationWithABlankExecutorField {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun savingAnApplicationWithABlankExecutorField() {
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

        val materialButton2 = onView(
            allOf(
                withId(R.id.add_new_claim_material_button),
                withContentDescription("Add new claim button"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_list_claim_include),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val textInputEditText3 = onView(
            allOf(
                withId(R.id.title_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.title_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText3.perform(replaceText("testwithoutexecutor"), closeSoftKeyboard())

        val textInputEditText4 = onView(
            allOf(
                withId(R.id.date_in_plan_text_input_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.date_in_plan_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText4.perform(click())

        val materialButton3 = onView(
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
        materialButton3.perform(scrollTo(), click())

        val textInputEditText5 = onView(
            allOf(
                withId(R.id.time_in_plan_text_input_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.time_in_plan_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText5.perform(click())

        val materialButton4 = onView(
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
        materialButton4.perform(scrollTo(), click())

        val textInputEditText6 = onView(
            allOf(
                withId(R.id.description_edit_text),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.description_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        textInputEditText6.perform(replaceText("12345"), closeSoftKeyboard())

        val materialButton5 = onView(
            allOf(
                withId(R.id.save_button), withText("Save"), withContentDescription("Save"),
                childAtPosition(
                    childAtPosition(
                        withClassName(`is`("com.google.android.material.card.MaterialCardView")),
                        0
                    ),
                    6
                )
            )
        )
        materialButton5.perform(scrollTo(), click())
        Thread.sleep(3000)
        val recyclerView = onView(
            allOf(withId(R.id.claim_list_recycler_view), isDisplayed())
        )
        recyclerView.check(matches(isDisplayed()))
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
