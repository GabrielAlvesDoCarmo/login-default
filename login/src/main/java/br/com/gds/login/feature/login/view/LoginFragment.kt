package br.com.gds.login.feature.login.view

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.databinding.FragmentLoginBinding
import br.com.gds.login.feature.login.model.LoginUI
import br.com.gds.login.feature.login.viewmodel.LoginUIState
import br.com.gds.login.feature.login.viewmodel.LoginViewModel
import br.com.gds.login.feature.login.viewmodel.isFormValid
import br.com.gds.login.utils.commons.LayoutSetup
import br.com.gds.login.utils.extensions.changeCheckBoxColor
import br.com.gds.login.utils.extensions.changeColorImageButton
import br.com.gds.login.utils.extensions.createCustomShapeDrawable
import br.com.gds.login.utils.extensions.edittext.EditTextState
import br.com.gds.login.utils.extensions.navigateTo
import br.com.gds.login.utils.extensions.setStartDrawableWithTint
import br.com.gds.login.utils.extensions.toastMessage
import br.com.gds.login.utils.helpers.PreferencesHelper
import br.com.gds.login.utils.helpers.biometric.BiometricHelper
import br.com.gds.login.utils.helpers.biometric.LoginBiometricCallback
import br.com.gds.login.utils.helpers.bottomsheet.PhoneLoginBottomSheet
import org.koin.androidx.viewmodel.ext.android.viewModel

class LoginFragment : Fragment(), LoginBiometricCallback {
    //TODO(RECONHECIMENTO FACIAL, voz , iris , e de retina)
    private lateinit var binding: FragmentLoginBinding
    private val viewModel: LoginViewModel by viewModel()
    private val defaultColors by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup?.layoutDefault?.defaultColors
            ?: LayoutSetup().layoutDefault.defaultColors
    }
    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup?.loginFragment ?: LoginUI()
    }
    private val provider by lazy {
        LoginModuleSession.loginModuleDependency?.loginModuleCallbackProvider
    }

    private val biometricHelper by lazy {
        BiometricHelper(this, this)
    }

    private val preferences by lazy {
        PreferencesHelper(requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupActivity()
        fieldsObservers()
        uiStateObserver()
    }

    override fun onResume() {
        super.onResume()
        setupFingerPrintButton()
    }
    private fun uiStateObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is LoginUIState.Error -> {
                    binding.loginProgressBar.isVisible = false
                    provider?.errorLogin()
                }

                is LoginUIState.Loading -> binding.loginProgressBar.isVisible = true

                is LoginUIState.Success -> {
                    binding.loginProgressBar.isVisible = false
                    provider?.successLogin()
                }
            }
        }
    }

    private fun fieldsObservers() {
        viewModel.loginFormState.observe(viewLifecycleOwner) { state ->
            binding.apply {
                loginEmailEdit.error = if (state.emailState is EditTextState.Invalid)
                    state.emailState.errorMessage else null

                loginPasswordEdit.error = if (state.passwordState is EditTextState.Invalid)
                    state.passwordState.errorMessage else null

                loginButtonEnterApp.isEnabled = state.isFormValid()
            }
        }
    }

    private fun setupActivity() {
        defineColorsScreen()
        changeEditTextFormLogin()
        navigateScreenListeners()
        providersListeners()
        setupViews()
    }


    private fun setupViews() = with(binding) {
        setupBackButton()
        setupCheckBox()
        setupBtnResetPassword()
        setupProviders()

        loginBtnRegister.apply {
            isVisible = fragmentUI.enabledRegister
            setTextColor(requireContext().getColor(defaultColors.lettersColors.link))
        }

        loginButtonEnterApp.apply {
            setOnClickListener {

//            viewModel.login(
//                userLogin = UserLogin(
//                    email = loginEmailEdit.text.toString(),
//                    password = loginPasswordEdit.text.toString(),
//                    isRemember = loginCkRemember.isChecked
//                )
//            )
            }
            background = requireContext().createCustomShapeDrawable(defaultColors)
            setTextColor(requireContext().getColor(defaultColors.secondaryColor))
        }

        loginEmailEdit.apply {
           background =  requireContext().createCustomShapeDrawable(defaultColors)
            setStartDrawableWithTint(
                context =requireContext(),
                drawableRes = R.drawable.baseline_email_24,
                tintColorRes = defaultColors.secondaryColor
            )
            setHintTextColor(requireContext().getColor(defaultColors.lettersColors.hint))
        }

        loginPasswordEdit.apply {
            background =  requireContext().createCustomShapeDrawable(defaultColors)
            setStartDrawableWithTint(
                context =requireContext(),
                drawableRes = R.drawable.baseline_password_24,
                tintColorRes = defaultColors.secondaryColor,
            )
            setHintTextColor(requireContext().getColor(defaultColors.lettersColors.hint))
        }
    }

    private fun setupProviders() = with(binding) {
        providerLinearContainer.isVisible = fragmentUI.enableProviders
        if (!fragmentUI.enableProviders) loginTextOr.text =
            requireContext().getText(R.string.create_your_account)
        else loginTextOr.text = requireContext().getText(R.string.or_connect_account)
    }

    private fun setupBtnResetPassword() = with(binding) {
        loginBtnResetPassword.apply {
            setTextColor(
                ContextCompat.getColorStateList(
                    requireContext(),
                    defaultColors.lettersColors.link
                )
            )
        }
    }

    private fun setupCheckBox() = with(binding){
        loginCkRemember.apply {
            isVisible = fragmentUI.enableButtonRememberPassword
            setOnCheckedChangeListener { _, isChecked ->
    //                viewModel.onRememberPasswordChanged(isRemember = isChecked )
            }
            changeCheckBoxColor(defaultColors)
        }
    }

    private fun setupBackButton() = with(binding) {
        loginButtonBack.apply {
            isVisible = fragmentUI.enableButtonBack
            changeColorImageButton(
                isBorder = true,
                iconDrawable = R.drawable.arrow_back_24,
                defaultColors = defaultColors
            )
            setOnClickListener {
                provider?.backButtonLogin()
            }
        }
    }

    private fun setupFingerPrintButton() = with(binding) {
        val preferencesResult = preferences.getLoginFirstAccess()
        if (preferencesResult) {
            loginBtnFingerprint.visibility = View.GONE
            preferences.saveFirstAccessConclude()
        } else {
            loginBtnFingerprint.isVisible = fragmentUI.enabledFingerPrint
        }
        loginBtnFingerprint.setOnClickListener {
            biometricHelper.showBiometric()
        }
        loginBtnFingerprint.changeColorImageButton(
            isBorder = true,
            iconDrawable = R.drawable.fingerprint_24,
            defaultColors = defaultColors
        )
    }

    private fun navigateScreenListeners() = with(binding) {
        loginBtnResetPassword.setOnClickListener {
            navigateTo(
                LoginFragmentDirections.actionLoginFragmentToResetPasswordFragment2()
            )
        }
        loginBtnRegister.setOnClickListener {
            navigateTo(
                LoginFragmentDirections.actionLoginFragmentToRegisterFragment()
            )
        }
        loginButtonBack.setOnClickListener {
            provider?.backButtonLogin()
        }
    }

    private fun defineColorsScreen() = with(binding) {
        loginRootContainerConstraint.setBackgroundColor(
            requireContext().getColor(defaultColors.primaryColor)
        )
        loginTextTitle.setTextColor(
            requireContext().getColor(defaultColors.secondaryColor)
        )

    }

    private fun changeEditTextFormLogin() = with(binding) {
        loginEmailEdit.doOnTextChanged { text, _, _, _ ->
            viewModel.onEmailChanged(
                email = text.toString()
            )
        }
        loginPasswordEdit.doOnTextChanged { text, _, _, _ ->
            viewModel.onPasswordChanged(
                password = text.toString()
            )
        }
    }

    private fun providersListeners() = with(binding) {
        googleProvider.setOnClickListener {

        }
        appleProvider.setOnClickListener { }
        facebookProvider.setOnClickListener { }
        phoneProvider.setOnClickListener {
            showBottomSheetPhone()
        }
        guestsProvider.setOnClickListener {
            provider?.clickGuestUser()
        }
    }

    private fun showBottomSheetPhone() {
        PhoneLoginBottomSheet(
            onClickSendPhone = { phone ->
                viewModel.verifyPhoneNumber(phone, requireActivity())
            }
        ).show(childFragmentManager, "phone_login_bottom_sheet")
    }


    override fun onDestroyView() {
        super.onDestroyView()
    }

    override fun onBiometricSuccess() {
        toastMessage("onBiometricSuccess")
    }

    override fun onBiometricError() {
        toastMessage("onBiometricError")
    }

    override fun onBiometricFaled() {
        toastMessage("onBiometricFaled")
    }
}