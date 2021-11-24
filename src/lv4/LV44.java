package lv4;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;
import java.util.ArrayList;

interface people{
    void setHp(int hp);
    void setAd(int ad);
    void setDp(int dp);
    int getAd();
    int getHp();
    int getDp();
}//英雄类
class Hero implements people{
    private int hp;
     int ad;
    private int dp;
    public Hero(int a,int b,int c){
        this.setAd(a);
        this.setHp(b);
        this.setDp(c);
    }//生成英雄
    public static Hero setHero() {
        System.out.println("请输入英雄数据");
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int c = scanner.nextInt();
        Hero hero = new Hero(a,b,c);
        System.out.println("成功生成英雄");
        return hero;
    }
    //覆写接口
    public void setHp(int hp){this.hp = hp;}
    public void setAd(int ad) {
        this.ad = ad;
    }
    public void setDp(int dp) {
        this.dp = dp;
    }
    public int getAd(){
        return ad;
    }
    public int getHp() {
        return hp;
    }
    public int getDp(){return dp;}
    // 受到攻击
    public int underact(Dog dog){
        System.out.println(dog.getName() + " 攻击了英雄，造成了" + dog.getAd() * this.getDp() / (this.getDp() + 100)
                + "点伤害。\n英雄剩余血量为"+this.getHp());
        hp = hp-dog.getAd() * this.getDp() / (this.getDp() + 100);
        return hp;
    }
}//   小兵
class Dog implements people{
    private int hp;
    private int ad;
    private int dp;
    private String name;
    public Dog(int a,int b,int c,String d){
        this.setAd(a);
        this.setHp(b);
        this.setDp(c);
        this.setName(d);
    }// 生成小兵
    public static Dog setDog() {
        Dog N[] = new Dog[1];
        N[0] = new Dog(100,200,100,"普通骷髅");
        return N[0];
    }public static Dog setBigDog() {
        Dog H[] = new Dog[1];
        H[0] = new Dog(300,500,200,"骷髅武士");
        return H[0];}
    public static Dog setBoss() {
        Dog H[] = new Dog[1];
        H[0] = new Dog(9999,9999,9999,"???");
        System.out.println("Boss登场");
        return H[0];}
    //覆写接口
    public void setHp(int hp){this.hp = hp;}
    public void setAd(int ad) {this.ad = ad;}
    public void setDp(int dp) {this.dp = dp;}
    public void setName(String name){this.name = name;}
    public int getAd(){return ad;}
    public int getHp() {return hp;}
    public int getDp(){return dp;}
    public String getName(){return name;}
    public int underact(Hero hero){
        hp = hp-hero.getAd() * this.getDp() / (this.getDp() + 100);
        System.out.println("你攻击了骷髅,其受到" + hero.getAd() * this.getDp() / (this.getDp() + 100) + "点伤害");
        return hp;
    }
}

public class LV44 {
    public static void main(String args[]){
        int chance = 1;                                       //复活机会
        Hero hero = Hero.setHero();
        System.out.println("请输入小兵数量");                    //小兵生成
        Scanner scanner = new Scanner(System.in);
        int a = scanner.nextInt();
        ArrayList<Dog> arr = new ArrayList<>();
        for(int b = 0;b < a;b++){
            arr.add(b,Dog.setDog());
        }System.out.println("请输入超级兵数量");
        int e = scanner.nextInt();
        for(int b = 0;b < e;b++){
            arr.add(a+b,Dog.setBigDog());
        } System.out.println("敌方士兵已生成");
        for(int counts = 1;counts <=20;counts++){                          //round
            if(chance==2){
                System.out.println("英雄觉醒\n请重新输入英雄数据");            //开挂
                try {
                    Class<?> c1 = Class.forName("lv4.Hero");
                    Method met = c1.getMethod("setAd", int.class);
                    met.invoke(hero,9999);
                    Method met1 = c1.getMethod("setHp", int.class);
                    met1.invoke(hero,99999);
                }catch (Exception h){
                    h.printStackTrace();
                }

            }
            for (int k = 0; k < arr.size() ; k++) {                      //血量显示
                System.out.println(arr.get(k).getName() + "   位置  "+(k + 1) + "   生命值" + arr.get(k).getHp());
            }System.out.println("请选择攻击对象");
            int c = scanner.nextInt();
            try {
                arr.get(c-1).underact(hero);
            }catch (Exception ex){
                System.out.println("攻击了个寂寞");
            }
            for(int j = 0;j<arr.size();j++){
                if(arr.get(j).getHp()<=0){
                    arr.remove(j);
                    System.out.println("小兵阵亡退场");
                }
            }//增加Boss
            if(counts==4){
                arr.add(arr.size(),Dog.setBoss());
            }
            int x = (int) (1 + Math.random() * arr.size());
            hero.underact(arr.get(x-1));
            if(hero.getHp()<=0){                          //结果判断，若对方全部死亡，则游戏结束
                if(chance<=1){                                          //若英雄第一次死亡，则触发复活机制
                    System.out.println("触发战斗续行，英雄复活");
                    hero.setHp(1000);
                    chance++;
                }
            }if(arr.size()<1){
                System.out.println("游戏结束");
                break;
            }
        }
    }
}

