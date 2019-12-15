/*
背包问题：已知一个载重为 M 的背包和 n 件物品，第 i 件物品的重
量为 wi，如果将第 i 件物品全部装入背包，将有收益 pi，这里 wi>0，
pi>0, 0 <i<n。所谓背包问题是指求一种最佳装载方案，使得收益最大。
输入：第一行物品个数 n 和背包载重 M，以下 n 行输入物品编号 i，
物品收益 pi，物品重量 wi。
输出： x1，x2，…，xn，0 <xi<1，0 <i<n，每个 xi 是第 i 件物品
装入背包中的部分（小数位保留二位）。
*/

import java.util.Scanner;

public class KnapsackQuestion {

    //定义元素（物品）类型，w为总重量，v为总价值，i为编号
    private static class Element {
        private float w = 0;
        private float v = 0;
        int i;

        //构造函数
        Element (float w, float v, int i){
            this.w = w;
            this.v = v;
            this.i = i;
        }

        @Override
        public boolean equals(Object obj) {
            return super.equals(obj);
        }
    }

    //背包问题处理函数,以书上例子为原型修改而来
    //参数中c为背包载重，w为物品总重量数组，v为物品总价值数组
    //函数返回一个物品装载百分比x数组，其下标为物品编号
    public static float[] knapsack(float c, float[] w, float[] v, int index[]){
        int n = v.length;
        float[] x = new float[n]; //装载数组
        Element[] d = new Element[n];
        for (int i = 0; i < n; i++){
            d[i] = new Element(w[i],v[i],index[i]-1);
        }
        FastSort.fastSort(d);//利用快排，依照单价（性价比）从大到小给物品排序
        int i;
        for (i = 0; i < n; i++){    //初始化装载
            x[i] = 0;
        }
        for (i = 0; i < n; i++){    //对高价值的先尽可能装，如果当前总重量超过空余载重，说明只能装部分，跳出循环
            if(d[i].w > c){
                break;
            }
            x[d[i].i] = 1;
            c -= d[i].w;
        }
        if (i < n){     //装部分，此时剩余的c就是要装的重量
            x[d[i].i] = c/d[i].w;
        }
        return x;
    }

    //快速排序类，用来对物品单价进行排序
    public static class FastSort {
        public static void fastSort(Element[] d){
            quickSort(d, 0, d.length-1);
        }

        private static Element[] quickSort(Element[] arr, int left, int right) {
            if (left < right) {
                int partitionIndex = partition(arr, left, right);
                quickSort(arr, left, partitionIndex - 1);
                quickSort(arr, partitionIndex + 1, right);
            }
            return arr;
        }

        private static int partition (Element[] arr,int left,int right){
            int pivot = left;
            float pivotRate = arr[pivot].v / arr[pivot].w;
            int index = pivot + 1;
            for (int i = index; i <= right; i++) {
                float rate = arr[i].v / arr[i].w;
                if (rate > pivotRate) {
                    swap(arr, i, index);
                    index++;
                }
            }
            swap(arr, pivot, index - 1);
            return index - 1;
        }

        private static void swap(Element[] arr, int i, int j) {
            Element temp = arr[i];
            arr[i] = arr[j];
            arr[j] = temp;
        }
    }

    //主程序
    public static void main(String args[]){
        Scanner scanner = new Scanner(System.in);

        int n = scanner.nextInt();
        float m = scanner.nextFloat();

        float[] pi = new float[n];
        float[] wi = new float[n];
        int[] indexes = new int[n];

        //输入物品信息
        for (int i = 0; i < n; i++){
            int index = scanner.nextInt();
            float value = scanner.nextFloat();
            float weight = scanner.nextFloat();

            indexes[i] = index;
            pi[i] = value;
            wi[i] = weight;
        }

        float[] x = knapsack(m, wi, pi, indexes);

        //输出装载百分比
        for (float number:x) {
            System.out.printf("%.2f ",number);
        }
    }
}
