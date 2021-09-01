selector
shape
set
animation-list
style
vector



以下由Eclipse中的Animation项目移植过来的哈！！
andrid下res目录：
	layout ：存放布局文件
	drawable ：存放图片文件资源
	menu：存放菜单资源文件
	values ：设置的一些常量xml文件，Strings，colors等
	anim ：存放动画
	assets ：文件夹是存放不进行编译加工的原生文件，即该文件夹里面的文件不会像 xml， java 文件被预编译，可以存放一些图片，html，js, css 等文件


	http://www.cnblogs.com/yc-755909659/p/4290114.html
	http://blog.sina.com.cn/s/blog_5e28af6e0101kfla.html



以下由Eclipse中的Drawable项目移植过来的哈！！
1. Android内置了如下几种Drawable类型：
    LayerDrawable、BitmapDrawable
    StateListDrawable、GradientDrawable
    ScaleDrawable、RotateDrawable
	ColorDrawable、 LevelListDrawable
	InsetDrawable、ClipDrawable、
	AnimationDrawable、 NinePatchDrawable、
	TransitionDrawable
	http://blog.csdn.net/linghu_java/article/details/42119969

2.LayerDrawable继承与Drawable，Drawable就是一个可画的对象，
           可能是一张位图BitmapDrawable,也可能是一个图形ShapeDrawable,
	还有可能是一个图层LayerDrawable。根据不同的画图需求创建相应的可画对象。
	http://www.jb51.net/article/67364.htm

3.针对LayerDrawable在xml实现方式的具体详解
    http://blog.csdn.net/lonelyroamer/article/details/8162569

4.BitmapDrawable是对bitmap的一种包装，可以设置它包装的bitmap在BitmapDrawable区域内的绘制方式，
	如平铺填充、拉伸填充或者保持图片原始大小，也可以在BitmapDrawable区域内部使用gravity指定的对齐方式
	http://blog.csdn.net/ymangu666/article/details/37729109

5.StateListDrawable,所有控件的背景基本上都使用了StateListDrawable，比如按钮就具有很多状态，按下状态、选中状态、默认状态、禁用状态等等
  GradientDrawable,表示一个渐变区域，可以实现线性渐变、发散渐变和平铺渐变效果，在Android中可以使用GradientDrawable表示很多复杂而又绚丽的界面效果
  http://blog.sina.com.cn/s/blog_7bac47070101329t.html
  ScaleDrawable,是对一个Drawable进行缩放操作，可以根据level属性控制这个drawable的缩放比率，也可以设置它在容器中的对齐方式