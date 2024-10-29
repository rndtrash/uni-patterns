class DataTable<T>(private val data: Array<Array<T>>) {

    fun getElement(row: Int, column: Int): T {
        if (row in data.indices && column in data[row].indices)
            return data[row][column]
        else
            throw IndexOutOfBoundsException("index not valid: $row, $column")
    }

    fun getRowCount(): Int = data.size

    fun getColumnCount(): Int {
        return if (data.isNotEmpty()) data[0].size else 0
    }

    fun displayTable() {
        for (row in data) {
            println(row.joinToString(" "))
        }
    }

    override fun toString(): String {
        return data.joinToString("\n") { row -> row.joinToString(" ") }
    }

}