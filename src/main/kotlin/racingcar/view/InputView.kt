package racingcar.view

import camp.nextstep.edu.missionutils.Console
import racingcar.constants.Messages

class InputView {
    fun readCarNames(): List<String> {
        println(Messages.INPUT_CAR_NAME_PROMPT)
        return Console.readLine().split(',')
    }

    fun readMoveCount(): Int {
        println(Messages.INPUT_MOVE_COUNT_PROMPT)
        return Console.readLine().toInt()
    }
}