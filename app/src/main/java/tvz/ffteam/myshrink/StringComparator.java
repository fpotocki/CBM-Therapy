package tvz.ffteam.myshrink;

/**
 * Created by Filip on 14.4.2015..
 */
public class StringComparator {

    public boolean compare(String text1, String text2) {
         text1=removeSpecialCharacter(text1);
         text2=removeSpecialCharacter(text2);

        if (text1.equals(text2)) {
            return true;
        }
        else if (text1.toLowerCase().equals(text2.toLowerCase())) {
            return true;
        }
        else if (text1.toLowerCase().equals(text2.toLowerCase())) {
            return true;
        }
      else {return false;}

    }


    private String removeSpecialCharacter (String text){
        text= text.replaceAll("[Š]","S");
        text= text.replaceAll("[š]","s");
        text= text.replaceAll("[Đ]","D");
        text= text.replaceAll("[đ]","d");
        text= text.replaceAll("[ČĆ]","C");
        text= text.replaceAll("[čć]","c");
        text= text.replaceAll("[Ž]","Z");
        text= text.replaceAll("[ž]","z");
        return text;
    }
}