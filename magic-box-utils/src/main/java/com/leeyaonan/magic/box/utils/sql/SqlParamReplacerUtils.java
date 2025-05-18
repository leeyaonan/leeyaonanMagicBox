package com.leeyaonan.magic.box.utils.sql;

import java.util.ArrayList;
import java.util.List;

public class SqlParamReplacerUtils {

    // 正则匹配参数值及类型（如 "180(Integer)"）
//    private static final Pattern PARAM_PATTERN = Pattern.compile("([^(]+)$([A-Za-z]+)$");

    public static String buildCompleteSql(String preparingSql, String parametersLine) {
        if (preparingSql.startsWith("==>  Preparing: ")) {
            preparingSql = preparingSql.substring("==>  Preparing: ".length());
        }
        if (parametersLine.startsWith("==> Parameters: ")) {
            parametersLine = parametersLine.substring("==> Parameters: ".length());
        }
        List<Object> params = parseParameters(parametersLine);
        return replacePlaceholders(preparingSql, params);
    }

    private static List<Object> parseParameters(String parametersLine) {
        List<Object> params = new ArrayList<>();
        String[] paramTokens = parametersLine.split(",\\s*");
        for (String token : paramTokens) {
            // 辣鸡正则，一点也不好使
//            Matcher matcher = PARAM_PATTERN.matcher(token.trim());
//            if (matcher.find()) {
//                String value = matcher.group(1).trim();
//                String type = matcher.group(2).trim();
//                params.add(new Param(value, type));
//            }
            if (token.contains("(Integer)")) {
                params.add(new Param(token.replace("(Integer)", ""), "Integer"));
            }
            if (token.contains("(Long)")) {
                params.add(new Param(token.replace("(Long)", ""), "Long"));
            }
            if (token.contains("(Double)")) {
                params.add(new Param(token.replace("(Double)", ""), "Double"));
            }
            if (token.contains("(Boolean)")) {
                params.add(new Param(token.replace("(Boolean)", ""), "Boolean"));
            }
            if (token.contains("(String)")) {
                params.add(new Param(token.replace("(String)", ""), "String"));
            }
            if (token.contains("(Date)")) {
                params.add(new Param(token.replace("(Date)", ""), "Date"));
            }
        }
        return params;
    }

    private static String replacePlaceholders(String sql, List<Object> params) {
        StringBuilder sb = new StringBuilder(sql);
        int paramIndex = 0;

        while (sb.indexOf("?") != -1 && paramIndex < params.size()) {
            int pos = sb.indexOf("?");
            Param param = (Param) params.get(paramIndex++);
            String formattedValue = formatParam(param);
            sb.replace(pos, pos + 1, formattedValue);
        }
        return sb.toString();
    }

    private static String formatParam(Param param) {
        String value = param.value;
        switch (param.type.toLowerCase()) {
            case "string":
            case "date":
            case "timestamp":
                return "'" + value.replace("'", "''") + "'"; // 转义单引号
            case "boolean":
                return Boolean.parseBoolean(value) ? "1" : "0";
            default: // Integer/Long/Double 等数值类型
                return value;
        }
    }

    // 参数值-类型包装类
    private static class Param {
        String value;
        String type;

        Param(String value, String type) {
            this.value = value;
            this.type = type;
        }
    }

    public static void main(String[] args) {
        // 示例输入（完全匹配日志格式）
        String preparing = "==>  Preparing: select u.*, g.`status`, g.`iz_del`, c.`forbid`, c.`surplus_fee`, g.`guarantee`, g.`stock` from `internet_hospital_third`.`t_drug_ref_drug_community_user` u left join `internet_hospital_third`.`goods` g on u.`drug_id` = g.`id` left join `internet_hospital_third`.`t_drug_ref_drug_community` c on u.`community_id` = c.`community_id` and u.`drug_id` = c.`drug_id` WHERE u.drug_id in ( ? , ? , ? , ? , ? ) and u.doctor_id = ? and u.state = ? LIMIT ?\n";

        String parameters = "==> Parameters: 180(Integer), 136(Integer), 125(Integer), 68(Integer), 3(Integer), 5cd943b5efe6c60008412a99(String), 1(Integer), 1000(Long)\n";

        // 生成完整 SQL
        String completeSql = buildCompleteSql(preparing, parameters);
        System.out.println("Complete SQL:\n" + completeSql);
    }
}
