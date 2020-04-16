package com.project.ny.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * spring-aop機能にて、処理の開始前、後にログを出力する機能です。
 */
@Aspect
public class LogAdvisor {

	/** ロガー。 */
    private final Logger log = LoggerFactory.getLogger(LogAdvisor.class);

    /**
     * 処理開始前と処理終了後に実施されるメソッドです。
     * 開始と終了ログを出力します。
     * @param pjp 結合点情報
     */
    public Object aroundLogger(ProceedingJoinPoint pjp) throws Throwable {
        String className = pjp.getTarget().getClass().getSimpleName();
        String methodName = pjp.getSignature().getName();

        log.info("処理開始 " + className + " " + methodName);

        Object retVal = null;
        try {
            retVal = pjp.proceed();
        } finally {
            log.info("処理終了 " + className + " " + methodName);
        }

        return retVal;
    }
}
