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
class CreatingAnApplicationWithAClaimOnTheMainPage {

    @Rule
    @JvmField
    var mActivityScenarioRule = ActivityScenarioRule(AppActivity::class.java)

    @Test
    fun creatingAnApplicationWithAClaimOnTheMainPage() {
        Thread.sleep(5000)
        val materialButton2 = onView(
            allOf(
                withId(R.id.add_new_claim_material_button),
                withContentDescription("Add new claim button"),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.container_list_claim_include_on_fragment_main),
                        0
                    ),
                    2
                ),
                isDisplayed()
            )
        )
        materialButton2.perform(click())

        val materialAutoCompleteTextView = onView(
            allOf(
                withId(R.id.executor_drop_menu_auto_complete_text_view),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.executor_drop_menu_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialAutoCompleteTextView.perform(click())

        val materialAutoCompleteTextView2 = onView(
            allOf(
                withId(R.id.executor_drop_menu_auto_complete_text_view),
                childAtPosition(
                    childAtPosition(
                        withId(R.id.executor_drop_menu_text_input_layout),
                        0
                    ),
                    0
                ),
                isDisplayed()
            )
        )
        materialAutoCompleteTextView2.perform(
            replaceText("Alekseev Aleksei Alekseevich"),
            closeSoftKeyboard()
        )

        val textInputEditText3 = onView(
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
        textInputEditText3.perform(click())

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

        val textInputEditText4 = onView(
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
        textInputEditText4.perform(click())

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

        val textInputEditText5 = onView(
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
        textInputEditText5.perform(replaceText("test12345"), closeSoftKeyboard())

        val textInputEditText6 = onView(
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
        textInputEditText6.perform(replaceText("Test_final1111"), closeSoftKeyboard())

        val textInputEditText7 = onView(
            allOf(
                withId(R.id.title_edit_text), withText("Test_final1111"),
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
        textInputEditText7.perform(click())

        val textInputEditText8 = onView(
            allOf(
                withId(R.id.title_edit_text), withText("Test_final1111"),
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
        textInputEditText8.perform(replaceText("TestClaim1111"))

        val textInputEditText9 = onView(
            allOf(
                withId(R.id.title_edit_text), withText("TestClaim1111"),
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
        textInputEditText9.perform(closeSoftKeyboard())
        Thread.sleep(5000)
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
        Thread.sleep(5000)
        val linearLayout = onView(
            allOf(withId(R.id.container_list_claim_include_on_fragment_main), isDisplayed())
        )
        linearLayout.check(matches(isDisplayed()))
        Thread.sleep(5000)
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
