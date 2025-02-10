package solutions.CHK

object CheckoutSolution {
    fun checkout(skus: String): Int {
        skus.groupingBy { it }.eachCount().let {
            return it.removeFreeItems().calculateCartTotal()
        }
    }

    private fun Map<Char, Int>.calculateCartTotal(): Int {
        return this.entries.sumOf { (item, quantity) ->
            when (item) {
                'A' -> quantity / 5 * 200 + (quantity % 5) / 3 * 130 + (quantity % 5) % 3 * 50
                'B' -> quantity / 2 * 45 + quantity % 2 * 30
                'C' -> quantity * 20
                'D' -> quantity * 15
                'E' -> quantity * 40
                'F' -> quantity * 10
                else -> return -1
            }
        }
    }

    private fun Map<Char, Int>.removeFreeItems(): Map<Char, Int>{
        val consolidationItemsMap = this.toMutableMap()
        consolidationItemsMap['E']?.let {
            consolidationItemsMap['B'] = consolidationItemsMap['B']?.minus(consolidationItemsMap['E']!!.div(2)) ?: 0
        }
        consolidationItemsMap['F']?.let {
            consolidationItemsMap['F'] = consolidationItemsMap['F']?.minus(consolidationItemsMap['F']!!.div(3)) ?: 0
        }
        return consolidationItemsMap
    }
}