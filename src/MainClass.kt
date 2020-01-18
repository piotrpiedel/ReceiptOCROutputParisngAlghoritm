import gdrive.GDriveResponseParserHelper
import resources.receipt1

fun main() {
    GDriveResponseParserHelper().parseStringFromOcrToListOfOperations(resources.receipt11).forEach{
        println(it)
    }
}