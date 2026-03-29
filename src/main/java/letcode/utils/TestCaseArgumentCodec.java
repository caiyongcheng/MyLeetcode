package letcode.utils;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * 将 LeetCode 风格示例字符串解析为反射调用所需的实参，并辅助返回值与期望字符串比对。
 */
final class TestCaseArgumentCodec {

    private TestCaseArgumentCodec() {
    }

    static String[] splitInputToParamStrings(String inputStr, Type[] parameterTypes) {
        String[] paramsStrArr = new String[parameterTypes.length];
        int j = 0;
        int length = inputStr.length();
        char ch;
        int retrieve = -1;
        int isArrParam = 0;
        int doubleQuotationCount = 0;
        StringBuilder paramStr = new StringBuilder();
        for (int i = 0; i < length; i++) {
            ch = inputStr.charAt(i);
            if (ch == '=' && (doubleQuotationCount & 1) == 0) {
                retrieve = 0;
            } else if (ch == '[' && retrieve == 0) {
                isArrParam++;
            } else if (ch == ']' && retrieve == 0) {
                if (--isArrParam == 0) {
                    retrieve = 1;
                    paramStr.append(ch);
                }
            } else if (((ch == ',') || (ch == '输' && (i + 1 < length && inputStr.charAt(i + 1) == '出')))
                    && retrieve == 0 && isArrParam == 0) {
                retrieve = 1;
            }
            if (retrieve == 0) {
                if (ch != '=' || (doubleQuotationCount & 1) == 1) {
                    paramStr.append(ch);
                }
                if (ch == '\"') {
                    doubleQuotationCount++;
                }
            }
            if (retrieve == 1) {
                retrieve = -1;
                paramsStrArr[j++] = paramStr.toString();
                paramStr.delete(0, paramStr.length());
            }
        }
        if (paramStr.length() > 0 && j < paramsStrArr.length) {
            paramsStrArr[j] = paramStr.toString();
        }
        if (j == 0 && paramStr.length() == 0) {
            paramsStrArr[0] = inputStr;
        }
        return paramsStrArr;
    }

    static Object[] buildParams(Type[] typeArr, String[] paramStrArr) {
        if (typeArr == null || typeArr.length == 0 || paramStrArr == null || paramStrArr.length == 0) {
            return new Object[0];
        }
        if (typeArr.length != paramStrArr.length) {
            throw new IllegalArgumentException(String.format("type array's length[%d] not equal paramStr array's length[%d]",
                    typeArr.length, paramStrArr.length));
        }
        Object[] params = new Object[typeArr.length];
        for (int i = 0; i < typeArr.length; i++) {
            paramStrArr[i] = paramStrArr[i].replaceAll("\"", "").trim();
            params[i] = TestCaseInputUtils.resolveParameter(
                    typeArr[i].getTypeName(),
                    Objects.equals(typeArr[i], String.class)
                            ? paramStrArr[i]
                            : paramStrArr[i].replaceAll("\\s", "")
            );
        }
        return params;
    }

    static boolean returnTypeComparesLikePlainString(Method testMethod) {
        Class<?> returnType = testMethod.getReturnType();
        return returnType == String.class
                || returnType == Character.class
                || returnType.isPrimitive()
                || (returnType.isArray() && returnType.getComponentType() == String.class);
    }
}
