public class StringTest {
    public static void main(String[] args) {
        Long l1=1389100886111268865L;
        Long l2=1389100886111268865L;
        Long l20=200L;
        Long l21=200L;


        System.out.println("l1==l2? "+(l1==l2));
        System.out.println("l1.equals(l2)? "+(l1.equals(l2)));
        System.out.println("l21==l20? "+(l20==l21));
        System.out.println("20.equals(l21)? "+(l20.equals(l21)));
        System.out.println(l1.toString());
        System.out.println(String.valueOf(l1));

    }
}
