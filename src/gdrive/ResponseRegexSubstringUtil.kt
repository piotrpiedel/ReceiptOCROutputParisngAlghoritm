package gdrive

import util.regexForAnyReceiptFicsalWord

class ResponseRegexSubstringUtil() {

    fun substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(string: String): String {
        val matchedResult = regexForAnyReceiptFicsalWord.find(string)
        if (matchedResult != null) {
            // fun: return substring after regex occurrence to the end of string
            return string.substring(matchedResult.range.last + 1, string.lastIndex).trim()
        }
        return string.trim()
    }
}