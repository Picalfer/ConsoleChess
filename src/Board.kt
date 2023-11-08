const val numRow = 8
const val numCol = 8

class Board {
    private val board: Array<Array<String>> = Array(numRow) { Array(numCol) { "" } }

    init {
        fillBoardEmptiesCells()
        makeStartStateBoard()
    }

    private fun fillBoardEmptiesCells() {
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                if ((j + i) % 2 == 0) {
                    board[i][j] = "██"
                } else {
                    board[i][j] = "░░"
                }
            }
        }
    }

    private fun makeStartStateBoard(){
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
    }

    fun printField() {
        println()
        for (i in 0..<numRow) {
            for (j in 0..<numCol) {
                print(" ")
                print(board[i][j])
                if (board[i][j] != "██" || board[i][j] != "░░") {

                } else {
                    print(" ")
                }
            }
            println()
        }
    }
}