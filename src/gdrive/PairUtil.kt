package gdrive

import util.toPair


class PairUtil {

    fun matchStringFromListToTitleValuePair(listOfStringToMatchWithPairs: List<String>): List<Pair<String, String>> {
        val pairTitleOperationValueOperation: List<Pair<String, String>>
        if (!listOfStringToMatchWithPairs.isNullOrEmpty()) {
            pairTitleOperationValueOperation = listOfStringToMatchWithPairs.toPair()
        } else return emptyList()
        return pairTitleOperationValueOperation
    }
}