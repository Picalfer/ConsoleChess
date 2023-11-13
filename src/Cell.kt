import utils.BoardConstants
import utils.FigureConstants

open class Cell(
    var letter: String? = null,
    var number: Int? = null,
    var color: String? = null,
    var shownChar: String? = null
)

class EmptyCell : Cell(shownChar = BoardConstants.EMPTY_CELL)

class Pawn(color: String?, letter: String? = null, number: Int? = null) :
    Cell(color = color, letter = letter, number = number) {
    init {
        if (color == BoardConstants.WHITE) {
            shownChar = FigureConstants.WHITE_PAWN
        } else if (color == BoardConstants.BLACK) {
            shownChar = FigureConstants.BLACK_PAWN
        }
    }
}