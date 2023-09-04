package com.zoo.tiger.me.util;

import com.zoo.tiger.me.annotation.Log;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.MDC;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Slf4j
public class ExpressionUtils {
    private static final ExpressionParser expressionParser = new SpelExpressionParser();

    public static void parseValue2MDC(Log logAnnotation, EvaluationContext context) {
        // 解析过后的Spring表达式对象
        if (StringUtils.isNotEmpty(logAnnotation.businessName())) {
            MdcUtils.setBusinessName(logAnnotation.businessName());
        }// 解析过后的Spring表达式对象
        if (StringUtils.isNotEmpty(logAnnotation.operator())) {
            parseValue2MDC(context, expressionParser.parseExpression(logAnnotation.userID()), "userID");
        }
    }

    private static void parseValue2MDC(EvaluationContext context, Expression expression, String key) {
        try {

            String expressionString = expression.getExpressionString();
            if (StringUtils.isEmpty(expressionString)) {
                return;
            }

            String value;

            if (expressionString.startsWith("T(")) {
                value = expression.getValue(String.class);
            } else {
                value = expression.getValue(context, String.class);
            }

            if (StringUtils.isNotEmpty(value)) {
                MDC.put(key, value);
            }
        } catch (Exception e) {
            log.error("解析表达式异常", e);
        }
    }
}
