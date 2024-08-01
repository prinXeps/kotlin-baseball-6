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


fun startGame() {
    var continuePlaying = true
    while (continuePlaying) {
        val secretNumber = generateRandomNumber()

        while (true) {
            val input = getUserInput("숫자 3개를 입력하세요 (1-9, 서로 다른 숫자):")

            if (!isValidInput(input)) {
                throw IllegalArgumentException("잘못된 입력입니다. 1에서 9까지의 서로 다른 3자리 숫자를 입력해 주세요.")
            }

            val guess = input.map { it.toString().toInt() }
            val (strikes, balls) = calculateStrikesAndBalls(secretNumber, guess)

            when {
                strikes == 3 -> {
                    println("3스트라이크! 3개의 숫자를 모두 맞히셨습니다! 게임 종료")
                    break
                }
                strikes > 0 || balls > 0 -> {
                    println("${balls}볼 ${strikes}스트라이크")
                }
                else -> println("낫싱")
            }
        }

        continuePlaying = handleRestart()
    }
}

fun handleRestart(): Boolean {
    val restartInput = getUserInput("게임을 새로 시작하려면 1, 종료하려면 2를 입력하세요.")
    return when (restartInput) {
        "1" -> true
        "2" -> {
            println("게임 종료")
            false
        }
        else -> {
            throw IllegalArgumentException("잘못된 입력입니다. 1 또는 2를 입력해 주세요.")
        }
    }
}


fun main() {
    println("숫자 야구 게임을 시작합니다!")
    startGame()
}
