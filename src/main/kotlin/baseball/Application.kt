package baseball

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms

fun generateRandomNumber(): List<Int> {
    val numbers = mutableListOf<Int>()
    while (numbers.size < 3) {
        val randomNumber = Randoms.pickNumberInRange(1, 9)
        if (randomNumber !in numbers) {
            numbers.add(randomNumber)
        }
    }
    return numbers
}


fun calculateStrikesAndBalls(secret: List<Int>, guess: List<Int>): Pair<Int, Int> {
    var strikes = 0
    var balls = 0
    for (i in guess.indices) {
        if (guess[i] == secret[i]) {
            strikes++
        } else if (guess[i] in secret) {
            balls++
        }
    }
    return Pair(strikes, balls)
}


fun getUserInput(prompt: String): String {
    println(prompt)
    return Console.readLine().trim()
}


fun isValidInput(input: String): Boolean {
    // Check if input length is exactly 3
    if (input.length != 3) return false
    // Check if all characters are digits between '1' and '9'
    if (!input.all { it in '1'..'9' }) return false
    // Check if all characters are unique
    return input.toSet().size == 3
}


fun main() {
    println("숫자 야구 게임을 시작합니다!")
    startGame()
}
