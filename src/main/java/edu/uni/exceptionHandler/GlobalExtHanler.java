package edu.uni.exceptionHandler;

import javax.servlet.http.HttpServletRequest;

import edu.uni.auth.exception.RoleException;
import edu.uni.auth.exception.UnivInfoSUPException;
import edu.uni.bean.Result;
import edu.uni.bean.ResultType;
import edu.uni.utils.LogUtils;
import org.springframework.dao.PermissionDeniedDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 局异常处理器
 * @Author 何亮
 * @date 2019/4/3
 */
@ControllerAdvice
public class GlobalExtHanler {
	private LogUtils logUilts = new LogUtils(this.getClass());

	@ExceptionHandler(Exception.class)
	@ResponseBody
	public Result handlerException(Exception e, HttpServletRequest request) {
		// 不记录到日志的异常
		if(e instanceof UnivInfoSUPException
				|| e instanceof RoleException ){
		}else{
			e.printStackTrace();
			logUilts.logException(e);
		}
		return Result.build(ResultType.Failed, e.getMessage()).appendData("Exception", e.getClass().getName());
	}
}
