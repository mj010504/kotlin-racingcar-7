package racingcar

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms.pickNumberInRange

enum class ErrorMessages(val errorMessage: String) {
    INVALID_CAR_NAME("올바르지 않은 자동차 이름입니다. 자동차 이름은 반드시 알파벳 문자로 시작해야 하며, 뒤에 선택적으로 숫자를 포함할 수 있습니다"),
    INVALID_CAR_NAME_LENGTH("자동차 이름이 허용 길이를 초과했습니다. 자동차 이름은 5자 이하만 가능합니다."),
    INVALID_MOVE_COUNT("올바르지 않은 시도 횟수입니다.")
}

fun main() {

    println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
    val str = Console.readLine()
    val carNameList = str.split(',')
    for(carName in carNameList) {
        isValidCarName(carName)
    }

    println("시도할 횟수는 몇 회인가요?")
    val input = Console.readLine()
    isValidMoveCount(input)
    val moveCount = input.toInt()

    val carDistanceList = MutableList(carNameList.size) {0}
    for(i in 0 until moveCount) {
        for(carIdx in 0 until carDistanceList.size) {
            moveCar(carIdx, carDistanceList)
            println("${carNameList[carIdx]} : ${("-".repeat(carDistanceList[carIdx]))}")
        }
        println()
    }

    val maxMove = carDistanceList.max()
    val raceWinners  : MutableList<String> = mutableListOf()
    for(carIdx in 0 until carDistanceList.size) {
        if(carDistanceList[carIdx] == maxMove) {
            raceWinners.add(carNameList[carIdx])
        }
    }

    println("최종 우승자 : ${raceWinners.joinToString(", ")}")

}

fun moveCar(carIdx : Int, carDistanceList : MutableList<Int>) {
    carDistanceList[carIdx] += if(pickNumberInRange(0, 9) >= 4) 1 else 0
}

fun isValidCarName(carName : String) {
    if(carName.length > 5) throwInvalidStringException(ErrorMessages.INVALID_CAR_NAME_LENGTH.errorMessage)
    // 자동차 이름은 문자열 또는 문자열 + 숫자의 형태만 가능하다.
    val regex = Regex("^[A-Za-z]+[0-9]*$")
    if(!regex.matches(carName)) throwInvalidStringException(ErrorMessages.INVALID_CAR_NAME.errorMessage)
}

fun isValidMoveCount(input : String) {
    val regex = Regex("^\\d+$")
    if(!regex.matches(input)) throwInvalidStringException(ErrorMessages.INVALID_MOVE_COUNT.errorMessage)
}

private fun throwInvalidStringException(message : String) {
    throw IllegalArgumentException(message)
}