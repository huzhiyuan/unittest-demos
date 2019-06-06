1, ArrayList,Collections.synchronized和CopyOnWriteArrayList的区别?
> ArrayList快,线程不安全  
> Collections.synchronizedList 在写操作的时候使用了同步锁,并发安全  
> CopyOnWriteArrayList使用了数组复制,插入性能不及synchronizedList,但是读取性能很好  

2, ArrayList为什么不是线程安全的?
> 因为他的实现是用数组实现的,有两个步骤,1.添加元素;2.给size加一; 如果多线程同时处理,就会导致数据不一致问题