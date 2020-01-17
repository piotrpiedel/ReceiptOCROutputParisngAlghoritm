package gdrive

import Operation


class ParsedOperationsHolder {

    var listOfParsedOperationsFromOCRString: MutableList<Operation> = mutableListOf()
        private set

    fun addResultToOperationList(operationList: List<Operation>) {
        if (!operationList.isNullOrEmpty()) {
            listOfParsedOperationsFromOCRString.addAll(operationList)
        } else return
    }
}