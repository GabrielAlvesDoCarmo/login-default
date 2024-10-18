package br.com.gds.login.feature.login.view

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gds.login.databinding.FragmentLoginBinding
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    private lateinit var scenario : FragmentScenario<LoginFragment>


    @Before
    fun setUp() {
        scenario = launchFragmentInContainer<LoginFragment>()
    }


    @Test
    fun launchFragment(){
        scenario.onFragment{ fragment ->
            val binding = FragmentLoginBinding.bind(fragment.requireView())
            onView(withText("Login")).check(matches(isDisplayed()))
        }

    }
}