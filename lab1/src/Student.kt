import java.io.File

class Student : BaseStudent {
    companion object {
        private val studentStringRegex = Regex("^Student\\((.+)\\)$")
        private val pairRegex = Regex("^([A-Za-z]+):[ \t]*(.+)\$")
        private val stringRegex = Regex("^\"(.*)\"\$")

        private fun serializeString(s: String?): String {
            return if (s == null) "null" else "\"$s\""
        }

        private fun deserializeString(s: String): String? {
            if (s == "null") return null

            val sr = stringRegex.matchEntire(s) ?: throw Exception("Неправильный формат строки")
            return sr.groupValues[1]
        }

        private fun deserializePair(s: String): Pair<String, String?> {
            val pr = pairRegex.matchEntire(s) ?: throw Exception("Неправильный формат пары")
            return pr.groupValues[1] to deserializeString(pr.groupValues[2])
        }

        fun fromString(s: String): Student {
            val studentR = studentStringRegex.matchEntire(s) ?: throw Exception("Неправильный формат строки Student")
            val params = studentR.groupValues[1].split(";")
            val map = mutableMapOf<String, String?>()
            for (param in params) {
                val pair = deserializePair(param)
                map[pair.first] = pair.second
            }

            return Student(map)
        }

        fun fromTextFile(path: String): Collection<Student> {
            val file = File(path)
            if (!file.canRead()) throw Exception("Файл по пути $path не существует")

            val students = mutableListOf<Student>()
            for (line in file.readLines()) {
                students.add(fromString(line))
            }

            return students
        }

        fun toTextFile(path: String, students: Iterable<Student>) {
            val file = File(path)
            file.printWriter().use { pw ->
                for (student in students) {
                    pw.println(student.toString())
                }
            }
        }
    }

    private lateinit var _lastName: String
    private lateinit var _firstName: String
    private var _patronymic: String? = null
    private var _phone: String? = null
    private var _telegram: String? = null
    private var _email: String? = null

    var id: Int
        get() = _id
        set(value) {
            _id = value
        }
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
        params["id"] as? Int ?: (params["id"] as String).toInt(),
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

    fun getInfo(): String {
        var s = "$lastName ${firstName[0]}."
        if (patronymic != null) s += " ${patronymic!![0]}."

        if (github != null) s += ", Гит: ${github!!}"

        if (phone != null) s += ", Контакт: Телефон ${phone!!}"
        else if (telegram != null) s += ", Контакт: Телеграм ${telegram!!}"
        else if (email != null) s += ", Контакт: Электронная почта ${email!!}"

        return s
    }

    override fun toString(): String =
        "Student(id:\"$id\";lastName:${serializeString(lastName)};firstName:${serializeString(firstName)};patronymic:${
            serializeString(
                patronymic
            )
        };phone:${
            serializeString(
                phone
            )
        };telegram:${serializeString(telegram)};email:${serializeString(email)};github:${serializeString(github)})"

    override fun equals(other: Any?): Boolean {
        val otherStudent = other as? Student ?: return false

        return id == otherStudent.id && lastName == otherStudent.lastName && firstName == otherStudent.firstName && patronymic == otherStudent.patronymic && phone == otherStudent.phone && telegram == otherStudent.telegram && email == otherStudent.email && github == otherStudent.github
    }
}