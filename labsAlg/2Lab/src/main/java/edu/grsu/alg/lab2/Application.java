package edu.grsu.alg.lab2;

import java.util.Arrays;
import java.util.Random;

public class Application {
    public static int swapCount;
    public static int compareCount;

    public static void main(String[] args) {
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
        swapCount=0;
        compareCount=0;
        heapSort(array);
//        Arrays.stream(array).forEach(System.out::println);
        System.out.println("SwapCount : "+swapCount);
        System.out.println("Compare Count : "+compareCount);

        array=generateIntArray(102);
        swapCount=0;
        compareCount=0;
        startQuickSort(array);
//        Arrays.stream(array).forEach(System.out::println);
        System.out.println("SwapCount : "+swapCount);
        System.out.println("Compare Count : "+compareCount);

    }

    //-------------------------------Быстрая с 2-мя pivot Sort-------------------------------------------

    public static void startQuickSort(int[] a) {
        startQuickSort(a, 0, a.length);
    }
    public static void startQuickSort(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        dualPivotQuicksort(a, fromIndex, toIndex - 1);
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

    private static void dualPivotQuicksort(int[] input, int lowIndex, int highIndex) {
        if (compareInt(highIndex,lowIndex)<=0) return;

        int pivot1=input[lowIndex];
        int pivot2=input[highIndex];

        if (compareInt(pivot1,pivot2)>0){
            swap(input, lowIndex, highIndex);
            pivot1=input[lowIndex];
            pivot2=input[highIndex];
        }
        else if (compareInt(pivot1,pivot2)==0){
            while (compareInt(pivot1,pivot2)==0 && compareInt(lowIndex,highIndex)<0){
                lowIndex++;
                pivot1=input[lowIndex];
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
        for (int i = n / 2 - 1; compareInt(i,0) >= 0; i--)
            heapify(arr, n, i);
        // Один за другим извлекаем элементы из кучи
        for (int i=n-1; compareInt(i,0) >= 0; i--)
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
        if (compareInt(l , n)<0 && compareInt(arr[l] ,arr[largest])>0)
            largest = l;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (compareInt(r , n)<0 && compareInt(arr[r] , arr[largest])>0)
            largest = r;
        // Если самый большой элемент не корень
        if (compareInt(largest,i) != 0)
        {
            swap(arr,i,largest);
            // Рекурсивно преобразуем в двоичную кучу затронутое поддерево
            heapify(arr, n, largest);
        }
    }
//-------------------------------Шейкерная  Sort-------------------------------------------

    public static void shekerSort(int []mass, int count)
    {
        int left = 0, right = count - 1; // левая и правая границы сортируемой области массива
        int flag = 1;  // флаг наличия перемещений
        // Выполнение цикла пока левая граница не сомкнётся с правой
        // и пока в массиве имеются перемещения
        while (compareInt(left , right)<0 && compareInt(flag,0)> 0)
        {
            flag = 0;
            for (int i = left; compareInt(i,right)<0; i++)  //двигаемся слева направо
            {
                if (compareInt(mass[i],mass[i + 1])>0 ) // если следующий элемент меньше текущего,
                {             // меняем их местами
                    swap(mass,i,i+1);
                    flag = 1;      // перемещения в этом цикле были
                }
            }
            right--; // сдвигаем правую границу на предыдущий элемент
            for (int i = right; compareInt(i,left)>0; i--)  //двигаемся справа налево
            {
                if (compareInt(mass[i - 1],mass[i])>0) // если предыдущий элемент больше текущего,
                {            // меняем их местами
                    swap(mass,i,i-1);
                    flag = 1;    // перемещения в этом цикле были
                }
            }
            left++; // сдвигаем левую границу на следующий элемент
        }
    }
    //-------------------------------Вставки Sort-------------------------------------------

    public static void insertionSort(int[] x){
        for(int i=1;compareInt(i,x.length)<0;i++)
            for(int j=i;compareInt(j,0)>0 && compareInt(x[j-1],x[j])>0;j--) // пока j>0 и элемент j-1 > j, x-массив int
                swap(x,j-1,j);        // меняем местами элементы j и j-1

    }
//-------------------------------Выбор Sort-------------------------------------------

    public static void selectionSort(int [] array){
        for (int left = 0; compareInt(left,  array.length)<0; left++) {
            int minInd = left;
            for (int i = left; compareInt(i,  array.length)<0; i++) {
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
