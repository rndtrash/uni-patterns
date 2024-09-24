data class Student_short(val id: Int, val info: String) {
    constructor(student: Student) : this(student.id, student.getInfo())
}
