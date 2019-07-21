#autohotkey参考手册
   
   ---
   ####基础
   > ; 分号开头代表这是注释行,不会被执行  
     ;win = #  
     ;Ctrl = ^  
     ;Alt = !  
     ;Shift = +  
     ;运行程序: Run,\{路径}\xx.exe  
     ;发送确认键(Enter): Send,{ENTER}    
     ;下一步（确认键）:Send,{ALTDOWN}n{ALTUP}  [下一步的快捷键是alt+n 所以点击alt+n再放开]    
     ;打印特殊字符:   
       比如感叹号,感叹号本身以及代表了Alt按键, 那么要打印感叹号可以这样: {raw}!  
   
   ---
   
   ####文本快捷键
   > ::tt::  
     clipboard=这是要代替的内容  
     send ^v  
     return  
   ---
   
   ####打开指定目录 win+d
   > \#d::  
     run D:\workspace\3rd-part-workspace\3rd-party-services  
     return  
    
   ----
   ####自动登录
   > ::cuser::  
     send username  
     send {tab}  
     send password{raw}!  
     send {enter}   
     return 
   ---
   #### 窗口置顶 Ctrl+Win+Alt+t
   > \#!t::WinSet AlwaysOnTop,on,A  
   ^#!t::WinSet AlwaysOnTop,off,A  
   
   ---
   ####大小写替换 shift+win+a  
   > +#a::  
     old=%clipboard%  
     send ^c  
     mess=%clipboard%  
     StringLeft,out,mess,1  
     if out is upper  
      StringLower,result,mess  
     else  
      StringUpper,result,mess  
     send %result%  
     clipboard=%old%  
     return    
   ---
   
   ####日期快捷键
   > ::now/::  
   FormatTime, now_date, %A_Now%, MM/dd ;格式化当前时间  
   Send, %now_date% ;发送  
   Return  
   ::now-::  
   FormatTime, now_date, %A_Now%, yyyy-MM-dd ;格式化当前时间  
   Send, %now_date% ;发送  
   Return  
   ::now_::  
   FormatTime, now_date, %A_Now%, yyyy_MM_dd ;格式化当前时间  
   Send, %now_date% ;发送  
   Return  
   
   ---
   ##附: 个人通用快捷键
   ```text
   ;===============================================================参考=========================================================
   ;win = #
   ;Ctrl = ^
   ;Alt = !
   ;Shift = +
   ;运行程序: Run,路径\xx.exe
   ;发送确认键(Enter): Send,{ENTER}  
   ;下一步（确认键）:Send,{ALTDOWN}n{ALTUP}  [下一步的快捷键是alt+n 所以点击alt+n再放开]
   ;打印特殊字符: {raw}!
   ;===========================通用必备快捷键========================================
   ;---------------------- markdown 空格 ----------------------------
   ::kg;::
   clipboard=&emsp; 
   send ^v
   return
   
   ;-------------------------------- 注释 //hzy: -------------------------
   ::zs::
   send //hzy:
   return
   
   ;;-------------------------------- xml注释 zs1 -------------------------
   ::zs1::
   clipboard=<!--  start-->
   Send ^v {enter}
   clipboard=<!--  end-->
   Send ^v {enter}
   return
   
   ;---------------- new hashmap -----------
   ::nhash::
   send HashMap<String, String> replacedParams = new HashMap<>();
   send {ENTER}
   send replacedParams .put("","");
   return
   
  ;---------------- 新的test方法, 测试经常用 -----------
  ::psvt::
  clipboard=public static void test(){
  send {ENTER}
  return
   
   ;-------------窗口置顶 : win+alt+t / win+alt+ctrl+t ----------
   #!t::WinSet AlwaysOnTop,on,A
   ^#!t::WinSet AlwaysOnTop,off,A
   
   ;-------------------大小写替换: shift+win+a------------------
   +#a::
   old=%clipboard%
   send ^c
   mess=%clipboard%
   StringLeft,out,mess,1
   if out is upper
    StringLower,result,mess
   else
    StringUpper,result,mess
   send %result%
   clipboard=%old%
   return
   
   ;-------------------常用日期格式----------------------
   ::now/::
   FormatTime, now_date, %A_Now%, MM/dd ;格式化当前时间
   Send, %now_date% ;发送
   Return
   ::now-::
   FormatTime, now_date, %A_Now%, yyyy-MM-dd ;格式化当前时间
   Send, %now_date% ;发送
   Return
   ::now_::
   FormatTime, now_date, %A_Now%, yyyy_MM_dd ;格式化当前时间
   Send, %now_date% ;发送
   Return
   ;---------------------------------------------------------
   ```