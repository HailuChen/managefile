# managefile
简易的文件上传管理
deme 采用springboot 框架，分为文件上传服务(consumer) 和 文件地址管理服务（provider）,两者通过dubbo直连方式的rpc调用，前端页面接收上传的文件，通过文件上传服务上传文件到服务器，并将生成的文件地址通过dubbo 服务发送给 文件地址管理服务 provider ,这里为了简化地址管理，provider服务调用redis 缓存管理地址，没有存在数据库，文件管理服务也提供了获取全部文件地址的接口，供consumer调用展示；


整个demo纯属熟悉springboot 以及dubbo 练手，比较简单，勿喷！
谢谢