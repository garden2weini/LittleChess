# LittleChess（ToBeDone）

## 定义
- 正棋子：正面朝上的棋子
- 翻棋子：反面朝上的棋子

## 游戏模型
- 每选择一个翻棋子，则将棋子翻正，玩家轮换；
    - 如果为选择翻棋子，则自动确定当前棋子颜色为本玩家颜色，对家自动为另一种颜色。
- 每选择一个正棋子
    - 如为对方玩家颜色，不进行任何操作；
    - 如为本玩家颜色，则显示（最多）四个方向可移动位置，及可以攻击的棋子。
    - 如选定待攻击棋子：吃子，则去除待攻击棋子，选择棋子替换其位置；兑子，则去除两棋子；
    - 如选定待移动位置，选择棋子替换为待移动位置；
- 棋子实力
    - （相邻棋子）由强到弱：将-士-象-马-车-跑
    - （相邻棋子）由强到弱：士象马车跑-卒，卒-将
    - （间隔棋子）炮-any/翻棋子
    - 注意：（炮除外）只能吃对方的正棋子；炮可吃翻棋子及对方正棋子
- 结束条件：棋子数先为0的一方判输，（简化版）否则为平局

## 服务接口
注：此版本中玩家ID仅为0／1，对应红黑（随机选择）两方。
1. 选择棋子
    - 输入参数：操作玩家ID，盘棋位置
    - 输出参数：新操作玩家ID，选择结果（0翻牌／1选定本方正棋子／-1忽略对方正棋子）；0时返回棋子颜色和内容；1时返回待操作位置及操作类型（移动／吃／兑）；-1时无
    - 说明：0时 游戏双方轮换
1. 棋子操作（对选定棋子进行移动／兑／吃三种操作）
    - 输入参数：操作玩家ID，选定棋子位置、待操作棋子位置
    - 输出参数：新操作玩家ID，选定棋子的新位置（兑时可空）；输赢判定结果
    - 说明：操作完成时，游戏双方轮换（ko除外）；

## 实现优先级
1. 建立游戏模型
1. 实现后端服务接口（SpringBoot REST），可通过接口进行游戏（仅一对一）
1. 完善接口数据容错及提示
1. （如可能）整理测试用例，并持续完善单元测试
1. 实现前端界面（Vue）及事件，并对接服务接口
1. 前后端联调并完善逻辑
1. 进行游戏模型优化，并调整实现
1. 实现多用户同时进行多盘比赛，并进行微服务化改造
    
## 备注
~~~
mvn spring-boot:run -Drun.arguments="--server.port=8080"
mvn -Dtest=ChessMainControllerTests test

# curl -l http://localhost:8080/chess/select?player=1\&offset=3
# curl -l http://localhost:8080/chess/{player}/select/{offset}
curl -l http://localhost:8080/chess/1/select/3
curl -l http://localhost:8080/chess/step
~~~
