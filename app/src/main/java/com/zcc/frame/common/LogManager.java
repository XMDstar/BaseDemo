package com.zcc.frame.common;

import android.util.Log;

/**
 * 功能: 日志打印，控制日志打印级别
 * 
 * @author zcc
 * @since 2017-6-9 17:13:30
 */
public class LogManager {

	public static final boolean VERBOSE = ConstantManager.LOG_LEVEL >= 5;
	public static final boolean DEBUG = ConstantManager.LOG_LEVEL >= 4;
	public static final boolean INFO = ConstantManager.LOG_LEVEL >= 3;
	public static final boolean WARN = ConstantManager.LOG_LEVEL >= 2;
	public static final boolean ERROR = ConstantManager.LOG_LEVEL >= 1;

	/**
	 * 获取打印日志的Tag
	 * 
	 * @return 返回 执行打印日志方法所在位置的 TAG
	 */
	private static String getTag() {
		// 返回一个表示该线程的堆栈跟踪元素数组。
		// 如果该线程尚未启动或已经终止，则该方法将返回一个零长度数组。
		// 如果返回的数组不是零长度的，则其第一个元素代表堆栈顶，它是该序列中最先调用的方法。最后一个元素代表堆栈底，是该序列中最后调用的方法。
		StackTraceElement caller = Thread.currentThread().getStackTrace()[4];
		String tag = "%s.%s(L:%d)";// LoginActivity.onCreate(L:56)
		String callerClazzName = caller.getClassName();
		callerClazzName = callerClazzName.substring(callerClazzName.lastIndexOf(".") + 1);
		tag = String.format(tag, callerClazzName, caller.getMethodName(), caller.getLineNumber());
		return tag;
	}

	/**
	 * Send a VERBOSE log message.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered
	 */
	public static void v(String TAG, String msg) {
		if (VERBOSE) {
			Log.v(TAG, msg);
		}
	}

	/**
	 * Send a VERBOSE log message and log the exception.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void v(String TAG, String msg, Throwable t) {
		if (VERBOSE) {
			Log.v(TAG, msg, t);
		}
	}

	/**
	 * Send a VERBOSE log message.
	 * 
	 * @param msg The message you would like LogManagered
	 */
	public static void v(String msg) {
		if (VERBOSE) {
			Log.v(getTag(), msg);
		}
	}

	/**
	 * Send a VERBOSE log message and log the exception.
	 * 
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void v(String msg, Throwable t) {
		if (VERBOSE) {
			Log.v(getTag(), msg, t);
		}
	}

	/**
	 * Send a DEBUG log message.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered
	 */
	public static void d(String TAG, String msg) {
		if (DEBUG) {
			Log.d(TAG, msg);
		}
	}

	/**
	 * Send a DEBUG log message and log the exception.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void d(String TAG, String msg, Throwable t) {
		if (DEBUG) {
			Log.d(TAG, msg, t);
		}
	}

	/**
	 * Send a DEBUG log message.
	 * 
	 * @param msg The message you would like LogManagered
	 */
	public static void d(String msg) {
		if (DEBUG) {
			Log.d(getTag(), msg);
		}
	}

	/**
	 * Send a DEBUG log message and log the exception.
	 * 
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void d(String msg, Throwable t) {
		if (DEBUG) {
			Log.d(getTag(), msg, t);
		}
	}

	/**
	 * Send a VERBOSE log message.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered
	 */
	public static void i(String TAG, String msg) {
		if (INFO) {
			Log.i(TAG, msg);
		}
	}

	/**
	 * Send a INFO log message and log the exception.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void i(String TAG, String msg, Throwable t) {
		if (INFO) {
			Log.i(TAG, msg, t);
		}
	}

	/**
	 * Send a VERBOSE log message.
	 * 
	 * @param msg The message you would like LogManagered
	 */
	public static void i(String msg) {
		if (INFO) {
			Log.i(getTag(), msg);
		}
	}

	/**
	 * Send a INFO log message and log the exception.
	 * 
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void i(String msg, Throwable t) {
		if (INFO) {
			Log.i(getTag(), msg, t);
		}
	}

	/**
	 * Send a WARN log message.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered
	 */
	public static void w(String TAG, String msg) {
		if (WARN) {
			Log.w(TAG, msg);
		}
	}

	/**
	 * Send a WARN log message and log the exception.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void w(String TAG, String msg, Throwable t) {
		if (WARN) {
			Log.w(TAG, msg, t);
		}
	}

	/**
	 * Send a WARN log message.
	 * 
	 * @param msg The message you would like LogManagered
	 */
	public static void w(String msg) {
		if (WARN) {
			Log.w(getTag(), msg);
		}
	}

	/**
	 * Send a WARN log message and log the exception.
	 * 
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void w(String msg, Throwable t) {
		if (WARN) {
			Log.w(getTag(), msg, t);
		}
	}

	/**
	 * Send an ERROR log message.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered
	 */
	public static void e(String TAG, String msg) {
		if (ERROR) {
			Log.e(TAG, msg);
		}
	}

	/**
	 * Send a ERROR log message and log the exception.
	 * 
	 * @param TAG Used to identify the source of a log message. It usually
	 *            identifies the class or QianQianRenMai where the log call occurs.
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void e(String TAG, String msg, Throwable t) {
		if (ERROR) {
			Log.e(TAG, msg, t);
		}
	}

	/**
	 * Send an ERROR log message.
	 * 
	 * @param msg The message you would like LogManagered
	 */
	public static void e(String msg) {
		if (ERROR) {
			Log.e(getTag(), msg);
		}
	}

	/**
	 * Send a ERROR log message and log the exception.
	 * 
	 * @param msg The message you would like LogManagered.
	 * @param t An exception to log
	 */
	public static void e(String msg, Throwable t) {
		if (ERROR) {
			Log.e(getTag(), msg, t);
		}
	}

}
