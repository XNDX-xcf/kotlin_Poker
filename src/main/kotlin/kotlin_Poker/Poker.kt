package kotlin_Poker

class Poker(var dot:String,var type:PokerType) {

}

class PokerType(var pic:String,var id:Int) {

}

val SPADE:PokerType= PokerType("♠",3)
val HEART:PokerType=PokerType("♥",4)
val CLUB:PokerType=PokerType("♣",1)
val DIAMAND:PokerType=PokerType("♦",2)