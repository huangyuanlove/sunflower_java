## sunflower_java
sunflower with java，A gardening app illustrating Android development best practices with Android Jetpack.
sunflower的java版本：园艺应用程序，说明了使用Android Jetpack进行Android开发的最佳做法。

#### 简介
1. 主页分为两个tab，默认tab展示已经栽种的植物，另外一个tab展示所有可栽种植物；
2. 点击每个植物进入到植物详情
3. 单Activity
使用到的JetPack组件如下
1. lifecycle
2. navigation
3. room
4. work
5. liveData

#### 框架搭建
1. 主页：MainActivity -> HomeViewPagerFragment
2. 我的花园：GardenFragment
3. 植物目录：PlantListFragment
4. 植物详情：PlantDetailFragment
5. 数据来源：assets中的plants.json

#### 结构简介
1. adapter:包含DatabindingAdapter,ListViewAdapter
2. data:数据库、实体类、DAO
3. utilities:常量字段
4. viewmodels:各个视图所需的ViewModel
5. widget:自定义控件
6. workers:


