# acm-template-pro-generator

> ACM 示例模板生成器
>
> 作者：CoderXiang
>
> 基于 [程序员香香] 的 [鱼籽代码生成器项目](https://gitee.com/zx162/re-generate) 制作，感谢您的使用！

可以通过命令行交互式输入的方式动态生成想要的项目代码

## 使用说明

执行项目根目录下的脚本文件：

```
generator <命令> <选项参数>
```

示例命令：

```
generator generate - -l -a -o 
```

## 参数说明

1）needGit

类型：boolean

描述：是否生成 .gitignore 文件

默认值：true

缩写： -


2）loop

类型：boolean

描述：是否生成循环

默认值：false

缩写： -l


3）author

类型：String

描述：作者注释

默认值："yupi"

缩写： -a


4）outputText

类型：String

描述：输出信息

默认值："sum = "

缩写： -o


