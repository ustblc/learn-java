package Interview2020.DJI._3;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

class Cookie{
    int value;
    int weight;
}
public class Main2 {
    private static Comparator<Cookie> cookieComparator = new Comparator<Cookie>() {
        @Override
        public int compare(Cookie o1, Cookie o2) {
            return o2.weight - o1.weight;
        }
    };
    private static PriorityQueue<Cookie> queue = new PriorityQueue<>(cookieComparator);
    private static int res = 0;

    public static void main(String[] args) {
        //1. 获取所有的输入
        Scanner sc = new Scanner(System.in);
        int money = sc.nextInt();
        int total = sc.nextInt();
        int[] cookie = new int[total];
        //System.out.println();
        for(int i=0; i < total; i++){
            cookie[i] = sc.nextInt();
        }
        int mostLike = sc.nextInt();
        int[] favor = new int[mostLike];
        for(int i=0; i < mostLike; i++){
            favor[i] = sc.nextInt();
        }

        //2. 处理数据
        int ini_w = 0;
        int[] weight = new int[total];
        for(int i=mostLike-1; i >= 0; i--){
            weight[favor[i]-1] = ini_w;
            ini_w++;
        }

        for(int i=0; i < total; i++){
            Cookie c = new Cookie();
            c.value = cookie[i];
            c.weight = weight[i];
            queue.add(c);
        }

        Cookie[] sortCookie = new Cookie[total];
        int i = 0;
        while(!queue.isEmpty()){
            sortCookie[i] = queue.poll();
            i++;
        }

        //3. 遍历所有的情况
        helper2(sortCookie,new int[total],money,sortCookie[0].weight+1,total);
        System.out.println(res);
    }

    private static void helper2(Cookie[] cookies,int[] used,int cur_money,int max_weight,int total){
        for(int i=0; i < cookies.length; i++){
            if(cookies[i].weight < max_weight && used[i] == 0){
                int[] new_used = Arrays.copyOf(used,total);
                new_used[i] = 1;
                for(int j = cookies[i].weight; j <= cur_money/cookies[i].value; j++)
                    if(cur_money - j * cookies[i].value == 0){
                        res++;
                        break;
                    }
                    else{
                        if(cur_money - j * cookies[i].value > 0)
                            helper2(cookies,new_used,cur_money - j * cookies[i].value,cookies[i].weight,total);
                    }
            }
        }
    }
}

