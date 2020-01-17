package gdrive

import util.toPairs


class PairUtil {

    fun getListOfTitleToValuePairs(listOfValueTitleTokens: List<String>): List<Pair<String, String>> {
        val titleToValuePairsList: List<Pair<String, String>>
        if (!listOfValueTitleTokens.isNullOrEmpty()) {
            titleToValuePairsList = listOfValueTitleTokens.toPairs()
        } else return emptyList()
        return titleToValuePairsList
    }
}