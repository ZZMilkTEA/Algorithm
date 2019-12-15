/*
用分治法编程解决在 n 个数当中找第 n/2 小元素问题（注意：不
能用排序算法）。
输入：第一行输入 n 的值，第二行输入 n 个数；
输出：第三行输出 n/2 的值，第四行输出这 n 个数中第 n/2 小的
元素。
 */

import java.util.Scanner;

public class FindNd2Small {

    //寻找地n/2小的函数，这样单独写出来是为了让主函数中函数的调用所需的参数只有数组，调用的是求第k小的函数
    static int find_Nd2_Samll(int[] array){
        return find_K_Small(array,0,array.length-1,array.length/2); //第一次先对数组整个范围求，这里k = n/2，
                                                                                    // 其中n为元素数量
    }

    //求第k小的函数
    static int find_K_Small(int[] array, int l_pos, int r_pos, int k) {
        int partitionIndex = partition(array, l_pos, r_pos);    /*对目前的范围中找到基准值（这里为array[l_pos]）放置到它正
                                                                确大小的位置上，把该位置赋值给partitionIndex。*/
        if (partitionIndex+1 < k){                              /*如果当前基准值所在的位置比k值小（在本题中为2/n）,
                                                                则答案一定在右半边，对当前范围的右半边范围递归求解*/
            return find_K_Small(array,partitionIndex+1,r_pos,k);
        }
        else if(partitionIndex+1 > k){                          /*如果当前基准值所在的位置比k值大（在本题中为2/n）,
                                                                则答案一定在左半边，对当前范围的左半边范围递归求解*/
            return find_K_Small(array,l_pos,partitionIndex-1,k);
        }
        else {                                                  //如果当前基准值所在的位置正好等于k，则说明该基准值就是第k小的数，返回它
            return array[partitionIndex];
        }
    }

    //将基准值放置到数组中正确大小位置上的函数，并返回基准值所在的位置
    static int partition (int[] array, int l_pos, int r_pos){
        int pivot = l_pos;                      //选该段数组的第一个值为基准
        int index = pivot + 1;                  //index用来分割与基准比较的值（左小右大）
        for (int i = index; i <= r_pos; i++) {
            if (array[pivot] > array[i]) {      //比基准小的值将会被移到当前index位置
                swap(array, i, index);
                index++;                        //移动完后index++，保证index左边都比基准值小
            }
        }
        swap(array, pivot, index - 1);       //最后将基准与index-1位置的值交换，保证本次排序index-1位置的左边全小于基准值
        return index-1;     //返回基准值的位置
    }

    //交换数组中两下标所存的值
    static void swap(int[] array, int i, int j) {
        int temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    //主函数
    public static void main(String[] args){
        Scanner scanner = new Scanner(System.in);
        //输入n
        int n = scanner.nextInt();
        int[] array = new int[n];
        //输入数组的n个元素
        for (int i = 0;i < n; i++){
            array[i] = scanner.nextInt();
        }
        //求解输出
        System.out.println(n/2);
        System.out.print(find_Nd2_Samll(array));
    }
}
