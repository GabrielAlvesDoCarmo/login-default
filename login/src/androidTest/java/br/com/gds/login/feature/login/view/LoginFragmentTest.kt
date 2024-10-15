package br.com.gds.login.feature.login.view

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import br.com.gds.login.databinding.FragmentLoginBinding
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class LoginFragmentTest {
    @Test
    fun launchFragment(){
        val scenario = launchFragmentInContainer<LoginFragment>()
        scenario.onFragment{ fragment ->
            val binding = FragmentLoginBinding.bind(fragment.requireView())
            onView(withId(binding.loginTextTitle.id)).check(matches(withText("Login")))
//            assertThat(onView(withId(binding.loginTextTitle.id)).check(matches(withText("Login"))), `is`(notNullValue()))
        }

    }
}