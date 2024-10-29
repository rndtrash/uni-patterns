fun checkEquality(student1: Student, student2: Student, student3: Student) {
    val studentsOut = listOf(student2, student1, student3)
    Student.toTextFile("students1.txt", studentsOut)
    val studentsIn = Student.fromTextFile("students1.txt")

    if (studentsOut.size != studentsIn.size) println("Функции toTextFile и fromTextFile не совместимы")

    var i = 0
    while (i < studentsOut.size) {
        if (studentsOut[i] != studentsIn.elementAt(i)) {
            println("Функции toTextFile и fromTextFile не совместимы")
            return
        }

        i++
    }

    println("Функции toTextFile и fromTextFile совместимы")
}

fun main() {
    // Создаем объект из Map
    val student1 = Student(
        mapOf(
            "id" to 1,
            "lastName" to "Кузьменко",
            "firstName" to "Иван",
            "patronymic" to "Сергеевич",
            "phone" to "+78005553535",
            "telegram" to "@rndtrash",
            "email" to "rodent@duck.com",
            "github" to "https://github.com/rndtrash"
        )
    )
    println(student1)

    val student1_fromString = Student.fromString(student1.toString())
    println(student1_fromString)

    val student2 = Student(
        mapOf(
            "id" to 2,
            "lastName" to "Петров",
            "firstName" to "Иван",
            "patronymic" to "Владимирович",
            "telegram" to "@pivnoi"
        )
    )
    println(student2)
    println(student2.getInfo())

    val student3 = Student(
        mapOf(
            "id" to 3, "lastName" to "Сидоров", "firstName" to "Пётр", "patronymic" to "Олегович"
        )
    )
    println(student3)

    student1.displayInfo()
    student2.displayInfo()
    student3.displayInfo()

    val students = Student.fromTextFile("students.txt")
    for (student in students) {
        println(student.getInfo())
    }

    checkEquality(student1, student2, student3)

    val shortStudentList = listOf(student1, student2, student3).map { student -> StudentShort(student) }
    val dataList = DataListStudentShort(shortStudentList)
    println(dataList.toString())
    dataList.select(0)
    dataList.select(1)
    val dataTable: DataTable<String> = dataList.getData()
    val dataListNames = dataList.getNames()
    dataList.printNames(dataListNames)
    dataTable.displayTable()
}