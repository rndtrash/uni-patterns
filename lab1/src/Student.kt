class Student {
    companion object {
        private val nameRegex = Regex("^[А-Яа-яЁёA-Za-z-]+$")
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val githubRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        private fun validateName(name: String) {
            if (!nameRegex.matches(name)) throw Exception("Неправильное имя")
        }

        private fun validatePatronymic(name: String?) {
            if (name != null && !nameRegex.matches(name)) throw Exception("Неправильное отчество")
        }

        private fun validatePhone(phone: String?) {
            if (phone != null && !phoneRegex.matches(phone)) throw Exception("Неправильный номер телефона")
        }

        private fun validateTelegram(telegram: String?) {
            if (telegram != null && !telegramRegex.matches(telegram)) throw Exception("Неправильный URL Telegram")
        }

        private fun validateEmail(email: String?) {
            if (email != null && !emailRegex.matches(email)) throw Exception("Неправильный адрес Email")
        }

        private fun validateGitHub(github: String?) {
            if (github != null && !githubRegex.matches(github)) throw Exception("Неправильный адрес GitHub")
        }
    }

    private lateinit var _lastName: String
    private lateinit var _firstName: String
    private var _patronymic: String? = null
    private var _phone: String? = null
    private var _telegram: String? = null
    private var _email: String? = null
    private var _github: String? = null

    var id: Int
    var lastName: String
        get() = _lastName
        set(value) {
            validateName(value)
            _lastName = value
        }
    var firstName: String
        get() = _firstName
        set(value) {
            validateName(value)
            _firstName = value
        }
    var patronymic: String?
        get() = _patronymic
        set(value) {
            validatePatronymic(value)
            _patronymic = value
        }
    var phone: String?
        get() = _phone
        set(value) {
            validatePhone(value)
            _phone = value
        }
    var telegram: String?
        get() = _telegram
        set(value) {
            validateTelegram(value)
            _telegram = value
        }
    var email: String?
        get() = _email
        set(value) {
            validateEmail(value)
            _email = value
        }
    var github: String?
        get() = _github
        set(value) {
            validateGitHub(value)
            _github = value
        }

    constructor(
        id: Int,
        lastName: String,
        firstName: String,
        patronymic: String? = null,
        phone: String? = null,
        telegram: String? = null,
        email: String? = null,
        github: String? = null
    ) {
        this.id = id
        this.lastName = lastName
        this.firstName = firstName
        this.patronymic = patronymic
        this.phone = phone
        this.telegram = telegram
        this.email = email
        this.github = github
    }

    constructor(params: Map<String, Any?>) : this(
        params["id"] as Int,
        params["lastName"] as String,
        params["firstName"] as String,
        params["patronymic"] as? String,
        params["phone"] as? String,
        params["telegram"] as? String,
        params["email"] as? String,
        params["github"] as? String
    )

    fun displayInfo() {
        println(
            """
            ID: $id
            Фамилия: $lastName
            Имя: $firstName
            Отчество: ${patronymic ?: "нет"}
            Телефон: ${phone ?: "не указан"}
            Телеграм: ${telegram ?: "не указан"}
            Почта: ${email ?: "не указана"}
            GitHub: ${github ?: "не указан"}
        """.trimIndent()
        )
    }
}