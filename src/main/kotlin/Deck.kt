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
        var tempo: Node? = cards
        while(tempo!!.value!=27){tempo = tempo.next} // iterate through until joker A is reached, and set temp var to it

        var behindJoker: Node? = tempo!!.previous
        var aheadOfJoker: Node? = tempo!!.next       
        var aheadTwoOfJoker: Node? = aheadOfJoker!!.next // now: we have variables for nodes behind, at, ahead of, and two ahead of joker

        if(aheadOfJoker==cards){cards = tempo}                      // special case: if the joker is moving to front position, set front pos var "cards" = to jokers
        else if(aheadOfJoker==cards!!.next){cards = aheadOfJoker}   // special case: if the joker is moving to second position, set front pos var "cards" = to spot that is being swapped to

                                                // -- BEGIN SWAPPAGE --
        behindJoker!!.next = aheadOfJoker       // swap links behind & in front of joker to make them consecutive
        aheadOfJoker!!.previous = behindJoker

        tempo!!.next = aheadTwoOfJoker          // swap links at joker & two in front of joker to make them consecutive
        aheadTwoOfJoker!!.previous = tempo

        aheadOfJoker!!.next = tempo             // finalize links between joker and the node that used to be one in front, but is now one behind the joker
        tempo!!.previous = aheadOfJoker

        // i.e. if 0 is joker and configuration used to be {-1, 0, 1, 2}, it is now {-1, 1, 0, 2}
    }

    fun swapJokerB() {
        var tempo: Node? = cards
        while(tempo!!.value!=28){tempo = tempo.next}       // while temp var keeps iterating upwards until joker B is reached

        val behindJoker: Node? = tempo!!.previous
        val aheadOfJoker: Node? = tempo!!.next 
        val aheadTwoOfJoker: Node? = aheadOfJoker!!.next       // note: this variable is only used as a stepping stone to define three ahead of the joker
        val aheadThreeOfJoker: Node? = aheadTwoOfJoker!!.next  // now: we have variables for nodes behind, at, ahead of, two, and three ahead of the joker

        if(aheadTwoOfJoker==cards){cards = tempo}                      // special case: if the joker is moving to front position, set front pos var "cards" = to jokers
        else if(aheadOfJoker==cards!!.next){cards = aheadOfJoker}   // special case: if the joker is moving to second position, set front pos var "cards" = to spot that is being swapped to

                                                    // -- BEGIN SWAPPAGE --
        behindJoker!!.next = aheadOfJoker           // swap links behind & in front of joker to make them consecutive
        aheadOfJoker!!.previous = behindJoker

        tempo!!.next = aheadThreeOfJoker            // swap links at joker & three in front of joker to make them consecutive
        aheadThreeOfJoker!!.previous = tempo

        aheadTwoOfJoker!!.next = tempo              // finalize links between joker and the node that used to be two ahead, but is now one behind the joker
        tempo!!.previous = aheadTwoOfJoker

        // i.e. if 0 is joker and configuration used to be {-1, 0, 1, 2, 3}, it is now {-1, 1, 2, 0, 3}
    }

    fun tripleCut() {
        val jokers: MutableList<Node> = mutableListOf()  // we will store the jokers, and their arrangement, in a mutable list that will be max size 2
        var tempo: Node? = cards
        while(jokers.size<2){                            // iterate through the cards, and add the jokers to the mutList when they are reached
            if(tempo!!.value==27){jokers.add(tempo)}
            else if(tempo!!.value==28){jokers.add(tempo)}
            tempo = tempo!!.next
        } // we now have the first joker in jokers[0], and the second joker in jokers[1]

        
        // ** now, we're assigning the variables for each three part in the triple cut **
        val head1: Node? = cards
        val tail1: Node? = jokers[0].previous
        var head2: Node? = jokers[1].next
        val tail2: Node? = cards!!.previous
        // now: we have variables of the head and tail of each three section

        if(cards==jokers[0]){   // special case 1: if group 1 is empty/joker is the head, just set the beginning of group 2 as the head
            cards = head2
        }
        else if(head1!!.previous==jokers[1]){   // special case 2: if group 2 is empty/joker is the tail, do the same but with the first joker
            cards = jokers[0]
        }
        else{
            // first move the first group in between the space in between the jokers, and the second group
            jokers[1].next = head1
            head1!!.previous = jokers[1]

            tail1!!.next = head2
            head2!!.previous = tail1

            // then move the second group around to the other side, and since head2 is already linked backwards, just set it as the head node of the full LinkedList
            tail2!!.next = jokers[0]
            jokers[0].previous = tail2

            // finally, set the list to start at the old head of group two, which is now the head of group one
            cards = head2
        }
    }
}