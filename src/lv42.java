import java.io.*;

class RW {
    public void Write(File file,String s) throws Exception {
        Writer out = new FileWriter(file);
        out.write(s);
        out.close();
    }
    public String Read(File file)throws Exception{
        char c[] = new char[1024];
        Reader r = new FileReader(file);
        int len = r.read(c);
        String s = new String(c,0,len);
        r.close();
        return s;
    }


}

public class lv42{
public static void main(String args[]){
    File file = new File("e:"+File.separator+"java.txt");
    RW rw = new RW();
    try {
        rw.Write(file,"HELLO WORLD");
    }catch (Exception e){
        e.printStackTrace();
    }
    try {
        System.out.println(rw.Read(file));
    }catch (Exception e){
        e.printStackTrace();
    }

}
}