package com.taotao.utils;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * @Package com.taotao.utils
 * @User 12428
 * @Date 2018/3/23 9:46
 */

public class ExceptionUtil {
    /*
    * 获取异常的堆栈信息
    * */
    public static String getStackTrace(Throwable t){
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        try {
            t.printStackTrace(pw);
            return sw.toString();
        } finally {
            pw.close();
        }
    }
}
