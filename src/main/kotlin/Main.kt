fun printPattern(ticTacList: List<List<Char>>) {
    println("---------")
    println("| ${ticTacList[0][0]} ${ticTacList[0][1]} ${ticTacList[0][2]} |")
    println("| ${ticTacList[1][0]} ${ticTacList[1][1]} ${ticTacList[1][2]} |")
    println("| ${ticTacList[2][0]} ${ticTacList[2][1]} ${ticTacList[2][2]} |")
    println("---------")
}

fun getTicTacList(str: String): MutableList<MutableList<Char>> {
    return mutableListOf(
        mutableListOf(str[0], str[1], str[2]),
        mutableListOf(str[3], str[4], str[5]),
        mutableListOf(str[6], str[7], str[8])
    )
}

fun isNumeric(str: String): Boolean {
    return str.all { char -> char.isDigit() }
}

fun checkGameStatus(ticTacList: List<List<Char>>, countSpace: Int): String {
    var x = 'X'
    var o = 'O'
    if(ticTacList[0][0] == x && ticTacList[0][1] == x && ticTacList[0][2] == x || // row
        ticTacList[1][0] == x && ticTacList[1][1] == x && ticTacList[1][2] == x || // row
        ticTacList[2][0] == x && ticTacList[2][1] == x && ticTacList[2][2] == x || // row
        ticTacList[0][0] == x && ticTacList[1][0] == x && ticTacList[2][0] == x || // column
        ticTacList[0][1] == x && ticTacList[1][1] == x && ticTacList[2][1] == x || // column
        ticTacList[0][2] == x && ticTacList[1][2] == x && ticTacList[2][2] == x || // column
        ticTacList[0][0] == x && ticTacList[1][1] == x && ticTacList[2][2] == x || // diagonal
        ticTacList[0][2] == x && ticTacList[1][1] == x && ticTacList[2][0] == x // diagonal
    ) x = 'W'
    if(ticTacList[0][0] == o && ticTacList[0][1] == o && ticTacList[0][2] == o || // row
        ticTacList[1][0] == o && ticTacList[1][1] == o && ticTacList[1][2] == o || // row
        ticTacList[2][0] == o && ticTacList[2][1] == o && ticTacList[2][2] == o || // row
        ticTacList[0][0] == o && ticTacList[1][0] == o && ticTacList[2][0] == o || // column
        ticTacList[0][1] == o && ticTacList[1][1] == o && ticTacList[2][1] == o || // column
        ticTacList[0][2] == o && ticTacList[1][2] == o && ticTacList[2][2] == o || // column
        ticTacList[0][0] == o && ticTacList[1][1] == o && ticTacList[2][2] == o || // diagonal
        ticTacList[0][2] == o && ticTacList[1][1] == o && ticTacList[2][0] == o // diagonal
    ) o = 'W'

    if(countSpace == 0 && x != 'W' && o != 'W') return "Draw"
    else if (x == 'W') return "X wins"
    else if(o == 'W') return "O wins"
    return ""
}
fun main() {
    val str = "         "
    val ticTacList = getTicTacList(str)
    printPattern(ticTacList)
    print("Enter 2 coordinates from 1 to 3: ")
    var turn = 1
    var countSpace = 9
    while (true) {
        val (y, x) = readln().split(' ')
//        println("y: $y, x: $x")
        if(!isNumeric(x) && !isNumeric(y)) {
            println("You should enter numbers!")
        } else if(x.toInt() > 3 || x.toInt() < 1  || y.toInt() > 3 || y.toInt() < 1) {
            println("Coordinates should be from 1 to 3!")
        } else if(ticTacList[y.toInt() - 1][x.toInt() - 1] == 'X' || ticTacList[y.toInt() - 1][x.toInt() - 1] == 'O') {
            println("This cell is occupied! Choose another one!")
        } else {
            ticTacList[y.toInt() - 1][x.toInt() - 1] = if(turn % 2 == 0) {
                countSpace--
                'O'
            } else {
                countSpace--
                'X'
            }
            printPattern(ticTacList)
            val gameStatus = checkGameStatus(ticTacList, countSpace)
            if(gameStatus == "Draw" || gameStatus == "X wins" || gameStatus == "O wins") {
                println(gameStatus)
                break
            }
//            println("x: $countX, o: $countO")
            turn++
        }

    }
}