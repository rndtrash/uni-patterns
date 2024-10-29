class DataListStudentShort(students: List<StudentShort>) : DataList<StudentShort>(students) {

    override fun getNames(): List<String> {
        return listOf("№", "name", "git", "contacts")
    }

    fun printNames(list: List<String>) {
        println(list.joinToString(" "))
    }

    override fun fetchData(): Array<Array<String?>> {
        val data = mutableListOf<Array<String?>>()
        for ((index, student) in (super.getSelected().map { it to elements[it] })) {
            val row = arrayOf(
                (index + 1).toString(),
                student.initials,
                student.github,
                student.contact,
            )
            data.add(row)
        }
        return data.toTypedArray()
    }

    override fun formatData(data: Array<Array<String?>>): Array<Array<String>> {
        val formatedData = mutableListOf<Array<String>>()
        for (row in data) {
            val newRow = arrayOf(
                row[0]!!,
                row[1]!!,
                row.getOrNull(2) ?: "",
                row.getOrNull(3) ?: "",
            )

            formatedData.add(newRow)
        }

        return formatedData.toTypedArray()
    }
}