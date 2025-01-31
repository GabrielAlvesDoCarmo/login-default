package br.com.gds.login_default

import br.com.gds.login.feature.container.model.DefaultColors
import br.com.gds.login.feature.container.model.LayoutDefault
import br.com.gds.login.feature.login.model.LoginUI
import br.com.gds.login.feature.register.address.model.AddressRegisterUI
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.resetpassword.model.ResetPasswordUI
import br.com.gds.login.utils.commons.LayoutSetup

object MockUI {
    private val defaultColors = DefaultColors(
        primaryColor = R.color.teste_two,
        secondaryColor = R.color.teste_one,
        tertiaryColor = R.color.teste_tree
    )

    private val layoutDefault = LayoutDefault(
        defaultColors = defaultColors,
        fullScreen = false,
        isStatusBarEnabled = false,
        isNavigationBarEnabled = false
    )

    private val loginFragment = LoginUI(
        enableButtonBack = true,
        enableButtonRememberPassword = true,
        enableProviders = false,
        enabledRegister = true,
        enabledFingerPrint = true
    )

    private val registerFragment = RegisterPersonalUI(
        enableButtonAddress = true,
        enableRegisterProviders = false
        //        backgroundColor = 0,
        //        titleColor = 0
    )

    private val addressRegisterFragment = AddressRegisterUI(
        //        backgroundColor = 0,
        //        titleColor = 0
    )

    private val resetPasswordFragment = ResetPasswordUI(
        //        backgroundColor = 0,
        //        titleColor = 0
    )

    val layoutSetup = LayoutSetup(
        layoutDefault = layoutDefault,
        registerFragment = registerFragment,
        addressRegisterFragment = addressRegisterFragment,
        loginFragment = loginFragment,
        resetPasswordFragment = resetPasswordFragment
    )
}