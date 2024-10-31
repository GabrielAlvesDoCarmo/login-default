package br.com.gds.login.feature.register.personal.view

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.closeSoftKeyboard
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import br.com.gds.login.R
import br.com.gds.login.commons.LoginModuleMatchers
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalViewModel
import br.com.gds.login.utils.commons.FormState
import io.mockk.every
import io.mockk.mockk
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.loadKoinModules
import org.koin.core.context.stopKoin
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

@RunWith(AndroidJUnit4::class)
class RegisterPersonalFragmentTest {
    private lateinit var scenario: FragmentScenario<RegisterPersonalFragment>

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        startKoin {
            modules(testModule)
        }
        loadKoinModules(testModule)
        scenario = launchFragmentInContainer<RegisterPersonalFragment>(
            initialState = Lifecycle.State.INITIALIZED,
            themeResId = R.style.TestTheme,
        )

        val mockFormState = MutableLiveData(FormState())
        val mockViewModel = mockk<RegisterPersonalViewModel>(relaxed = true)
        every { mockViewModel.formState } returns mockFormState
    }

    @After
    fun tearDown() {
        unloadKoinModules(testModule)
        stopKoin()
    }

    @Test
    fun testLifeCycle() {
        scenario.moveToState(Lifecycle.State.CREATED)
        scenario.moveToState(Lifecycle.State.STARTED)
        scenario.moveToState(Lifecycle.State.RESUMED)
        scenario.moveToState(Lifecycle.State.DESTROYED)
    }

    @Test
    fun validateBackgroundScreenColor() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(R.id.container_register_person_constraint_root)).check(matches(isCompletelyDisplayed()))
//        onView(withId(R.id.container_register_person_constraint_root)).check(matches(hasBackground(R.color.white)))
    }


    @Test
    fun testTitleScreen() {
        val viewID = R.id.register_text_title
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(withId(viewID)).check(matches(isDisplayed()))
        onView(withId(viewID)).check(matches(withText("Registre-se")))
        onView(withId(viewID)).check(matches(LoginModuleMatchers.withFontSize(32f)))
        onView(withId(viewID)).check(matches(ViewMatchers.hasTextColor(R.color.black)))
    }

    @Test
    fun testTypingInNameField() {

        scenario.moveToState(Lifecycle.State.RESUMED)
        disabledAnim()
        // 1. Verifique se o campo está visível
        onView(withId(R.id.register_name_layout)).check(matches(isDisplayed()))

        // 2. Dê foco ao campo
        onView(withId(R.id.register_name_edit)).perform(click())

        // 3. Digite o texto
        onView(withId(R.id.register_name_edit)).perform(typeText("Seu nickname aqui"))

        // 4. Feche o teclado virtual (opcional)
        onView(withId(R.id.register_name_edit)).perform(closeSoftKeyboard())

        //5. Realize asserções para verificar o texto digitado
        onView(withId(R.id.register_name_edit)).check(matches(withText("Seu nickname aqui")))


    }


}

fun disabledAnim(){
    val animationScale = 0f // ou 0f para desabilitar completamente
    val instrumentation = InstrumentationRegistry.getInstrumentation()
    instrumentation.uiAutomation.executeShellCommand("settings put global window_animation_scale $animationScale")
    instrumentation.uiAutomation.executeShellCommand("settings put global transition_animation_scale $animationScale")
    instrumentation.uiAutomation.executeShellCommand("settings put global animator_duration_scale $animationScale")
}

val testModule = module {
    single { mockk<RegisterPersonalViewModel>(relaxed = true) }
}