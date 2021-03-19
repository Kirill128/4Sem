package edu.grsu.alg.lab2;

import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Application {
    public static int swapCount;
    public static int compareCount;

    public static void main(String[] args) {
<<<<<<< HEAD
        int [] array=generateIntArray(102);

//        swapCount=0;
//        compareCount=0;
//        selectionSort(array);
//        Arrays.stream(array).forEach(System.out::println);
//        System.out.println("Swap Count : "+swapCount);
//        System.out.println("Compare Count : "+compareCount);

//        swapCount=0;
//        compareCount=0;
//        insertionSort(array);
//        Arrays.stream(array).forEach(System.out::println);
//        System.out.println("SwapCount : "+swapCount);
//        System.out.println("Compare Count : "+compareCount);

//        swapCount=0;
//        compareCount=0;
//        shekerSort(array,array.length);
//        Arrays.stream(array).forEach(System.out::println);
//        System.out.println("SwapCount : "+swapCount);
//        System.out.println("Compare Count : "+compareCount);
=======
        int [] array;//=generateIntArray(102);
        while(true){
        Scanner scanner=new Scanner(System.in);
        System.out.print("Elements in arr: ");
        int amountEl=scanner.nextInt();

        System.out.println("\n1-Selection sort" +
                "\n2-Insertion sort" +
                "\n3-Sheker sort" +
                "\n4-Heap sort" +
                "\n5-Quick sort");
        switch (scanner.nextInt()) {
            case 1:
        array=generateIntArray(amountEl);
>>>>>>> 44b3424b1bcc7af58d3fa0f98d0e2c0cc2567d6f
        swapCount=0;
        compareCount=0;
        selectionSort(array);
        Arrays.stream(array).forEach(System.out::println);
        System.out.println("Swap Count : "+swapCount);
        System.out.println("Compare Count : "+compareCount);
            break;
            case 2:
        array=generateIntArray(amountEl);
        swapCount=0;
        compareCount=0;
        insertionSort(array);
        Arrays.stream(array).forEach(System.out::println);
        System.out.println("SwapCount : "+swapCount);
        System.out.println("Compare Count : "+compareCount);
            break;
            case 3:
            array=generateIntArray(amountEl);
            swapCount=0;
            compareCount=0;
            shekerSort(array,array.length);
            Arrays.stream(array).forEach(System.out::println);
            System.out.println("SwapCount : "+swapCount);
            System.out.println("Compare Count : "+compareCount);
                break;
            case 4:
                array = generateIntArray(amountEl);
                swapCount = 0;
                compareCount = 0;
                heapSort(array);
                Arrays.stream(array).forEach(System.out::println);
                System.out.println("SwapCount : " + swapCount);
                System.out.println("Compare Count : " + compareCount);
            break;
            case 5:
                array = generateIntArray(amountEl);
                swapCount = 0;
                compareCount = 0;
                startQuickSort(array);
                Arrays.stream(array).forEach(System.out::println);
                System.out.println("SwapCount : " + swapCount);
                System.out.println("Compare Count : " + compareCount);
            break;
        }
        }
    }

    //-------------------------------Быстрая с 2-мя pivot Sort-------------------------------------------

    public static void startQuickSort(int[] a) {
        startQuickSort(a, 0, a.length);
    }
    public static void startQuickSort(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
<<<<<<< HEAD
        dualPivotQuicksort(a, fromIndex, toIndex - 1);
=======
        dualPivotQuicksort(a, fromIndex, toIndex - 1,3);
>>>>>>> 44b3424b1bcc7af58d3fa0f98d0e2c0cc2567d6f
    }
    private static void rangeCheck(int length, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {
            throw new IllegalArgumentException("fromIndex > toIndex");
        }    if (fromIndex < 0) {
            throw new ArrayIndexOutOfBoundsException(fromIndex);
        }    if (toIndex > length) {
            throw new ArrayIndexOutOfBoundsException(toIndex);
        }
    }

<<<<<<< HEAD
    private static void dualPivotQuicksort(int[] input, int lowIndex, int highIndex) {
        if (compareInt(highIndex,lowIndex)<=0) return;
=======
//    private static void dualPivotQuicksort(int[] input, int lowIndex, int highIndex) {
//        if (compareInt(highIndex,lowIndex)<=0) return;
//
//        int len=(highIndex-lowIndex)/3;
//        int leftIndex=lowIndex;
//        int rightIndex=highIndex;
//
//        int pivot1=input[leftIndex];
//        int pivot2=input[rightIndex];
//
//        if (compareInt(pivot1,pivot2)>0){
//            swap(input, leftIndex, rightIndex);
//            pivot1=input[leftIndex];
//            pivot2=input[rightIndex];
//            //sort(input, lowIndex, highIndex);
//        }
//        else if (compareInt(pivot1,pivot2)==0){
//            while (compareInt(pivot1,pivot2)==0 && compareInt(leftIndex,rightIndex)<0){
//                lowIndex++;
//                pivot1=input[leftIndex];
//            }
//        }
//
//        int i=leftIndex+1;
//        int lt=leftIndex+1;
//        int gt=rightIndex-1;
//
//        while (compareInt(i,gt)<=0){
//
//            if (compareInt(input[i], pivot1)<0){
//                swap(input, i++, lt++);
//            }
//            else if (compareInt(pivot2, input[i])<0){
//                swap(input, i, gt--);
//            }
//            else{
//                i++;
//            }
//
//        }
//
//        swap(input, leftIndex, --lt);
//        swap(input, rightIndex, ++gt);
//
//        dualPivotQuicksort(input, leftIndex, lt-1);
//        dualPivotQuicksort (input, lt+1, gt-1);
//        dualPivotQuicksort(input, gt+1, rightIndex);
//
//    }
    private static void dualPivotQuicksort(int[] a, int left,int right, int div) {
        int len = right - left;
>>>>>>> 44b3424b1bcc7af58d3fa0f98d0e2c0cc2567d6f

        int pivot1=input[lowIndex];
        int pivot2=input[highIndex];

<<<<<<< HEAD
        if (compareInt(pivot1,pivot2)>0){
            swap(input, lowIndex, highIndex);
            pivot1=input[lowIndex];
            pivot2=input[highIndex];
        }
        else if (compareInt(pivot1,pivot2)==0){
            while (compareInt(pivot1,pivot2)==0 && compareInt(lowIndex,highIndex)<0){
                lowIndex++;
                pivot1=input[lowIndex];
=======
        int third = len / div;
        // "medians"
        int m1 = left  + third;
        int m2 = right - third;
        if (m1<=  left) {
            m1 = left + 1;
        }
        if (m2 >= right) {
            m2 = right - 1;
        }
        if (compareInt(a[m1],  a[m2])<0) {
        swap(a, m1, left);
        swap(a, m2, right);
        }else {
        swap(a, m1, right);
        swap(a, m2, left);
        }    // pivots
        int pivot1 = a[left];
        int pivot2 = a[right];
        // pointers
        int less  = left  + 1;
        int great = right - 1;
        // sorting
        for (int k = less; k<=great; k++) {
             if (compareInt(a[k],  pivot1)<0) {
                 swap(a, k, less++);
             }
             else if (compareInt(a[k] , pivot2)>0) {
                 while (k < great && compareInt(a[great] , pivot2)>0) {
                     great--;
                 }
                 swap(a, k, great--);
                 if (compareInt(a[k] , pivot1)<0) {
                     swap(a, k, less++);
                 }
             }
        }
        // swaps
        int dist = great - less;
        if (dist < 13) {
            div++;
        }
         swap(a, less  - 1, left);
         swap(a, great + 1, right);
         // subarrays
        dualPivotQuicksort(a, left,   less - 2, div);
        dualPivotQuicksort(a, great + 2, right, div);
        // equal elements
        if (dist >len - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (compareInt(a[k] , pivot1)==0) {
                    swap(a, k, less++);
                }
                else if (compareInt(a[k] , pivot2)==0) {
                    swap(a, k, great--);
                    if (compareInt(a[k] , pivot1)==0) {
                        swap(a, k, less++);
                    }
                }
>>>>>>> 44b3424b1bcc7af58d3fa0f98d0e2c0cc2567d6f
            }
        }

        int i=lowIndex+1;
        int lt=lowIndex+1;
        int gt=highIndex-1;

        while (compareInt(i,gt)<=0){
            if (compareInt(input[i], pivot1)<0){
                swap(input, i++, lt++);
            }
            else if (compareInt(pivot2, input[i])<0){
                swap(input, i, gt--);
            }
            else{
                i++;
            }
        }

        swap(input, lowIndex, --lt);
        swap(input, highIndex, ++gt);

        dualPivotQuicksort(input, lowIndex, lt-1);
        dualPivotQuicksort (input, lt+1, gt-1);
        dualPivotQuicksort(input, gt+1, highIndex);
    }

//    private static void dualPivotQuicksort(int[] a, int left,int right, int div) {
//        int len = right - left;
//
//        if (compareInt(len , 27)<0) { // insertion sort for tiny array
////            for (int i = left + 1; i <= right; i++) {
////                for (int j = i; j > left && a[j] < a[j - 1]; j--) {
////                    swap(a, j, j - 1);
////                }
////            }
//            insertionSort(a);
//            return;
//        }
//
//        int third = len / div;
//        // "medians"
//        int m1 = left  + third;
//        int m2 = right - third;
//        if (compareInt(m1,  left)<=0) {
//            m1 = left + 1;
//        }
//        if (compareInt(m2 , right)>=0) {
//            m2 = right - 1;
//        }
//        if (compareInt(a[m1],  a[m2])<0) {
//        swap(a, m1, left);
//        swap(a, m2, right);
//        }else {
//        swap(a, m1, right);
//        swap(a, m2, left);
//        }    // pivots
//        int pivot1 = a[left];
//        int pivot2 = a[right];
//        // pointers
//        int less  = left  + 1;
//        int great = right - 1;
//        // sorting
//        for (int k = less; compareInt(k,  great)<=0; k++) {
//             if (compareInt(a[k],  pivot1)<0) {
//                 swap(a, k, less++);
//             }
//             else if (compareInt(a[k] , pivot2)>0) {
//                 while (compareInt(k , great)<0 && compareInt(a[great] , pivot2)>0) {
//                     great--;
//                 }
//                 swap(a, k, great--);
//                 if (compareInt(a[k] , pivot1)<0) {
//                     swap(a, k, less++);
//                 }
//             }
//        }
//        // swaps
//        int dist = great - less;
//        if (compareInt(dist , 13)<0) {
//            div++;
//        }
//         swap(a, less  - 1, left);
//         swap(a, great + 1, right);
//         // subarrays
//        dualPivotQuicksort(a, left,   less - 2, div);
//        dualPivotQuicksort(a, great + 2, right, div);
//        // equal elements
//        if (compareInt(dist , len - 13)>0 && compareInt(pivot1 , pivot2)!=0) {
//            for (int k = less; compareInt(k , great)<=0; k++) {
//                if (compareInt(a[k] , pivot1)==0) {
//                    swap(a, k, less++);
//                }
//                else if (compareInt(a[k] , pivot2)==0) {
//                    swap(a, k, great--);
//                    if (compareInt(a[k] , pivot1)==0) {
//                        swap(a, k, less++);
//                    }
//                }
//            }
//        }
//        // subarray
//        if (compareInt(pivot1 , pivot2)<0) {
//              dualPivotQuicksort(a, less, great, div);
//        }
//    }
    //-------------------------------Пирамидальная Sort-------------------------------------------

    public static void heapSort(int arr[])
    {
        int n = arr.length;
        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i>=0; i--)
            heapify(arr, n, i);
        // Один за другим извлекаем элементы из кучи
        for (int i=n-1; i >= 0; i--)
        {
            // Перемещаем текущий корень в конец
            swap(arr,0,i);
            // Вызываем процедуру heapify на уменьшенной куче
            heapify(arr, i, 0);
        }
    }

    // Процедура для преобразования в двоичную кучу поддерева с корневым узлом i, что является
// индексом в arr[]. n - размер кучи
    public static void heapify(int arr[], int n, int i)
    {
        int largest = i; // Инициализируем наибольший элемент как корень
        int l = 2*i + 1; // левый = 2*i + 1
        int r = 2*i + 2; // правый = 2*i + 2

        // Если левый дочерний элемент больше корня
        if (l < n && compareInt(arr[l] ,arr[largest])>0)
            largest = l;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && compareInt(arr[r] , arr[largest])>0)
            largest = r;
        // Если самый большой элемент не корень
        if (largest!=i)
        {
            swap(arr,i,largest);
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largest);
        }
    }
//-------------------------------Шейкерная  Sort-------------------------------------------

    public static void shekerSort(int []array, int count)
    {

        boolean sort_or_not = true;
        int right = array.length - 1; // n - размер массива
        int left = 1;
        do {
            sort_or_not = true;
            for (int i = left; i <= right; i++) {
                if (compareInt(array[i - 1] , array[i])>0) {
                    swap (array,i - 1, i);
                    sort_or_not = false;
                }
            }
            right--;
            for (int i = right; i >= left; i--) {
                if (compareInt(array[i] , array[i - 1])<0) {
                    swap (array,i, i - 1);
                    sort_or_not = false;
                }
            }
            left++;
        } while (sort_or_not == false);

    }
    //-------------------------------Вставки Sort-------------------------------------------

    public static void insertionSort(int[] x){
        for(int i=1;i<x.length;i++)
            for(int j=i;j>0 && compareInt(x[j-1],x[j])>0;j--) // пока j>0 и элемент j-1 > j, x-массив int
                swap(x,j-1,j);        // меняем местами элементы j и j-1

    }
//-------------------------------Выбор Sort-------------------------------------------

    public static void selectionSort(int [] array){
        for (int left = 0; left<  array.length; left++) {
            int minInd = left;
            for (int i = left; i< array.length; i++) {
                if ( compareInt(array[i],array[minInd])<0) {
                    minInd = i;
                }
            }
//            int b=array[left];
//            array[left]=array[minInd];
//            array[minInd]=b;
            swap(array,left,minInd);
        }
    }
//--------------------------------------------------------------------------

    public static int[] generateIntArray(int length){
        int leftRandBorder=-123;
        int rightRandBorder=890;
        int [] array=new int[length];
        Random rand=new Random();
        for(int i=0;i<array.length;i++){
            array[i]= rand.nextInt(rightRandBorder-leftRandBorder)+leftRandBorder;
        }
        return array;
    }

    public static int compareInt(int a,int b){
        compareCount++;
        return a-b;
    }
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
        swapCount++;
    }

}
