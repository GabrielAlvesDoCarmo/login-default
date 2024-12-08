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
        statusBarColor = R.color.black,
        isNavigationBarEnabled = true,
        navigationBarColor = R.color.black
    )
    private val registerFragment = RegisterPersonalUI()
    private val addressRegisterFragment = AddressRegisterUI()
    private val autoRegisterFragment = AutomovelRegisterUI()
    private val loginFragment = LoginUI()
    private val resetPasswordFragment = ResetPasswordUI()

    val layoutSetup = LayoutSetup(
        layoutDefault = layoutDefault,
        registerFragment = registerFragment,
        addressRegisterFragment = addressRegisterFragment,
        autoRegisterFragment = autoRegisterFragment,
        loginFragment = loginFragment,
        resetPasswordFragment = resetPasswordFragment
    )
}