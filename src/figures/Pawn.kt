package figures

import utils.BoardConstants
import utils.FigureConstants

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