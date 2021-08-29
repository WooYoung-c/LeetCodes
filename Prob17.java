import java.util.LinkedList;
import java.util.List;

public class Prob17 {
    public List<String> letterCombinations(String digits) {
        // discussion
        // 답이될 list, ans를 선언
        LinkedList<String> ans = new LinkedList<String>();
        // 입력값 digits이 비었다면 그대로 ans 반환 --> 다른 설정하기 전에 바로 함수를 종료해 효율적
        if (digits.isEmpty()) return ans;
        // digits의 값에 따라 조합해줄 문자열을 매핑
        String[] mapping = new String[]{"0", "1", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
        ans.add("");
        // 문자열의 조합이 완성될 때까지(== ans 안의 문자열이 digits과 길이가 같아질때) 반복
        while (ans.peek().length() != digits.length()) {
            // 반복 한 바퀴마다 문자열 조합의 길이는 하나씩 늘어난다
            // 반복를 새로 시작할 땐 이전 반복에서 만들어진 조합들을 지워나가면서 거기에 digits에서 눌러진 문자를 하나씩 덧붙인다
            String remove = ans.remove();
            // 새로 덧붙일 차례의 문자들을 map으로 구분(--> remove.length()로 처리한다)한다
            String map = mapping[digits.charAt(remove.length()) - '0'];
            // map의 문자 하나하나를 조합에 붙여 ans의 요소로 더해준다
            for (char c : map.toCharArray()) {
                ans.addLast(remove + c);
            }
        }
        return ans;
    }
}
