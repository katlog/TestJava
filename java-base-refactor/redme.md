#重构-改善既有得代码设计

##重新组织函数
    重构中， 很大一部分是对函数进行整理， 使之更恰当地包装代码。 基本上都源于Long Method（过长函数） ，包含太多信息，这些信息又被函数错综复杂的逻辑掩盖。
	Extract Method，可把代码从原先函数中提取出来， 放进一个单独函数中。 
	Inline Method 正好相反： 将一个函数调用动作替换为该函数本体。 当多次提炼后，发现某些函数并没做任何实质事情， 或要回溯恢复原先函数， 就需要Inline Method。
	Extract Method最大的困难就是处理局部变量，而临时变量则是其中一个主要的困难源头。 处理一个函数时， 可用Replace Temp with Query去掉所有可去掉的临时变量。 如果很多地方使用了某个临时变量，先用Split Temporary Variable将它变得比较容易替换。
	但有时临时变量实在太混乱，难以替换，这时可用Replace Method with Method Object，它能分解哪怕最混乱的函数，代价则是引入一 个新class。
	参数带来的问题比临时变量稍微少一些，前提是不在函数内对它们进行赋值，否则，要用Remove Assignments to Parameters。
    函数分解完毕后，就能知道如何让它工作得更好。也许还会发现算法可以改进，从而使代码更清晰。 这时就用Substitute Algorithm引入更清晰的算法。 
###Extract Method                                                                                        （提炼函数）
       当一个过长的函数或一段需要注释才能让人理解用途的代码，就应将这段代码放进一个独立函数中。
###Inline Method                                                                                          （函数内联）
    一个函数， 其本体（method body） 若与其名称（method name)同样清楚易懂，可在函数调用点插入函数本体，后移除该函数。
###Inline Temp                                                                                              （临时变量内联）
    一个临时变量，只被一个简单表达式赋值一次，而它妨碍了其他重构手法。将所有对该变量的引用动作，替换为对它赋值的那个表达式本身
    + idea中 ctrl+alt+n快捷键
    Inline Temp多半是作为Replace Temp with Query的一部分来使用
###Replace Temp with Query                                                                       （用查询取代临时变量
    当一个临时变量（temp） 保存某一表达式的运算结果。将这个表达式提炼到一个独立函数（译注： 所谓查询式， query）中。将这个临时变量的所有「被引用点」替换为「对新函数的调用」。新函数可被其他函数使用。
###Introduce Explaining Variable                                                                   （引入解释性变量）
###Split Temporary Variable                                                                          （剖解临时变量）
###Remove Assignments to Parameters                                                       （移除对参数的赋值动作）
###Replace Method with Method Object                                                      （以函数对象取代函数）
###Substitute Algorithm                                                                                 （替换你的算法）
