package edu.uni.example;

import edu.uni.auth.bean.User;
import edu.uni.auth.service.AuthService;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("json/example")
public class ExampleController {
    @Autowired
    private AuthService authService;

    /**
     * 獲取登錄信息示例
     * @return
     */
    @GetMapping("getLoginInfo")
    public Result getLoginInfo(){
        User user = authService.getUser();
        if(user == null){
            return Result.build(ResultType.Failed, "你沒有登錄");
        }
        return Result.build(ResultType.Success).appendData("user", user);
    }
}
