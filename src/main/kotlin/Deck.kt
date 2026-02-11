/*
 * Author: Zev Thompson
 * 
 * Collab Statement: Worked on all the code & thinking of this by myself. Discussed makeup of Deck.kt briefly with Ian.
 * 
 * Reflection:  The only challenge here was understanding how the LinkedList was set up -- it took a little bit for me
 *              to realize that the LinkedList wasn't stored as any object, but was just a series of loose nodes that 
 *              contained internal info about the next item. Writing on paper helped! Assignment took ~1 hour.
 */

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
        var tempo: Node? = cards // start w first item in LinkedList as temp var
        var count: Int = 0

        var totalString: String = ""

        while(count<n){
            totalString += tempo!!.value.toString() + " " // append temp var to string
            tempo = tempo!!.next // set the temp var to be the item after the temp var in the LinkedList
            count++ // iterate through until you've reached the requested amount of nums
        }
        totalString = totalString.dropLast(1) // remove last item cause (count<n) iterates one too many times
        return totalString
    }

    // STEP 3
    fun getStringBackwards(n: Int): String {
        var tempo: Node? = cards!!.previous // start w last item in LinkedList by going one back from first node
        var count: Int = 0

        var totalString: String = ""

        while(count<n){ // all the same functionality as the previous function, just iterate backwards using .previous
            totalString += tempo!!.value.toString() + " "
            tempo = tempo!!.previous
            count++
        }
        totalString = totalString.dropLast(1)
        return totalString
    }

    // STEP 4
    fun countDown(): Int {
        var tempo: Node? = cards // start w first var in list
        var count: Int = 0
        if(tempo!!.value==28){count = 27} // special case when first card is joker: iterate 27 times
        else{count = cards!!.value} // else: iterate the amount of times associated with the first card number

        for(num in 1..count){ // for the exact amt of times associated with the head var...
            tempo = tempo!!.next  // iterate upwards in the list with the temp var
        }
        return tempo!!.value // will return variable in LinkedList that is {count} cards ahead of the first card
    }

    fun swapJokerA() {
    }

    fun swapJokerB() {
    }

    fun tripleCut() {
    }
}
