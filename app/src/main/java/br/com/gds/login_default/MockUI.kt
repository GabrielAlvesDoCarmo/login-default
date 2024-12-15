package br.com.gds.login_default

import br.com.gds.login.feature.container.model.LayoutDefault
import br.com.gds.login.feature.login.model.LoginUI
import br.com.gds.login.feature.register.address.model.AddressRegisterUI
import br.com.gds.login.feature.register.automovel.model.AutomovelRegisterUI
import br.com.gds.login.feature.register.personal.model.RegisterPersonalUI
import br.com.gds.login.feature.resetpassword.model.ResetPasswordUI
import br.com.gds.login.utils.commons.LayoutSetup

object MockUI {
    private val layoutDefault = LayoutDefault(
        isStatusBarEnabled = true,
        statusBarColor = R.color.teste_one,
        isNavigationBarEnabled = true,
        navigationBarColor = R.color.teste_two
    )

    private val registerFragment = RegisterPersonalUI(
        enableButtonAddress = false,
        enableNickname = false,
        //        backgroundColor = 0,
        //        titleColor = 0
    )

    private val addressRegisterFragment = AddressRegisterUI(
        //        backgroundColor = 0,
        //        titleColor = 0
    )

    private val autoRegisterFragment = AutomovelRegisterUI(
        //        backgroundColor = 0,
        //        titleColor = 0
    )

    private val loginFragment = LoginUI(
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
        autoRegisterFragment = autoRegisterFragment,
        loginFragment = loginFragment,
        resetPasswordFragment = resetPasswordFragment
    )
}