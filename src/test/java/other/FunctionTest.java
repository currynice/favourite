package other;


public class FunctionTest {
    public static void main(String []args){

        System.out.println(parseOtionsToAnswer("a"));
    }


    public  static String parseOtionsToAnswer(String optionsSign){

        char start='A';
        char end ='Z';
        for(int i = 0 ; i < (int)(end-start+1) ;i++){
            optionsSign = optionsSign.toUpperCase().replaceAll(String.valueOf((char)(start+(char)i)), String.valueOf(i+1));
        }
        return optionsSign;
    }
}
