package kotlin_Poker

class Player_Manager {
   //管理所有玩家
    var players:MutableList<Player> = mutableListOf()
    //由系统给玩家给于名字
    val names:List<String> = listOf("刘德华","张家辉","周润发","黎明","李宗盛","周杰伦")
    //生成玩家
    fun initPlayer(count:Int){
        for (i in 1..count){
            var player=Player(i,names[i-1],1000)
            players.add(player)
        }
    }
}