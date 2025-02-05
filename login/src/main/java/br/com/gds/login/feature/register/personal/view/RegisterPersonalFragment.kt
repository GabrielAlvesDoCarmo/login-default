package br.com.gds.login.feature.register.personal.view

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import br.com.gds.core.commons.notifications.NotificationHelperCore
import br.com.gds.core.commons.notifications.model.NotificationManagerModel
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.databinding.FragmentRegisterPersonalBinding
import br.com.gds.login.feature.container.model.DefaultColors
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.model.isFormValid
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalState
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalViewModel
import br.com.gds.login.utils.commons.LayoutSetup
import br.com.gds.login.utils.extensions.appendMessageToFile
import br.com.gds.login.utils.extensions.changeColorImageButton
import br.com.gds.login.utils.extensions.createCustomShapeDrawable
import br.com.gds.login.utils.extensions.edittext.EditTextMask
import br.com.gds.login.utils.extensions.edittext.EditTextState
import br.com.gds.login.utils.extensions.edittext.setMaskEdit
import br.com.gds.login.utils.extensions.edittext.setupEdit
import br.com.gds.login.utils.extensions.navigateTo
import br.com.gds.login.utils.extensions.toastMessage
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterPersonalFragment : Fragment() {

    private lateinit var binding: FragmentRegisterPersonalBinding
    private val viewModel: RegisterPersonalViewModel by viewModel()

    private val fragmentUI by lazy {
        LoginModuleSession.loginModuleDependency?.layoutSetup?.registerFragment
            ?: RegisterPersonalUI()
    }

    private val defaultColors: DefaultColors by lazy {
        LoginModuleSession.loginModuleDependency
            ?.layoutSetup
            ?.layoutDefault
            ?.defaultColors
            ?: LayoutSetup()
                .layoutDefault
                .defaultColors
    }

    private val provider by lazy {
        LoginModuleSession.loginModuleDependency?.loginModuleCallbackProvider
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreenAppearance()
        setupEditTextChanges()
        fieldObserver()
        uiStateObserver()
        appendMessageToFile(this.requireContext(), "TELA DE REGISTRO DISPONIVEL")
    }

    private fun setupScreenAppearance() = with(binding) {
        containerRegisterPersonRoot.setBackgroundColor(requireContext().getColor(defaultColors.primaryColor))
        registerTextTitle.setTextColor(requireContext().getColor(defaultColors.secondaryColor))
        registerButtonBack.apply {
            setOnClickListener {
                provider?.backButtonRegister()
            }
            changeColorImageButton(
                isBorder = true,
                iconDrawable = R.drawable.arrow_back_24,
                defaultColors = defaultColors
            )
        }
        registerButtonRegisterAddress.apply {
            isVisible = fragmentUI.enableButtonAddress
            setOnClickListener {

                autoPreencher()
//                navigateTo(RegisterPersonalFragmentDirections.actionRegisterToAddressRegister())
            }
            background = requireContext().createCustomShapeDrawable(defaultColors)
            setTextColor(requireContext().getColor(defaultColors.secondaryColor))
        }

        registerButtonRegisterApp.apply {
            background = requireContext().createCustomShapeDrawable(defaultColors)
            setTextColor(requireContext().getColor(defaultColors.secondaryColor))
            setOnClickListener {
               viewModel.register(
                   registerPersonalUser = getUserRegister()
               )
            }
        }

        registerNameEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_person_24)
        registerOtherNameEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_person_24)
        registerEmailEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_email_24)
        registerPhoneEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_phone_24)
        registerPasswordEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_password_24)
        registerConfirmPasswordEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_password_24)


        if (fragmentUI.enableRegisterProviders) {
            registerLinearContainerOr.isVisible = true
            providerLinearContainer.isVisible = true
        } else {
            registerLinearContainerOr.isVisible = false
            providerLinearContainer.isVisible = false
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        appendMessageToFile(this.requireContext(), "TELA DE REGISTRO ENCERRADA")
    }

    private fun setupEditTextChanges() {
        binding.apply {
            registerPhoneEdit.setMaskEdit(EditTextMask.PHONE)
            registerNameEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onNameChanged(
                    name = text.toString()
                )
            }

            registerOtherNameEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onNameChanged(
                    name = text.toString()
                )
            }

            registerEmailEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onEmailChanged(
                    email = text.toString()
                )
            }
            registerPasswordEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onPasswordChanged(
                    password = text.toString()
                )
            }
            registerConfirmPasswordEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onConfirmPasswordChanged(
                    password = binding.registerPasswordEdit.text.toString(),
                    confirmPassword = text.toString()
                )
            }
        }
    }


    private fun fieldObserver() {
        viewModel.registerPersonalFormState.observe(viewLifecycleOwner) { state ->
            binding.apply {
                registerNameEdit.error = if (state.nameState is EditTextState.Invalid)
                    state.nameState.errorMessage else null

//                registerOtherNameEdit.error = if (state.nameState is EditTextState.Invalid)
//                    state.nameState.errorMessage else null

                registerEmailEdit.error = if (state.emailState is EditTextState.Invalid)
                    state.emailState.errorMessage else null

//                registerPhoneEdit.error = if (state.phoneState is EditTextState.Invalid)
//                    state.phoneState.errorMessage else null

                registerPasswordEdit.error = if (state.passwordState is EditTextState.Invalid)
                    state.passwordState.errorMessage else null

                registerConfirmPasswordEdit.error =
                    if (state.confirmPasswordState is EditTextState.Invalid)
                        state.confirmPasswordState.errorMessage else null

//                registerButtonRegisterApp.isEnabled = state.isFormValid()
//                registerButtonRegisterAddress.isEnabled = state.isFormValid()
            }
        }
    }

    private fun uiStateObserver() {
        viewModel.uiState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is RegisterPersonalState.Error -> {
                    binding.progressBar.isVisible = false
                    toastMessage(state.message)
                }

                is RegisterPersonalState.Loading -> {
                    binding.progressBar.isVisible = false
                }

                is RegisterPersonalState.Success -> {
                    binding.progressBar.isVisible = false
                    LoginModuleSession
                        .loginModuleDependency
                        ?.loginModuleCallbackProvider
                        ?.successRegister()
                }
            }
        }
    }

    private fun getUserRegister() = RegisterPersonalUser(
        name = binding.registerNameEdit.text.toString(),
        email = binding.registerEmailEdit.text.toString(),
        password = binding.registerPasswordEdit.text.toString(),
        confirmPassword = binding.registerConfirmPasswordEdit.text.toString()
    )

    @SuppressLint("SetTextI18n")
    fun autoPreencher() {
        binding.apply {
            registerNameEdit.setText("GAbriel")
            registerOtherNameEdit.setText("Gabriel")
            registerEmailEdit.setText("william.paterson@my-own-personal-domain.com")
            registerPasswordEdit.setText("11aaAA@@")
            registerConfirmPasswordEdit.setText("11aaAA@@")
            registerPhoneEdit.setText("11111111111")


        }
    }
}