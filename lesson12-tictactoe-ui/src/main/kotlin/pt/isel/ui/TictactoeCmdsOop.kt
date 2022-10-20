package pt.isel.ui

import pt.isel.ttt.*

object CmdQuitOop : CommandOop<Board> {
    override fun action(subject: Board?, args: List<String>) = null
    override fun show(subject: Board) {}
    override val syntax: String get() = "quit"
}
object CmdStartOop : CommandOop<Board> {
    override fun action(subject: Board?, args: List<String>) = BoardRun()
    override fun show(subject: Board) = printBoard(subject)
    override val syntax: String get() = "start"
}


/**
 * Represents a command with syntax e.g. play X 2 1
 */
object CmdPlayOop : CommandOop<Board> {
    override fun show(subject: Board) = printBoard(subject)
    override val syntax: String get() = "play <X|O> <line> <col>"

    override fun action(subject: Board?, args: List<String>): Board? {
        require(subject != null) {"You should start a game to initialize a Board before start playing"}
        require(args.size == 3) {"Missing arguments! Required player, line and column."}
        val player = args[0][0].toPlayer() // May throw Error for invalid symbol diff from 0 or X
        val line = args[1].toIntOrNull() ?: throw IllegalArgumentException("Invalid Integer value for line!")
        val col = args[2].toIntOrNull() ?: throw IllegalArgumentException("Invalid Integer value for column!")
        val pos = Position(line, col) // May throw Error for illegal line or col
        return subject.play(pos, player)
    }
}

val sepLine = "\n"+"---+".repeat(BOARD_SIZE-1)+"---"

fun printBoard(board: Board) {
    Position.values.forEach { pos ->
        print(" ${board.get(pos)?.player?.symbol ?: " "} ")
        if (pos.col == BOARD_SIZE-1)
            if (pos.lin < BOARD_SIZE-1) println(sepLine)
            else println()
        else
            print("|")
    }
    if(board is BoardWin)
        board.winner?.apply{ println("Player ${board.winner} wins.") }
}
/*
 O |   |
---+---+---
 X | O |
---+---+---
 O |   | X
 */