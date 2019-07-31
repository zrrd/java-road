* #### BasicUtilities  排序、比较、参数校验的方法类
> ComparisonChain 链式比较 比较优雅
> Ordering 排序器 封装了很多默认的排序方法
> Preconditions 参数校验

* #### cache           缓存(待完成)

* #### Collections     集合相关的工具类
> ###### 不可变类  
> ###### 新的集合类 
> * Multiset(无序可重复,并且能够统计重复数) 
> * ListMultimap SetMultimap 等等  用于解决Map中嵌套List的问题
> * BiMap key 与 value 都为唯一不可重复
> * Table 行 列 值 类型的数据
> * ClassToInstanceMap key 为类的Map
> * RangeSet 存储的不是固定值 而是范围  

> ##### 各个集合的工具类
> Iterables  Lists Sets Maps

* #### Strings         字符串相关工具类
> Joiner 字符串组合工具类 用某某符号隔开 array -> string
> Splitter 字符串分隔工具类  string -> array
> CaseFormat 大小写 驼峰 _ 的各种字符串转换
> Strings 字符串常用工具类
> CharMatcher 字符串处理类 

* #### current         并发
> 使用ListenableFuture来代替JDK的 Future  错误情况返回

* #### enumUtils       枚举工具类
> 枚举 与 枚举名的互相转化关系

* #### event           事件总线(待完成)

* #### hash            哈希(待完成)

* ##### io              流
> ByteStreams 字节流处理工具类 对应 InputStream OutputStream
> CharStreams 字符流处理工具类 对应 Reader Writer
> ByteSource 字节源,读取字节  ByteSink 字节汇,输出字节
> CharSource 字符源 CharSink 字符汇,输出字符
> Files 文件操作

* ##### math            运算
> IntMath LongMath BigIntegerMath 整型运算 
> DoubleMath 浮点型运算(舍入) 判断是否为整数

* ##### reflect         反射(待完成)