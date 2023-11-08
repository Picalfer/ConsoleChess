const val numRow = 9
const val numCol = 9

class Board {
    private val board: Array<Array<Cell>> = Array(numRow) { Array(numCol) { Cell() } }
    private val lettersList = arrayOf("A", "B", "C", "D", "E", "F", "G", "J")

    init {
        fillBoardEmptiesCells()
        //makeStartStateBoard()
    }

    private fun fillBoardEmptiesCells() {
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                cell.number = j
                if (i != 0) cell.letter = lettersList[i - 1] else {
                    cell.letter = "*"
                }
                if ((j + i) % 2 == 0) {
                    cell.shownChar = "██"
                } else {
                    cell.shownChar = "░░"
                }
            }
        }
        for (i in 1..8) {
            board[0][i].shownChar = "|$i"
        }
        for (i in lettersList) {
            board[lettersList.indexOf(i) + 1][0].shownChar = i
        }

        board[0][0].shownChar = " "
    }

    /*private fun makeStartStateBoard(){
        board[0][0] = "♖"
        board[0][7] = "♖"
        board[7][7] = "♜"
        board[7][0] = "♜"

        board[0][1] = "♘"
        board[0][6] = "♘"
        board[7][1] = "♞"
        board[7][6] = "♞"

        board[0][2] = "♗"
        board[0][5] = "♗"
        board[7][2] = "♝"
        board[7][5] = "♝"

        board[0][4] = "♔"
        board[0][3] = "♕"

        board[7][4] = "♚"
        board[7][3] = "♛"

        board[1][0] = "♙"
        board[1][1] = "♙"
        board[1][2] = "♙"
        board[1][3] = "♙"
        board[1][4] = "♙"
        board[1][5] = "♙"
        board[1][6] = "♙"
        board[1][7] = "♙"

        board[6][0] = "♟"
        board[6][1] = "♟"
        board[6][2] = "♟"
        board[6][3] = "♟"
        board[6][4] = "♟"
        board[6][5] = "♟"
        board[6][6] = "♟"
        board[6][7] = "♟"
    }*/

    fun printBoard() {
        println()
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                print(" ")
                //print("${board[i][j].letter}${board[i][j].number}")
                print(cell.shownChar)
                if (cell.shownChar != "██" || cell.shownChar != "░░") {

                } else {
                    print(" ")
                }
            }

            if (i == 1) {
                print("       Do your turn, select cell you want to go like this: g2 -> e2")
            }
            println()
        }
    }

    fun doTurn() {
        println()
        print("Write your turn here: ")
        var input = readlnOrNull() ?: return

        var cellFrom = ""
        var cellTo = ""
        for (i in input) {
            if (i == ' ') {
                cellFrom = input.substringBefore(i)
                input = input.substringAfter(i)
                break
            }
        }
        for (i in input) {
            if (i == ' ') {
                cellTo = input.substringAfter(i)
                break
            }
        }
        println("Your turn is $cellFrom to $cellTo")

        var letterFrom = ""
        var numberFrom = 0
        for (i in cellFrom) {
            if (i.isLetter()) letterFrom = i.toString().uppercase()
            if (i.isDigit()) numberFrom = i.toString().toInt()
        }

        var letterTo = ""
        var numberTo = 0
        for (i in cellTo) {
            if (i.isLetter()) letterTo = i.toString().uppercase()
            if (i.isDigit()) numberTo = i.toString().toInt()
        }

        println("$letterFrom, $numberFrom, $letterTo, $numberTo")


        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                if (cell.letter == letterFrom && cell.number == numberFrom) {
                    cell.shownChar = "*"
                }
            }
        }
    }
}