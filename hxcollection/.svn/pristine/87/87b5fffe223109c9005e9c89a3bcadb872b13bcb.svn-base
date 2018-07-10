package cn.com.hxfz.util.exception;


/**
 * 
 * 公共错误码
 * @author ren
 *
 */
public enum CommonErrorCode implements ErrorCode {
    //连接错误
    
    UNSUPPORTED_TYPE("200001", "不支持的数据库类型. 请注意查看hxfz已经支持的数据库类型以及数据库版本."),
    NO_DELETE_PRIVILEGE("200002", "数据库没有DELETE权限，请联系DBA"),
    CONF_ERROR("200003", "您的配置错误."),
    CONN_DB_ERROR("200004", "连接数据库失败. 请检查您的 账号、密码、数据库名称、IP、Port或者向 DBA 寻求帮助(注意网络环境)."),
    GET_COLUMN_INFO_FAILED("200005", "获取表字段相关信息失败."),
    RS_ASYNC_ERROR("200006", "异步获取ResultSet next失败."),
    SET_SESSION_ERROR("200007", "设置 session 失败."),
    JAVA_MAIL("500001", "收件人邮箱不得为空！"),
    ;

    private final String code;

    private final String description;

    private CommonErrorCode(String code, String description) {
        this.code = code;
        this.description = description;
    }

    @Override
    public String getCode() {
        return this.code;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    @Override
    public String toString() {
        return String.format("Code:[%s], Description:[%s]. ", this.code,
                this.description);
    }
}
