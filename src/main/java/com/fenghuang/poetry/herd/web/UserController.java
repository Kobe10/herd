//package com.fenghuang.poetry.herd.web;
//
//import com.alibaba.fastjson.JSONObject;
//import com.fenghuang.poetry.herd.api.model.resp.ApplyInfoVo;
//import com.fenghuang.poetry.herd.common.web.Resp;
//import com.fenghuang.poetry.herd.service.model.dto.PageInfoDto;
//import com.fenghuang.poetry.herd.service.provider.UserService;
//import com.fenghuang.poetry.herd.web.model.req.LoginReq;
//import com.fenghuang.poetry.herd.web.model.req.QueryUserReq;
//import com.fenghuang.poetry.herd.web.model.resp.user.LoginResultVo;
//import com.fenghuang.poetry.herd.web.model.resp.user.QueryUserCountVo;
//import com.fenghuang.poetry.herd.web.model.resp.user.UserDetailInfoVo;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.http.MediaType;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.Objects;
//
///**
// * <p></p>
// * <p>
// * <PRE>
// * <BR>    修改记录
// * <BR>-----------------------------------------------
// * <BR>    修改日期         修改人          修改内容
// * </PRE>
// *
// * @author fuzq
// * @version 1.0
// * @Desc 用户
// * @date Created in 2020年05月10日 08:30
// * @since 1.0
// */
//@Slf4j
//@RestController
//@RequestMapping("/web/user")
//@Api(value = "【用户域】", tags = {"【后台系统-用户域】"}, produces = MediaType.APPLICATION_JSON_VALUE)
//public class UserController {
//    @Autowired
//    private UserService userService;
//
//    /**
//     * 查询用户列表
//     *
//     * @param queryUserReq
//     * @return
//     */
//    @ApiOperation(
//            value = "用户列表接口",
//            notes = "用户列表接口",
//            response = UserDetailInfoVo.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            httpMethod = "POST"
//    )
//    @PostMapping("/list")
//    @ResponseBody
//    public Object list(@RequestBody QueryUserReq queryUserReq) {
//        log.info("【查询用户列表接口-list】入参：{}", JSONObject.toJSONString(queryUserReq));
//        PageInfoDto<UserDetailInfoVo> userDetailInfoVoPageInfoDto = userService.findUserListByCondition(queryUserReq);
//        log.info("【查询用户列表接口-list】出参：{}", JSONObject.toJSONString(userDetailInfoVoPageInfoDto));
//        return new Resp<PageInfoDto>(userDetailInfoVoPageInfoDto);
//    }
//
//    /**
//     * 统计参赛人数
//     *
//     * @return
//     */
//    @ApiOperation(
//            value = "用户统计接口",
//            notes = "用户统计接口",
//            response = QueryUserCountVo.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            httpMethod = "POST"
//    )
//    @PostMapping("/count")
//    public Object count() {
//        log.info("【统计用户数量开始】");
//        QueryUserCountVo queryUserCountVo = userService.countUser();
//        log.info("【统计用户数量出参】: {}", JSONObject.toJSONString(queryUserCountVo));
//        return new Resp(queryUserCountVo);
//    }
//
//
//    /**
//     * 登录
//     *
//     * @param loginReq
//     * @return
//     */
//    @ApiOperation(
//            value = "后台登录",
//            notes = "后台登录",
//            response = LoginResultVo.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            httpMethod = "POST"
//    )
//    @PostMapping("/login")
//    public Object login(@RequestBody LoginReq loginReq) {
//        if (Objects.equals("admin", loginReq.getUserName()) && Objects.equals("admin", loginReq.getUserPassword())) {
//            LoginResultVo loginResultVo = LoginResultVo.builder()
//                    .loginResult("login")
//                    .build();
//            return new Resp(loginResultVo);
//        }
//        return new Resp(500, "用户名或者密码错误");
//    }
//
//
//    /**
//     * 退出登录
//     *
//     * @return
//     */
//    @ApiOperation(
//            value = "后台退出登录",
//            notes = "后台退出登录",
//            response = LoginResultVo.class,
//            produces = MediaType.APPLICATION_JSON_VALUE,
//            httpMethod = "POST"
//    )
//    @PostMapping("/logout")
//    public Object logout() {
//        LoginResultVo loginResultVo = LoginResultVo.builder()
//                .loginResult("logout")
//                .build();
//        return new Resp(loginResultVo);
//    }
//}
