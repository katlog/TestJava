###创建型模式：5种，

##### 1.工厂方法模式
        简单工厂：SimpleFactory 通过 一个方法创建各个 Product
        工厂方法：工厂接口，不同工厂的实现。hf中的披萨示例还是很有启发的。
##### 2.抽象工厂模式
##### 3.单例模式
        懒汉模式
        饿汉模式
        ..
        枚举
##### 4.建造者模式
##### 5.原型模式

###结构型模式：7种

##### 1.配适器模式
    鸭子和火鸡的示例
* 对象适配器
* 类适配器
    
##### 2. 装饰器模式
    装饰者模式动态地将责任附加到对象上，若要扩展功能，装饰者比继承更有弹性的替代方案。
    装饰者和被装饰者有相同类型的超类
    hf中饮料的示例，对象通过装饰形成一个链表（包装类->...包装类->组件类），在通过调用链表每个节点上的cost，很像递归。
##### 3. 代理模式
    proxy常用的方式，如scf(远程代理)、spring的aop实现、java的instrument、proxy
    
* 远程代理：为一个位于不同的地址空间的对象提供一个本地的代理对象，这个不同的地址空间可是在同一台主机中，也可是在另一台主机中。
* 虚拟代理：若需要创建一个资源消耗较大的对象，先创建一个消耗相对较小的对象来表示，真实对象只在需要时才会被真正创建。
* 保护代理：控制对一个对象的访问，可给不同的用户提供不同级别的用权限。
* 缓冲代理：为某一个目标操作的结果提供临时的存储空间，以便多个客户端可共享这些结果。
* 智能引用代理：当一个对象被引用时，提供一些额外的操作，例如将对象被调用的次数记录下来等。
##### 4. 外观模式
##### 5. 桥接模式
##### 6. 组合模式
##### 7. 享元模式

###行为型模式：11种

1.	第一类：父类与子类
    
策略模式

    定义了算法族，分别封装起来，让它们之间可相互替换，此模式让算法的变化独立于使用算法的客户。
    多用在算法决策系统中，用户只需要决定用哪个算法即可。head first中的鸭子示例的启发
模板方法模式
		    
2.	第二类：两个类之间
观察者模式
    
    定义了一系列对象间的一对多关系。当一个对象改变状态，其他依赖者都会收到通知。
    hf中气象站面板例子。
迭代子模式

责任链模式

    具体处理者类，处理它所负责的请求，可访问它的后继者，如果可处理该请求，则处理，否则转给它的后继者处理
    
    总结与对比：aop、servlet filter、spring interceptor
        要实现鉴权的过滤器，以上3种方式都可以，然而从粒度，场景，和方式上边有有所区别，
        采取用哪个，还是由业务来决定去用，没有统一的参考标准。
        比如要对所有的web接口，进行统一的权限处理，不需要区分动作，写或者读，所有一视同仁，此时，servlet更适合。
        针对一些存在状态的，如做一些统一的去参数转换，cookie转uid之类，以及通用检验uid是否符合当前权限，则很用mvc较好，
        而aop粒度就可以分的更加细致了，在一些更新需要，查询不需要的，如分控，日志记录等，就比较适合
命令模式

    将“动作的请求者”与“动作的执行者”解耦。
    利用命令对象，将请求封装成对象，命令对象与具体执行者进行沟通，如LightOnCommand与Light沟通
    hf中遥控器例子。同镇房产项目groupChainPolicy是一种变种
3.	第三类：类的状态
备忘录模式
状态模式
     
     状态间的转换，如不断调用context#request，状态不断转换。水的几种状态、账户的几种状态
     hf中GumballMachine示例，逻辑分支改成状态模式
     一般用来实现有限状态机FSM，状态机3个部分：状态State 事件Event 动作Action
     状态机的实现方式
         逻辑分支
         查表法
         状态模式
4.	第四类：通过中间类
访问者模式、中介者模式、解释器模式