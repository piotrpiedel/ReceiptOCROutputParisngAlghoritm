import gdrive.GDriveResponseParserHelper
import resources.receipt1

fun main() {
    GDriveResponseParserHelper().parseStringFromOcrToListOfOperations(resources.receipt1).forEach{
        println(it)
    }
}