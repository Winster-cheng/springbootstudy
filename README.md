2018年10月17日

1.application.properties中的
#如果是非前后端分离项目添加此配置设为false
gw.app.onlyService=false
等前后端分离合并以后在改成true

2.访问localhost:8088/center首页，会把src/main/java/templates/index.html返回，接口写在了LoginController类中
    @RequestMapping("/center")
    public String center() {
        return "index";
    }


2018年10月18日：
1.修改项目版本，从SpringBoot2.0->SpringBoot1.5

2.修复拦截器会拦截静态资源的问题


2018年11月2日
1.hueshellServiceImpl的selectByName方法中，把owner和type写死了，后期考虑如何导入的问题