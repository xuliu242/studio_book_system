public class StringTest {
    public static void main(String[] args) {
        String str="010129025";
        int c = Integer.parseInt(str.substring(str.length()-1, str.length()));
        int r = Integer.parseInt(str.substring(str.length()-2,str.length() -1));
        System.out.println("行："+r+",列："+c);

    }
}
