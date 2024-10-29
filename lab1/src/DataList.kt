abstract class DataList<T : Comparable<T>>(val elements: List<T>) {
    private val sortedElements: List<T> = elements.sorted()
    private val selectedElements = mutableSetOf<Int>()


    fun select(number: Int) {
        if (number in sortedElements.indices)
            selectedElements.add(number)
        else
            throw IndexOutOfBoundsException("Number $number is not valid")
    }

    fun getSelected(): List<Int> = selectedElements.toList()

    open fun getNames(): List<String> {
        throw UnsupportedOperationException("Should be implemented in nested class")
    }

    open fun getData(): DataTable<String> {
        val data = fetchData()
        val newData = formatData(data)
        return DataTable(newData)
    }

    abstract fun fetchData(): Array<Array<String?>>

    abstract fun formatData(data: Array<Array<String?>>): Array<Array<String>>

    override fun toString(): String {
        return getNames().joinToString(" ") + "\n" + getData().toString()
    }
}