package baseball

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

fun main() {
    println("숫자 야구 게임을 시작합니다!")
    startGame()
}
