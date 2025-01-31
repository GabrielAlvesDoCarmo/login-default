package br.com.gds.login.feature.resetpassword.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import br.com.gds.login.LoginModuleSession
import br.com.gds.login.R
import br.com.gds.login.databinding.FragmentResetPasswordBinding
import br.com.gds.login.feature.resetpassword.model.ResetPasswordUI
import br.com.gds.login.feature.resetpassword.viewmodel.ResetPasswordViewModel
import br.com.gds.login.utils.commons.LayoutSetup
import br.com.gds.login.utils.extensions.changeColorImageButton
import br.com.gds.login.utils.extensions.createCustomShapeDrawable
import br.com.gds.login.utils.extensions.edittext.setupEdit
import br.com.gds.login.utils.extensions.toastMessage

class ResetPasswordFragment : Fragment() {
    private lateinit var binding: FragmentResetPasswordBinding
    private val viewModel: ResetPasswordViewModel by viewModels()
    private val fragmentUI by lazy {
        LoginModuleSession
            .loginModuleDependency
            ?.layoutSetup
            ?.resetPasswordFragment
            ?: ResetPasswordUI()
    }

    private val provider by lazy {
        LoginModuleSession
            .loginModuleDependency
            ?.loginModuleCallbackProvider
    }

    private val defaultColors by lazy {
        LoginModuleSession
            .loginModuleDependency
            ?.layoutSetup?.layoutDefault?.defaultColors ?: LayoutSetup()
            .layoutDefault
            .defaultColors
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ) = FragmentResetPasswordBinding.inflate(inflater).also {
        binding = it
    }.root

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupScreenAppearance()
    }

    private fun setupScreenAppearance() = with(binding) {
        resetPasswordContainerRoot.setBackgroundColor(requireContext().getColor(defaultColors.primaryColor))
        resetPasswordTextTitle.setTextColor(requireContext().getColor(defaultColors.secondaryColor))
        resetPasswordEmailEdit.setupEdit(requireContext(),defaultColors, R.drawable.baseline_email_24)
        resetPasswordButtonBack.apply {
            setOnClickListener {
                provider?.backButtonResetPassword()
            }
            changeColorImageButton(
                isBorder = true,
                iconDrawable = R.drawable.arrow_back_24,
                defaultColors = defaultColors
            )
        }
        resetPasswordButtonEnterApp.apply {
            setOnClickListener {
                toastMessage("Clicou")
            }
            background = requireContext().createCustomShapeDrawable(defaultColors)
            setTextColor(requireContext().getColor(defaultColors.secondaryColor))
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = ResetPasswordFragment()
    }

}