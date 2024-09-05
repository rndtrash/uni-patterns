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

    val student2 = Student(
        mapOf(
            "id" to 2,
            "lastName" to "Петров",
            "firstName" to "Иван",
            "patronymic" to "Владимирович",
            "telegram" to "@pivnoi"
        )
    )

    val student3 = Student(
        mapOf(
            "id" to 3, "lastName" to "Сидоров", "firstName" to "Пётр", "patronymic" to "Олегович"
        )
    )

    student1.displayInfo()
    student2.displayInfo()
    student3.displayInfo()
}