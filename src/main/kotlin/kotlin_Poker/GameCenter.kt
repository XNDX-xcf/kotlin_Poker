package kotlin_Poker

import com.sun.org.apache.bcel.internal.classfile.Constant
import java.lang.reflect.Array
import java.util.*

fun main() {
    //欢迎界面
    welcome()
    //设置游戏参数
    //游戏人数  游戏底注
    var sc=Scanner(System.`in`)
    print("请输入游戏人数（注意本游戏支持1-6人游戏）:")
    var count=sc.nextInt()
    //创建玩家管理器
    val player_manager=Player_Manager()
    //生成玩家信息
    player_manager.initPlayer(count)
    //展示玩家信息
    showplayers(player_manager)
    while (true) {
        print("请输入游戏底注(注意：为防止过度沉迷，请在0-20之间下注):")
        var money = sc.nextInt()
        //由游戏中心来管理整个游戏的过程
        //记录下注的总金额
        var toltallmoney: Int = count * money
        //减去每个玩家的下注金额
        for (i in 0..(player_manager.players.size - 1)) {
            player_manager.players[i].totallmoney -= money
        }
        //生成每个玩家的具体信息
        println("****************************")
        println("下注之后的信息:")
        showplayers(player_manager)
        //创建扑克管理器
        val poker_manager = Poker_Manager()
        //准备牌
        poker(poker_manager)
        println(player_manager.players.size)
        //发牌  展示每个玩家牌的信息并比较出大小
        var num = playpoker(player_manager, poker_manager)
        //将底注放入赢家的钱包里
        player_manager.players[num - 1].totallmoney += toltallmoney
        println("****************************")
        println("这局后的信息:")
        showplayers(player_manager)
        //是否进行下一局
        print("是否进行下一局(y/n):")
        var isfo=sc.next()
        if (isfo=="n"){
            break
        }
    }
    outlogin()
}

//把洗好的牌准备好
fun poker(poker_manager:Poker_Manager){
    //生成一副牌
    poker_manager.initPokers()
    //洗牌
    poker_manager.xpoker()
    /*for (poker in poker_manager.pokers){
        println("点数：${poker.dot},花色：${poker.type.pic}")
    }*/
}

//发牌 并比较大小  并返回赢家的编号
fun playpoker(player_manager: Player_Manager,poker_manager: Poker_Manager): Int {
    var playinformation:MutableList<Player> = mutableListOf()
    for (i in 0..(player_manager.players.size-1)){
        player_manager.players[i].poker=poker_manager.pokers[i]
        playinformation.add(i,player_manager.players[i])
    }
    for (i in 0..(player_manager.players.size-1)){
       println("玩家编号:${player_manager.players[i].id}  玩家扑克牌：${player_manager.players[i].poker.type.pic}${player_manager.players[i].poker.dot}")
   }

    playinformation.sortWith(
        compareBy({(poker_manager.DOTS.indexOf(it.poker.dot))},{it.poker.type.id})
    )
   /* for (i in 0..(player_manager.players.size-1)){
        println("玩家编号:${playinformation[i].id}  玩家扑克牌：${playinformation[i].poker.type.pic}${playinformation[i].poker.dot}")
    }*/
    //假如数值相同  根据花色来判断大小
   println("由上面的牌的信息很容易的出牌的大小 大小如下:")
    for (i in (player_manager.players.size-1) downTo 0){
        if (i==0){
            println("${playinformation[i].id}号玩家")
        }else{
            print("${playinformation[i].id}号玩家>")
        }
    }
    return playinformation[playinformation.size-1].id
}


//把娱乐人员的个人信息准备好
fun player(count:Int){
    //生成玩家信息
    val player_manager=Player_Manager()
    player_manager.initPlayer(count)
}

//显示玩家信息
fun showplayers(player_manager: Player_Manager){
    for (i in 0..(player_manager.players.size-1)){
        println("玩家编号:${player_manager.players[i].id}  玩家姓名：${player_manager.players[i].name}  玩家剩余金币:${player_manager.players[i].totallmoney}")
    }
}


//欢迎界面
fun welcome(){
    println("**************************")
    println("***欢迎来到比大小扑克游戏*****")
    println("**************************")
}

fun outlogin(){
    println("**************************")
    println("******欢迎下次光临**********")
    println("**************************")
}