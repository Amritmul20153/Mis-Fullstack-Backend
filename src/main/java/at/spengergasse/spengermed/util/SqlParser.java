package at.spengergasse.spengermed.util;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SqlParser {

    public static void main(String[] args) {
        System.out.println("Enter log lines (Type 'END' to finish):");

        Scanner scanner = new Scanner(System.in);
        List<String> logLines = new ArrayList<>();
        String inputLine;
        while (!(inputLine = scanner.nextLine()).equals("END")) {
            logLines.add(inputLine);
        }

        StringBuilder currentSQL = new StringBuilder();
        Map<Integer, String> paramMap = new HashMap<>();
        Pattern sqlPattern = Pattern.compile("^.*org\\.hibernate\\.SQL.*: (insert into.*|update.*)$");
        Pattern bindPattern = Pattern.compile("binding parameter \\[(\\d+)] as \\[\\w+] - \\[(.*)]");

        for (String line : logLines) {
            Matcher sqlMatcher = sqlPattern.matcher(line);
            Matcher bindMatcher = bindPattern.matcher(line);

            if (sqlMatcher.find()) {
                if (currentSQL.length() > 0) {
                    outputSQL(currentSQL.toString(), paramMap);
                    paramMap.clear();
                }
                currentSQL = new StringBuilder(sqlMatcher.group(1));
            } else if (bindMatcher.find()) {
                int paramIndex = Integer.parseInt(bindMatcher.group(1));
                String value = bindMatcher.group(2).equals("null") ? "NULL" : "'" + bindMatcher.group(2) + "'";
                paramMap.put(paramIndex, value);
            }
        }

        if (currentSQL.length() > 0) {
            outputSQL(currentSQL.toString(), paramMap);
        }


    }

    private static void outputSQL(String sql, Map<Integer, String> paramMap) {
        for (Map.Entry<Integer, String> entry : paramMap.entrySet()) {
            sql = sql.replaceFirst("\\?", Matcher.quoteReplacement(entry.getValue()));
        }
        System.out.println(sql + ";");
    }

}
/*

 */