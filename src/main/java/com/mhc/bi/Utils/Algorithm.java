package com.mhc.bi.Utils;

/**
 * @author baiyan
 * @date 2018/09/27
 * @description
 */
public class Algorithm {

    //获取无序数组b中离x最近且<x的值,现在的做法是先对数组从大到小排序,然后在处理
    public static int getSimulateNumber(int x, int[] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = i + 1; j < array.length; j++) {
                if (array[i] > array[j]) {
                    int temp = array[i];
                    array[i] = array[j];
                    array[j] = temp;
                }
            }
        }

        int result = array[0];
        int diffNum =x-array[0] ;//这里肯定是x>array[0]，因为x是子节点的某个执行时间，array[0]是父节点的第一个执行时间
        int diffNumTmp ;


        for (int i = 0; i < array.length; i++) {
            if (x > array[i]) {
                diffNumTmp = x - array[i];
                if (diffNum > diffNumTmp) {
                    result=array[i];
                }
            }
        }
        return result;
    }


}
