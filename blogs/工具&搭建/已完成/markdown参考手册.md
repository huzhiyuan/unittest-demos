#Markdown参考手册
> 原文:  https://www.jianshu.com/p/8c1b2b39deb0
---
##最常用  
空格: 
\&emsp; 
---
##todo list
常用:
- [x] 表格
- [x] 图片
- [ ] 代码
- [ ] 超链接
   - [x] 基本 
   - [x] 多个
- [x] 锚点


---
##表格
  
| 学号 | 姓名 | 分数  
|---|---|---
|小明|男|75  
|小红|女|79  
|小陆|男|92  

---
##图片  
语法说明：![图片Alt](图片地址 "图片Title")   
![喝茶](https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1563085271296&di=867040b96cf734aca614f52b5e98bfda&imgtype=0&src=http%3A%2F%2Ff.pk.365imgs.cn%2Ff%2F7%2F76%2F34801-45ce.jpg)
---
##代码  
```
#include <stdio.h>
int main(void)
{
printf("Hello world\n");
}
```
---
##超链接
1. 超链接[百度](http://www.baidu.com)  
2. 我经常去的几个网站[GitHub][1]、[知乎][2]以及[简书][3]  
[简书][3]是一个不错的[写作社区][]。  

    [1]:https://github.com "GitHub"  
    [2]:https://www.zhihu.com "知乎"  
    [3]:http://www.jianshu.com "简书"  
    [写作社区]:http://www.jianshu.com  
    
3. <>包起来的自动转成超链接  
<http://example.com/>  
<address@example.com>
---
##锚点
在你准备跳转到的指定标题后插入锚点{#标记}，然后在文档的其它地方写上连接到锚点的链接。

#### 0. 目录{#index}
跳转到[目录](#index)

---
##列表
无序列表  
使用 *，+，- 表示无序列表  
- 无序列表项 一
- 无序列表项 二
- 无序列表项 三
---
##引用
多层嵌套
>>> 请问 Markdwon 怎么用？ - 小白

>>自己看教程！ - 愤青

>教程在哪？ - 小白

---
##字体
*斜体* 或_斜体_  
**粗体**  
***加粗斜体***  
~~删除线~~  
