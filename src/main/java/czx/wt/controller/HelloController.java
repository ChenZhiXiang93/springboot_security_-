package czx.wt.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author:ChenZhiXiang
 * @Description:
 * @Date:Created in 17:15 2018/8/23
 * @Modified By:
 */
@RestController
public class HelloController {

    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    public String hello(){

        return "您好，陈志翔!";
    }

    @RequestMapping(value = "/gun",method = RequestMethod.GET)
    public String gun(){
        return "滚，陈志翔!";
    }

    @RequestMapping("/first")
    public Object first() {
        return "first controller";
    }

    @RequestMapping("/doError")
    public Object error() {
        return 1 / 0;
    }
}
