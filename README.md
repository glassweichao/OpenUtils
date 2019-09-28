# Description
OpenUtils 是一个Android常用工具库，收集并整理了常用的工具类，涉及Android组件、Android控件、Java工具类等。集成方便易于使用，帮助快速开发。项目持续更新。

## Usage
### step1-Dependencies
last-version ： [![](https://jitpack.io/v/glassweichao/OpenUtils.svg)](https://jitpack.io/#glassweichao/OpenUtils)

在项目的`build.gradle`文件中添加依赖

```implementation 'com.github.glassweichao:OpenUtils:last-version'```

### step2-Init
在`Application`类的`onCreate`中添加

```OpenUtils.init(this);```进行初始化。
