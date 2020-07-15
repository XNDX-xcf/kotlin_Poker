package kotlin_Poker

import com.sun.deploy.security.ValidationState
import java.util.*

class Poker_Manager {
    //保存点数
    val DOTS:List<String> = listOf("2","3","4","5","6","7","8","9","10","J","Q","K","A")
    //保存花色
    val TYPES:List<PokerType> = listOf(SPADE,CLUB,DIAMAND,HEART)

    //管理一副牌
    var pokers:MutableList<Poker> = mutableListOf()

    //生成一副牌
    fun initPokers(){
        //从点数里面依次取出每一个点数
        for (dot in DOTS){
            //给每一个点数配上花色
            for (type in TYPES){
                val poker=Poker(dot,type)
                pokers.add(poker)
            }
        }
    }
    //洗牌 打乱牌的顺序
    fun xpoker(){
        Collections.shuffle(pokers)
    }


}