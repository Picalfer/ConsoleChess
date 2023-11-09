import utils.BoardConstants.BLACK
import utils.BoardConstants.EMPTY_CELL
import utils.BoardConstants.WHITE
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
    private var userInput = ""
    private val figureList = arrayOf("K", "Q", "R", "B", "N", "P")
    private var turn = 1
    private var turnColor = WHITE

    init {
        fillBoardEmptiesCells()
        makeStartStateBoard()
    }

    private fun fillBoardEmptiesCells() {
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                val cell = board[i][j]
                cell.number = i
                cell.shownChar = EMPTY_CELL
                if (j != 0) board[i][j].letter = lettersList[j - 1] else {
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
            board[i][0].shownChar = i.toString()
        }
        for (i in lettersList) {
            board[0][lettersList.indexOf(i) + 1].shownChar = "\u202F$i"
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
        turnColor = if (turn % 2 == 0) BLACK else WHITE
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
            if (i == 2) {
                print("                         Now turn $turnColor")
                turn++
            }
            println()
        }
    }

    fun doTurn() {
        println()
        print("Write your turn here: ")
        userInput = readlnOrNull() ?: return

        val cellFromText = getFirstCellText()
        val cellToText = getLastCellText()

        val letterFrom = getLetter(cellFromText)
        val numberFrom = getNumber(cellFromText)

        val letterTo = getLetter(cellToText)
        val numberTo = getNumber(cellToText)

        val cellFrom = Cell(letterFrom, numberFrom)
        val cellTo = Cell(letterTo, numberTo)

        println("Your turn is ${cellFrom.letter}${cellFrom.number} to ${cellTo.letter}${cellTo.number}\n")

        val showChar = makeCharFromCell(cellFrom)

        putCharToCell(cellTo, showChar)
    }

    private fun putCharToCell(cell: Cell, showChar: String) {
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                if (board[i][j].letter == cell.letter && board[i][j].number == cell.number) {
                    board[i][j].shownChar = showChar
                }
            }
        }
    }

    private fun getLastCellText(): String {
        for (i in userInput.reversed()) {
            if (i == ' ') {
                return userInput.reversed().substringBefore(i)
            }
        }
        return ""
    }

    private fun getFirstCellText(): String {
        for (i in userInput) {
            if (i == ' ') {
                return userInput.substringBefore(i)
            }
        }
        return ""
    }

    private fun getLetter(cellText: String): String {
        for (i in cellText) {
            if (i.isLetter()) return i.toString().uppercase()
        }
        return ""
    }

    private fun getNumber(cellText: String): Int {
        for (i in cellText) {
            if (i.isDigit()) return i.toString().toInt()
        }
        return 0
    }

    private fun makeCharFromCell(cell: Cell): String {
        var showChar = ""
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                if (board[i][j].letter == cell.letter && board[i][j].number == cell.number) {
                    showChar = board[i][j].shownChar
                    board[i][j].shownChar = EMPTY_CELL
                }
            }
        }
        return showChar
    }
}