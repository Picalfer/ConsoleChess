const val numRow = 8
const val numCol = 8
private lateinit var field: Array<Array<String>>

fun main() {
    field = Array(numRow) { Array(numCol) { "" } }
    fillFieldEmptiesCells(field)
    printField(field)
}

fun fillFieldEmptiesCells(field: Array<Array<String>>) : Array<Array<String>> {
    for (i in 0..<numRow) {
        for (j in 0..<numCol) {
            if ((j + i) % 2 == 0) {
                field[i][j] = "██"
            } else {
                field[i][j] = "░░"
            }
        }
    }
    return field
}

fun printField(field: Array<Array<String>>) {
    for (i in 0..<numRow) {
        for (j in 0..<numCol) {
            print(field[i][j])
            print(" ")
        }
        println()
    }
}