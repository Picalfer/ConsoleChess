import utils.BoardConstants.EMPTY_CELL
import utils.FigureConstants.BLACK_BISHOP
import utils.FigureConstants.BLACK_KING
import utils.FigureConstants.BLACK_KNIGHT
import utils.FigureConstants.BLACK_PAWN
import utils.FigureConstants.BLACK_QUEEN
import utils.FigureConstants.BLACK_ROOK
import utils.FigureConstants.WHITE_BISHOP
import utils.FigureConstants.WHITE_KING
import utils.FigureConstants.WHITE_KNIGHT
import utils.FigureConstants.WHITE_PAWN
import utils.FigureConstants.WHITE_QUEEN
import utils.FigureConstants.WHITE_ROOK

const val numRow = 9
const val numCol = 9

class Board {
    private val board: Array<Array<Cell>> = Array(numRow) { Array(numCol) { Cell() } }
    private val lettersList = arrayOf("A", "B", "C", "D", "E", "F", "G", "H")
    private val figureList = arrayOf("K", "Q", "R", "B", "N", "P")

    init {
        fillBoardEmptiesCells()
        makeStartStateBoard()
    }

    private fun fillBoardEmptiesCells() {
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                cell.number = j
                cell.shownChar = EMPTY_CELL
                if (i != 0) board[i][j].letter = lettersList[i - 1] else {
                    board[i][j].letter = "*"
                }
                /*if ((j + i) % 2 == 0) {
                    cell.shownChar = "██"
                } else {
                    cell.shownChar = "░░"
                }*/
            }
        }
        for (i in 1..8) {
            board[0][i].shownChar = "\u202F$i"
        }
        for (i in lettersList) {
            board[lettersList.indexOf(i) + 1][0].shownChar = i
        }

        board[0][0].shownChar = " "
    }

    private fun makeStartStateBoard() {
        board[1][1].shownChar = BLACK_ROOK

        board[1][8].shownChar = BLACK_ROOK
        board[8][8].shownChar = WHITE_ROOK
        board[8][1].shownChar = WHITE_ROOK

        board[1][2].shownChar = BLACK_KNIGHT
        board[1][7].shownChar = BLACK_KNIGHT
        board[8][2].shownChar = WHITE_KNIGHT
        board[8][7].shownChar = WHITE_KNIGHT

        board[1][3].shownChar = BLACK_BISHOP
        board[1][6].shownChar = BLACK_BISHOP
        board[8][3].shownChar = WHITE_BISHOP
        board[8][6].shownChar = WHITE_BISHOP

        board[1][5].shownChar = BLACK_KING
        board[1][4].shownChar = BLACK_QUEEN

        board[8][5].shownChar = WHITE_KING
        board[8][4].shownChar = WHITE_QUEEN

        for (i in 1..8) {
            board[2][i].shownChar = BLACK_PAWN
            board[7][i].shownChar = WHITE_PAWN
        }
    }

    fun printBoard() {
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

        var cellFromText = ""
        var cellToText = ""
        for (i in input) {
            if (i == ' ') {
                cellFromText = input.substringBefore(i)
                input = input.substringAfter(i)
                break
            }
        }
        for (i in input) {
            if (i == ' ') {
                cellToText = input.substringAfter(i)
                break
            }
        }
        println("Your turn is $cellFromText to $cellToText\n")

        var letterFrom = ""
        var numberFrom = 0
        for (i in cellFromText) {
            if (i.isLetter()) letterFrom = i.toString().uppercase()
            if (i.isDigit()) numberFrom = i.toString().toInt()
        }

        var letterTo = ""
        var numberTo = 0
        for (i in cellToText) {
            if (i.isLetter()) letterTo = i.toString().uppercase()
            if (i.isDigit()) numberTo = i.toString().toInt()
        }

        //println("$letterFrom, $numberFrom, $letterTo, $numberTo")
        val cellFrom = Cell(letterFrom, numberFrom)
        val cellTo = Cell(letterTo, numberTo)

        var showChar = ""
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                if (cell.letter == cellFrom.letter && cell.number == cellFrom.number) {
                    showChar = board[i][j].shownChar
                    board[i][j].shownChar = EMPTY_CELL
                }
            }
        }

        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                if (cell.letter == cellTo.letter && cell.number == cellTo.number) {
                    board[i][j].shownChar = showChar
                }
            }
        }
    }
}