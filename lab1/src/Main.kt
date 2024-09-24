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
}