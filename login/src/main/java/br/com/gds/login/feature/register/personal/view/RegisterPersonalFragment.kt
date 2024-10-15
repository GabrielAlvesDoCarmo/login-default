package br.com.gds.login.feature.register.personal.view

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import br.com.gds.login.databinding.FragmentRegisterPersonalBinding
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUser
import br.com.gds.login.feature.register.personal.viewmodel.RegisterPersonalViewModel
import br.com.gds.login.feature.register.personal.viewmodel.isFormValid
import br.com.gds.login.utils.extensions.appendMessageToFile
import br.com.gds.login.utils.extensions.edittext.EditTextState
import br.com.gds.login.utils.extensions.requestPermission
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegisterPersonalFragment : Fragment() {
    companion object {
        fun newInstance() = RegisterPersonalFragment()
    }

    private lateinit var binding: FragmentRegisterPersonalBinding
    private val viewModel: RegisterPersonalViewModel by viewModel()
    private val registerArgs: RegisterPersonalFragmentArgs by navArgs()
    private val isBtnAddressEnabled get() = registerArgs.registerUI?.enableButtonAddress
    private val isNicknameEnabled get() = registerArgs.registerUI?.enableNickname

    val requestPermission = requestPermission(
        permission = Manifest.permission.CAMERA,
        onGranted = {

        },
        onDenied = {

        }
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRegisterPersonalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews()
        filedObserve()
        appendMessageToFile(this.requireContext(), "TELA DE REGISTRO DISPONIVEL")
    }

    override fun onDestroyView() {
        super.onDestroyView()
        appendMessageToFile(this.requireContext(), "TELA DE REGISTRO ENCERRADA")
    }

    private fun setupViews() {
        binding.apply {
            registerNameEdit.doOnTextChanged { text, _, _, _ ->
                viewModel.onNameChanged(
                    name = text.toString()
                )
            }
            loginEmailEdit.doOnTextChanged { text, _, _, _ ->
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
            registerImage.setOnClickListener {
                requestPermission.launch(Manifest.permission.CAMERA)
            }
            buttonRegister.setOnClickListener {
                viewModel.register(
                    registerPersonalUser = getUserRegister()
                )
            }
            ckUseNickname.isVisible = isNicknameEnabled ?: false
            buttonAddress.isVisible = isBtnAddressEnabled ?: true

            ckUseNickname.setOnClickListener {
                registerNicknameLayout.isVisible = ckUseNickname.isChecked
            }

        }
    }

    private fun filedObserve() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.formState.collect { state ->
                binding.apply {
                    registerNameLayout.error = if (state.nameState is EditTextState.Invalid)
                        state.nameState.errorMessage else null

                    loginEmailLayout.error = if (state.emailState is EditTextState.Invalid)
                        state.emailState.errorMessage else null

                    registerPasswordLayout.error = if (state.passwordState is EditTextState.Invalid)
                        state.passwordState.errorMessage else null

                    registerConfirmPasswordLayout.error =
                        if (state.confirmPasswordState is EditTextState.Invalid)
                            state.confirmPasswordState.errorMessage else null

                    buttonRegister.isEnabled = state.isFormValid()
                    buttonAddress.isEnabled = state.isFormValid()
                }
            }
        }
    }

    private fun getUserRegister() = RegisterPersonalUser(
        immage = binding.registerImage.toString(),
        name = binding.registerNameEdit.text.toString(),
        email = binding.loginEmailEdit.text.toString(),
        password = binding.registerPasswordEdit.text.toString(),
        confirmPassword = binding.registerConfirmPasswordEdit.text.toString()
    )
}