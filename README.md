# quicKshare
目标功能：<br/>
  1.屏幕分享<br/>
  2.文件分享<br/>
  3.视频点播<br/>
  4.视频弹幕（这个应用场景hin蠢啊，但是想学习一下弹幕）<br/>
当前进度<br/>
  成功创建了Server和Client之间的Socket通信<br/>
  17.9.7 进展：在解析Intent文件传入的Uri时遇到了问题，如何通过Uri找到文件的绝对路径？查到的资料都是找媒体文件的，不适用于所有文件。以系统原生分享作为文件分享入口功能暂缓</br>
  17.9.8 进展：消息互通是解决好了，UI和Socket线程之间通信通过Handler传递，从Socket中获取的byte应该如何处理？是否应当参考mayubao大神的协议自己撸一个协议？得好好考虑清楚</br>
  进度评估 5%<br/>

参考：<br/>
1. 参考了这位大神的代码，文件分享功能应该还会参考 https://github.com/mayubao/Kuaichuan<br/>
2. 参考博客 http://www.jianshu.com/p/089fb79e308b<br/>

