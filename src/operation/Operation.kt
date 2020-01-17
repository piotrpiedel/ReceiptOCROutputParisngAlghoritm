import operation.OperationBase
import operation.OperationType
import java.util.*

data class Operation(
    override var value: Double = 123456.1,
    override var title: String? = "Empty Constructor",
    override var operationType: OperationType = OperationType.OUTCOME,
    override var date: Date? = Date(), override var other_category_id: Int? = 0
) : OperationBase