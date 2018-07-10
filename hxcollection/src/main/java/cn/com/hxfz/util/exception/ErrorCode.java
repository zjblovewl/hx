package cn.com.hxfz.util.exception;

/**
 * 
 * 错误码借口
 * @author ren
 *
 */
public interface ErrorCode {
	// 错误码编号
	String getCode();

	// 错误码描述
	String getDescription();

	String toString();
}
