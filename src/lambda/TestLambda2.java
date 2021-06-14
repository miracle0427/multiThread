package lambda;

public class TestLambda2 {
    static class Love2 implements Ilove{
        @Override
        public void love(int a) {
            System.out.println("I love you2 " + a);
        }
    }
    public static void main(String[] args) {
        Ilove ilove = new Love();
        ilove.love(1);

        ilove = new Love2();
        ilove.love(2);

        class Love3 implements Ilove{
            @Override
            public void love(int a) {
                System.out.println("I love you3 " + a);
            }
        }
        ilove = new Love3();
        ilove.love(3);

        ilove = new Ilove() {
            @Override
            public void love(int a) {
                System.out.println("I love you4 " + a);
            }
        };
        ilove.love(4);

        ilove = (int a) ->System.out.println("I love you5 " + a);
        ilove.love(5);

        //简化1，去掉参数类型，多个参数也可以去掉参数类型，要去掉就都去掉，必须加括号
        ilove = (a) ->{
            System.out.println("I love you6 " + a);
        };
        ilove.love(6);

        //简化2，去掉括号
        ilove = a -> {
            System.out.println("I love you7 " + a);
        };
        ilove.love(7);

        //简化3，去掉花括号,若方法体只有一条语句，可以去掉花括号
        ilove = a -> System.out.println("I love you8 " + a);
        ilove.love(8);
    }
}

interface Ilove{
    void love(int a);
}
class Love implements Ilove{
    @Override
    public void love(int a) {
        System.out.println("I love you1 " + a);
    }
}