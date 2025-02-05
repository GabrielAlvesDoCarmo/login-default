package br.com.gds.core.login_module.commons.network.constants

object FirebaseMsgError {
    object Auth {
        const val WEAK_PASSWORD = "A senha fornecida é muito fraca."
        const val INVALID_EMAIL = "O email fornecido é inválido."
        const val INVALID_USER = "Usuário inválido."
        const val USER_COLLISION = "Já existe uma conta com este email."
        const val RECENT_LOGIN_REQUIRED = "É necessário fazer login novamente para continuar."
        const val ERROR_UNKNOWN = "Erro de login desconhecido."
    }

    object Firestore {
        const val FIRESTORE_CANCELLED = "Operação cancelada."
        const val FIRESTORE_UNKNOWN = "Erro desconhecido."
        const val FIRESTORE_INVALID_ARGUMENT = "Argumento inválido."
        const val FIRESTORE_DEADLINE_EXCEEDED = "Tempo limite excedido."
        const val FIRESTORE_NOT_FOUND = "Documento não encontrado."
        const val FIRESTORE_ALREADY_EXISTS = "O documento já existe."
        const val FIRESTORE_PERMISSION_DENIED = "Permissão negada."
        const val FIRESTORE_RESOURCE_EXHAUSTED = "Limite de recursos excedido."
        const val FIRESTORE_FAILED_PRECONDITION = "Pré-condição falhou."
        const val FIRESTORE_ABORTED = "Operação abortada."
        const val FIRESTORE_OUT_OF_RANGE = "Valor fora do intervalo permitido."
        const val FIRESTORE_UNIMPLEMENTED = "Operação não implementada."
        const val FIRESTORE_INTERNAL = "Erro interno."
        const val FIRESTORE_UNAVAILABLE = "Serviço indisponível."
        const val FIRESTORE_DATA_LOSS = "Perda de dados."
        const val FIRESTORE_UNAUTHENTICATED = "Usuário não autenticado."
    }

    object Storage {
        const val RETRY_LIMIT_EXCEEDED = "Limite de tentativas excedido."
        const val QUOTA_EXCEEDED = "Cota de armazenamento excedida."
        const val PROJECT_NOT_FOUND = "Projeto não encontrado."
        const val OBJECT_NOT_FOUND = "Objeto não encontrado."
        const val BUCKET_NOT_FOUND = "Bucket não encontrado."
        const val CANCELLED = "Operação cancelada."
        const val INVALID_CHECKSUM = "Checksum inválido."
        const val NOT_AUTHENTICATED = "Usuário não autenticado."
        const val NOT_AUTHORIZED = "Não autorizado."
        const val UNKNOWN = "Erro desconhecido."
    }

    object HttpError {
        const val NOT_FOUND_404 = "Não encontrado"
        const val FORBIDDEN_403 = "Acesso negado"
        const val UNKNOWN = "Erro desconhecido"
        const val UNAUTHORIZED_401 = "Não autorizado."
    }

    object Database {
        const val ERROR_DATABASE = "Erro database"
    }

    object Exception {
        const val EXCEPTION_UNKNOWN = "Erro desconhecido"
    }
}