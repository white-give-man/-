import java.util.Scanner;
class Calculate {
    public Calculate() {
    }

    public static float cal(float a, String b, float c) {
        float d = 0;

        switch (b) {                       //计算方法
            case "+":
                d = a + c;
                break;
            case "-":
                d = a - c;
                break;
            case "*":
                d = a * c;
                break;
            case "/":
                d = a / c;
                break;
            default:
                try {
                    throw new MyException("表达式不符合规范(数字（空格）运算符（空格）数字)");
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }

        return d;
    }
}




class MyException extends Exception{
    public MyException(String s){
        System.out.println(s);
    }

}
public class lv43 {
    public static void main(String args[]) {
        System.out.println("数字 运算符 数字");
        Scanner scanner = new Scanner(System.in);
        float a = scanner.nextFloat();
        String b = scanner.next();
        float c = scanner.nextFloat();
        System.out.printf("%.3f",Calculate.cal(a,b,c));
    }
}
