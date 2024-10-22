package racingcar

import camp.nextstep.edu.missionutils.Console
import camp.nextstep.edu.missionutils.Randoms.pickNumberInRange

fun main() {

    println("경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)")
    val str = Console.readLine()
    val carNameList = str.split(',')
    val carDistanceList = MutableList(carNameList.size) {0}

    println("시도할 횟수는 몇 회인가요?")
    val moveCount  = Console.readLine().toInt()


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