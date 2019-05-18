package rsp.demo;

 import com.sun.imageio.plugins.common.ReaderUtil;
 import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.core.io.ClassPathResource;
 import org.springframework.core.io.Resource;
 import org.springframework.core.io.ResourceLoader;
 import org.springframework.data.repository.init.ResourceReader;
 import org.springframework.util.ResourceUtils;

 import javax.validation.constraints.Null;
 import java.io.*;
 import java.util.*;
 import java.util.HashSet;
 import java.util.LinkedList;

public class Wordladder{

    public static void main(String[] args){
        Wordladder demo = new Wordladder();
    }

    public String ss(String beginw, String endw) {
        System.out.println("Please give me two English words, and I will change the first into the second by changing one letter at a time");
        System.out.println();
        System.out.println("Dictionary file name?");
//        String fileName = "/home/rsp/chris/git/Backend/task3/task3/src/main/resources/d.txt";
        Resource res = null;
        res = new ClassPathResource("d.txt");
        HashSet<String> set = new HashSet<>();
        if(readFile(res,set)){
            System.out.println("Ending word? (or Enter to quit):");
            System.out.println("Begining word? (or Enter to quit):");
            return access(beginw,endw,set);
        }
        return "wrong";
    }

    public boolean readFile(Resource resource, HashSet<String> set) {
        String temp = null;
        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(resource.getInputStream()));
            while ((temp = br.readLine()) != null) {
                set.add(temp);
            }
            br.close();
            System.out.println("Read object success!");
            return true;
        } catch (IOException e) {
            System.out.println("Unable to open that file, try again.");
            return false;
        }
    }

    public boolean if_indic(String s, HashSet<String> set){
        return set.contains(s);
    }
    public String getstr(Stack<String> ss,StringBuilder demos){
        while(ss.size() > 1)
        {
            String temp = ss.pop();
            demos.append(temp);
            demos.append(" -> ");
            //System.out.print(temp + " -> ");
        }
        demos.append(ss.pop());
        return demos.toString();
    }
    public  String rep(String begin, int i, char c) {
        StringBuilder strB = new StringBuilder(begin);
        strB.setCharAt(i, c);
        String endss = strB.toString();
        return endss;
    }
    public String access(String begin, String end, HashSet<String> set) {
        StringBuilder demo=new StringBuilder();
        if(!if_indic(begin, set)){
            set.add(begin);
        }
        if(!if_indic(end, set)){
            set.add(end);
        }
        Stack<String> st = new Stack<String>();
        LinkedList<Stack<String>> ladder = new LinkedList<Stack<String>>();
        st.push(begin);
        ladder.addLast(st);
        while(ladder.size()!=0)
        {
            String s = ladder.getFirst().peek();
            for (int i = 0; i < s.length(); ++i){
                for (char c = 'a'; c <= 'z'; ++c){
                    String temp = rep(s,i,c);
                    if (if_indic(temp,set))
                    {
                        if (temp.equals(end))
                        {
                            ladder.getFirst().push(temp);
                            return getstr(ladder.getFirst(),demo);
                        }
                        Stack<String> temp_sta = new Stack<String>();
                        temp_sta.addAll(ladder.getFirst());
                        temp_sta.push(temp);
                        ladder.addLast(temp_sta);
                        set.remove(temp);
                    }
                }

            }
            ladder.removeFirst();
        }
        return "No word ladder found";
    }
}