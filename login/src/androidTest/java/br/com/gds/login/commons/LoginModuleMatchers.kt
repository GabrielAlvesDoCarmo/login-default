package br.com.gds.login.commons

import android.util.TypedValue
import android.view.View
import android.widget.TextView
import androidx.annotation.ColorRes
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.TypeSafeMatcher

object LoginModuleMatchers {

    fun withFontSize(expectedSizeInSp: Float): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            override fun matchesSafely(item: View): Boolean {
                if (item !is TextView) {
                    return false
                }
                val resources = item.context.resources
                val expectedSizeInPixels = TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_SP,
                    expectedSizeInSp,
                    resources.displayMetrics
                )
                val actualSize = item.textSize
                return expectedSizeInPixels == actualSize
            }

            override fun describeTo(description: Description?) {
                description?.appendText("with fontSize: ")
                description?.appendValue(expectedSizeInSp)
            }
        }
    }

    fun withTextColor(
        @ColorRes expectedColor: Int
    ): Matcher<View> {
        return object : TypeSafeMatcher<View>() {
            private var actualColor: Int = 0
            override fun matchesSafely(item: View): Boolean {
                if (item !is TextView) {
                    return false
                }
                actualColor = item.currentTextColor
                return expectedColor == actualColor
            }

            override fun describeTo(description: Description?) {
                description?.appendText("with textColor: ")
                description?.appendValue(expectedColor)
                description?.appendText(", but got: ")
                description?.appendValue(actualColor) // Acesse a cor atual
            }
        }
    }

}