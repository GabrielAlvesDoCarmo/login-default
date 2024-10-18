package br.com.gds.login.utils.commons

object LoginModuleConstants {
    const val EMPTY_STRING = ""
    const val EMPTY_SPACE = " "
    const val UNDERSCORE = "_"

    object Fragments {

    }

    object ViewModels {

    }

    object Repositories {
        object Register {
            const val KEY_REALTIME_IS_ONLINE = "isOnline"
            const val KEY_REALTIME_NAME = "name"
            const val KEY_REALTIME_EMAIL = "email"
            const val KEY_REALTIME_DATA_USER = "data_user"
        }
    }

    object UseCases {
        object Register {
            const val REGEX_NAME = "^[a-zA-Z]+$"
            const val REGEX_EMAIL = "^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$"
            const val REGEX_PASSWORD = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=!])(?=\\S+$).{8,20}$"
            const val ERROR_MESSAGE_NAME = "Nome inválido. Use apenas letras."
            const val ERROR_MESSAGE_EMAIL = "Email inválido."
            const val ERROR_MESSAGE_PASSWORD = "Senha inválida. A senha deve ter pelo menos 8 caracteres, uma letra maiúscula, uma letra minúscula, um número e um caractere especial."
            const val ERROR_MESSAGE_CONFIRM_PASSWORD = "As senhas não coincidem."

        }
    }

    object Telemetry {

    }
}