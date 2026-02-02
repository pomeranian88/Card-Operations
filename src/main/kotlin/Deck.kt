class Deck(initialCardOrdering: String) {

    var cards: Node? = null
    var numCards = 0

    init {
        val cardNumberList = initialCardOrdering.split(' ')
        if (cardNumberList.isEmpty()) {
            throw Exception("Deck empty.")
        }

        numCards = cardNumberList.size
        lateinit var current: Node
        for (cardString in cardNumberList) {
            val cardValue = cardString.toInt()
            if (cards == null) {
                // First node, special case
                val firstNode = Node(cardValue, null, null)
                cards = firstNode
                current = firstNode
            } else {
                // Any other node
                val card = Node(cardValue, current, null)
                current.next = card
                current = card
            }
        }

        // Wrap last card around
        current.next = cards
        cards!!.previous = current
    }

    // cards is the first card in the string, we don't have a list of anything else
    // numCards is 28

    // STEP 2
    fun getString(n: Int): String {
        var tempo: Node? = cards
        var count: Int = 0

        var totalString: String = ""

        while(count<=n){
            totalString += tempo!!.value.toString() + " "
            tempo = tempo!!.next
            count++
        }
        return totalString
    }

    // STEP 3
    fun getStringBackwards(n: Int): String {
        var tempo: Node? = cards!!.previous
        var count: Int = 0

        var totalString: String = ""

        while(count<=n){
            totalString += tempo!!.value.toString() + " "
            tempo = tempo!!.previous
            count++
        }
        return totalString
    }

    // STEP 4
    fun countDown(): Int {
        var tempo: Node? = cards
        var count: Int = 0
        if(tempo!!.value==28){count = 28}
        else{count = cards!!.value}

        for(num in 1..count){
            tempo = tempo!!.next
        }
        return tempo!!.value
    }

    fun swapJokerA() {
    }

    fun swapJokerB() {
    }

    fun tripleCut() {
    }
}
