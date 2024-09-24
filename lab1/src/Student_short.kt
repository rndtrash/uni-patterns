class Student_short : BaseStudent {
    companion object {
        private val initialsRegex = Regex("^[А-Яа-яЁёA-Za-z-]+ [А-ЯЁA-Z]\\.( [А-ЯЁA-Z]\\.)?$")

        private fun validateInitials(name: String) {
            if (!initialsRegex.matches(name)) throw Exception("Неправильные инициалы")
        }
    }

    var id: Int
        get() = _id
        private set(value) {
            _id = value
        }

    var github: String?
        get() = _github
        set(value) {
            validateGitHub(value)
            _github = value
        }

    private var _contact: String? = null
    var contact: String?
        get() = _contact
        set(value) {
            _contact = value
        }

    private lateinit var _initials: String
    var initials: String
        get() = _initials
        private set(value) {
            validateInitials(value)
            _initials = value
        }

    constructor(id: Int, string: String) {
        this.id = id

        val split = string.split(", ").toMutableList()
        this.initials = split.removeFirst()

        var current = split.removeFirstOrNull()
        while (current != null) {
            val withoutGit = current.removePrefix("Гит: ")
            if (current != withoutGit) github = withoutGit

            val withoutContact = current.removePrefix("Контакт: ")
            if (current != withoutContact) contact = withoutContact

            current = split.removeFirstOrNull()
        }
    }

    constructor(student: Student) : this(student.id, student.getInfo())
}
