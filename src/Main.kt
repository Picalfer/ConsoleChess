fun main() {
    val board = Board()
    board.printBoard()
    while (true) {
        board.doTurn()
        board.printBoard()
    }
}





