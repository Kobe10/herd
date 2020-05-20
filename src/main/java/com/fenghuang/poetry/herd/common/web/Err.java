package com.fenghuang.poetry.herd.common.web;

/**
 * Created by lyy on 16/3/29.
 */
public enum Err {
    //a

    OK(0, "OK"),
    ERR(1, "ERROR"),
    PARAMERR(2, "参数错误"),
    DUMPLICATE(3, "数据重复"),
    PARAMISNULL(4, "必传参数为空"),
    PLACEHOLDER(5, "%s:%s"),
    PLACEHOLDERNULL(6, "%s为空"),
    //账单错a误码100000-100199
    //推送消息的错误码20000-20100
    PUSH_ALIAS_NULL(20001, "参数：终端标识为空"),
    PUSH_TYPE_NULL(20002, "参数：业务类型为空"),
    PUSH_CLICK_NULL(20003, "参数：是否可点击字段为空"),
    PUSH_MSG_ERROR(20004, "推送消息异常"),
    PLACEHOLDER_S(20005, "%s"),
    PLACEHOLDER_CHECK_RESULT(20006, "%s"),
    PARAM_UID_PHONE_NULL(20010, "参数：手机号或者uid为空"),
    //api接口 参数校验
    PARAM_ASYNC_NULL(20011, "参数：同步配置为空"),
    PARAM_SCENE_NULL(20012, "参数：场景编码不能为空，请联系开发配置"),
    PARAM_SCENE_NOT_EXIST(20013, "参数：场景编码不存在，请联系开发配置"),
    PARAM_ORDER_PARAM_NULL(20014, "参数：评价单参数缺失"),
    PARAM_EVALUATOR_PARAM_NULL(20015, "参数：评价人参数缺失"),
    PARAM_BE_EVALUATOR_PARAM_NULL(20016, "参数：被评人参数缺失"),
    PARAM_QUESTION_ANSWER_NULL(20017, "参数: 答案数据为空"),
    //数据清洗异常
    TEMPLATE_NULL(21001, "模板编码不存在"),
    TEMPLATE_IS_NOT_SAME(21002, "问题不属于同一模板编码"),
    PACKAGE_IS_EXCEPTION(21003, "数据打包异常"),
    WASH_DATA_EXCEPTION(21004, "数据清洗异常"),



    //service方法使用异常
    SERVICE_USE_EXCEPTION(20007, "%s"),
    NO_SUPPORT_EVALUATE_OBJECT(20008, "不支持的评价对象"),
    ALREADY_EVALUATE_OBJECT(20009, "已评价,请不要重复评价"),

    EVALUATE_ORDER_NOT_EXIST(10000, "评价单不存在"),
    EVALUATE_ORDER_EXIST(10001, "评价单已经生成且待评价"),
    EVALUATE_ORDER_REPEAT(10002, "存在多个评价单"),

    EVALUATE_SAVE_FAIL(10003, "保存评价失败，请重新提交！"),
    EVALUATE_ORDER_EXIST_COMPLETE(10004, "评价单已经生成且已评价"),
    EVALUATE_ORDER_EXIST_EXPIRE(10005, "评价单已经生成且已过期"),

    //拦截器错误编码配置
    //未登录
    LOGIN_AUTH_INVALID(70000, "您的登录状态已经失效，请重新登录"),

    MOT_QUESTION_IS_NULL(3002, "MOT落地率无初始值"),

    EVALUATE_MANAGER_FEEDBACK_IS_NULL(40002, "经理回复信息为空"),

    EVALUATE_QUESTION_IS_NULL(30000, "评价问题不存在"),

    THIRD_CLASSIFY_IS_NULL(50001, "该分类下无三级分类"),

    CHECK_STATE_PARAM_IS_NULL(60001, "评价单号为空或者业务单号和场景编码同时为空");


    private Err(int result, String message) {
        this.result = result;
        this.message = message;
    }

    private final int result;

    private final String message;

    public int getResult() {
        return result;
    }

    public String getMessage() {
        return message;
    }

}
