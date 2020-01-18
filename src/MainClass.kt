import gdrive.GDriveResponseParserHelper
import resources.receipt1

fun main() {
    GDriveResponseParserHelper().parseStringFromOcrToListOfOperations(resources.receipt3).forEach{
        println(it)
    }
}