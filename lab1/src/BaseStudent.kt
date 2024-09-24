abstract class BaseStudent {
    companion object {
        private val nameRegex = Regex("^[А-Яа-яЁёA-Za-z-]+$")
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val githubRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        @JvmStatic
        protected fun validateName(name: String) {
            if (!nameRegex.matches(name)) throw Exception("Неправильное имя")
        }

        @JvmStatic
        protected fun validatePatronymic(name: String?) {
            if (name != null && !nameRegex.matches(name)) throw Exception("Неправильное отчество")
        }

        @JvmStatic
        protected fun validatePhone(phone: String?) {
            if (phone != null && !phoneRegex.matches(phone)) throw Exception("Неправильный номер телефона")
        }

        @JvmStatic
        protected fun validateTelegram(telegram: String?) {
            if (telegram != null && !telegramRegex.matches(telegram)) throw Exception("Неправильный URL Telegram")
        }

        @JvmStatic
        protected fun validateEmail(email: String?) {
            if (email != null && !emailRegex.matches(email)) throw Exception("Неправильный адрес Email")
        }

        @JvmStatic
        protected fun validateGitHub(github: String?) {
            if (github != null && !githubRegex.matches(github)) throw Exception("Неправильный адрес GitHub")
        }
    }

    protected var _id: Int = 0
    protected var _github: String? = null
}