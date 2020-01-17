import gdrive.ResponseRegexSubstringUtil
import resources.receipt1

fun main() {
    val receipt1 = receipt1
    println(ResponseRegexSubstringUtil().substringAfterAnyOfWordsFiscalReceiptOrReturnOrigin(receipt1))
}