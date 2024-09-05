data class Student(
    val id: Int,
    val lastName: String,
    val firstName: String,
    val patronymic: String? = null,
    var phone: String? = null,
    var telegram: String? = null,
    var email: String? = null,
    var github: String? = null
) {
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

    companion object {
        private val nameRegex = Regex("^[А-Яа-яЁёA-Za-z-]+$")
        private val phoneRegex = Regex("^\\+?[0-9]{10,15}\$")
        private val telegramRegex = Regex("^@[A-Za-z0-9_]{5,32}\$")
        private val emailRegex = Regex("^[\\w.-]+@[\\w.-]+\\.[a-zA-Z]{2,6}\$")
        private val githubRegex = Regex("^(https?://)?(www\\.)?github\\.com/[A-Za-z0-9_-]+/?\$")

        fun createFromMap(params: Map<String, Any?>): Student? {
            // Обязательные параметры
            val id = params["id"] as? Int ?: return null
            val lastName = params["lastName"] as? String ?: return null
            val firstName = params["firstName"] as? String ?: return null

            // Не обязательные параметры
            val patronymic = params["patronymic"] as? String
            val phone = params["phone"] as? String
            val telegram = params["telegram"] as? String
            val email = params["email"] as? String
            val github = params["github"] as? String

            // Валидация
            return if (isNameValid(lastName) && isNameValid(firstName) && isNameValid(
                    patronymic, false
                ) && isPhoneValid(phone) && isTelegramValid(telegram) && isEmailValid(email) && isGitHubValid(github)
            ) {
                Student(id, lastName, firstName, patronymic, phone, telegram, email, github)
            } else {
                println("Ошибка при создании студента с ID $id: Некорректные данные.")
                null
            }
        }

        private fun isNameValid(name: String?, required: Boolean = true): Boolean {
            return nameRegex.matches(name ?: return !required)
        }

        private fun isPhoneValid(phone: String?): Boolean {
            return phoneRegex.matches(phone ?: return true)
        }

        private fun isTelegramValid(telegram: String?): Boolean {
            return telegramRegex.matches(telegram ?: return true)
        }

        private fun isEmailValid(email: String?): Boolean {
            return emailRegex.matches(email ?: return true)
        }

        private fun isGitHubValid(git: String?): Boolean {
            return githubRegex.matches(git ?: return true)
        }
    }
}