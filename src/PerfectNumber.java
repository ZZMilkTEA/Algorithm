/*
编写一个递归程序求解下列问题
给定正整数 n（n<=10000），确定 n 是否是它所有因子之和（完数）。
输入：正整数 n 输出：若 n 是它所有因子之和，输出 Yes，否则输出 No.


什么是完数？
如果一个数恰好等于它的因子之和，则称该数为“完数”或“完全数”，例
如第一个完全数是 6，它有约数 1、2、3、6，除去它本身 6 外，其余 3 个数相加，
1+2+3=6。第二个完全数是 28，它有约数 1、2、4、7、14、28，除去它本身 28
外，其余 5 个数相加，1+2+4+7+14=28。第三个完全数是 496，有约数 1、2、4、
8、16、31、62、124、248、496，除去其本身 496 外，其余 9 个数相加，
1+2+4+8+16+31+62+124+248=496。后面的完全数还有 8128、33550336 等等。
 */

import java.util.HashSet;

public class PerfectNumber {
    //判断完数的函数
    static boolean isPerfectNumber(int n){
        if (n == 1){    //特殊情况，n等于1时直接返回“假”
            return false;
        }
        int sum = 0;    //用来累加因数，最后来判断因数和是否为本身的变量
        HashSet<Integer> hashSet = findFactor(n);   //利用函数求得所有除本身的因子
        for(Integer integer: hashSet){
            sum += integer;     //求因子和
        }
        if (sum == n){  //如果因子和与n相等，则说明为完数，返回“真”，反之返回“假”
            return true;
        }
        else {
            return false;
        }
    }

    //寻找所有除了本身以外的因数的函数，返回的是哈希表集合
    static HashSet<Integer> findFactor(int n){
        HashSet<Integer> hashSet = new HashSet<Integer>();  //用来存储因子的哈希表，目的是为了防止分解过程中因子被重复计算的问题（实际上后面使用的算法不会出现此问题，是上一次修改代码前遗留的做法）
        for (int i = 2; i <= n/2; i++){ //除数i从2一直到n/2都做一遍
            if (n % i == 0){    //余数为0说明除数i和结果都为n的因子
                hashSet.add(n / i); //在结果中添加除后的结果
                hashSet.add(i); //在结果中添加
            }
        }
        hashSet.add(1); //最后添加1元素
        return hashSet; //若除了1和本身外什么因子都没求到，返回null
    }

    //寻找并输出完数的函数，递归写法，变量n为寻找的范围，即1~n
    static void findPerfectNumber(int n){
        if (n != 0){    //递归终止条件
            findPerfectNumber(n-1); //递归代码
        }
        if (isPerfectNumber(n)) {   //方法的实际内容，由于在递归代码的后方，所以实际执行是从n=1开始的。
            System.out.println(n);  //如果n为完数，则输出该完数。
        }
    }

    //测试程序
    public static void main(String[] args) {
       findPerfectNumber(10000);    //求10000及以内的正整数中的完数
    }
}
