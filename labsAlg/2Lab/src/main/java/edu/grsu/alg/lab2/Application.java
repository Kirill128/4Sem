package edu.grsu.alg.lab2;

import java.util.Arrays;
import java.util.Random;

public class Application {
    public static void main(String[] args) {
        int [] array=generateIntArray(102);
//        selectionSort(array);
//        insertionSort(array);
//        shekerSort(array,array.length);
//        heapSort(array);
        sort(array);
        Arrays.stream(array).forEach(System.out::println);
    }

    //-------------------------------Быстрая с 2-мя pivot Sort-------------------------------------------

    public static void sort(int[] a) {
        sort(a, 0, a.length);
    }
    public static void sort(int[] a, int fromIndex, int toIndex) {
        rangeCheck(a.length, fromIndex, toIndex);
        dualPivotQuicksort(a, fromIndex, toIndex - 1, 3);
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
    private static void swap(int[] a, int i, int j) {
        int temp = a[i];    a[i] = a[j];    a[j] = temp;
    }
    private static void dualPivotQuicksort(int[] a, int left,int right, int div) {
        int len = right - left;
        if (len < 27) { // insertion sort for tiny array
            for (int i = left + 1; i <= right; i++) {
                for (int j = i; j > left && a[j] < a[j - 1]; j--) {
                    swap(a, j, j - 1);
                }
            }
            return;
        }
        int third = len / div;
        // "medians"
        int m1 = left  + third;
        int m2 = right - third;
        if (m1 <= left) {
            m1 = left + 1;
        }
        if (m2 >= right) {
            m2 = right - 1;
        }
        if (a[m1] < a[m2]) {
        swap(a, m1, left);
        swap(a, m2, right);
        }else {
        swap(a, m1, right);
        swap(a, m2, left);
        }    // pivots
        int pivot1 = a[left];
        int pivot2 = a[right];    // pointers
        int less  = left  + 1;
        int great = right - 1;    // sorting
        for (int k = less; k <= great; k++) {
             if (a[k] < pivot1) {
                 swap(a, k, less++);
             }
             else if (a[k] > pivot2) {
                 while (k < great && a[great] > pivot2) {
                     great--;
                 }
                 swap(a, k, great--);
                 if (a[k] < pivot1) {
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
        if (dist > len - 13 && pivot1 != pivot2) {
            for (int k = less; k <= great; k++) {
                if (a[k] == pivot1) {
                    swap(a, k, less++);
                }
                else if (a[k] == pivot2) {
                    swap(a, k, great--);
                    if (a[k] == pivot1) {
                        swap(a, k, less++);
                    }
                }
            }
        }
        // subarray
        if (pivot1 < pivot2) {
              dualPivotQuicksort(a, less, great, div);
        }
}
    //-------------------------------Пирамидальная Sort-------------------------------------------

    public static void heapSort(int arr[])
    {
        int n = arr.length;

        // Построение кучи (перегруппируем массив)
        for (int i = n / 2 - 1; i >= 0; i--)
            heapify(arr, n, i);

        // Один за другим извлекаем элементы из кучи
        for (int i=n-1; i>=0; i--)
        {
            // Перемещаем текущий корень в конец
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;

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
        if (l < n && arr[l] > arr[largest])
            largest = l;

        // Если правый дочерний элемент больше, чем самый большой элемент на данный момент
        if (r < n && arr[r] > arr[largest])
            largest = r;
        // Если самый большой элемент не корень
        if (largest != i)
        {
            int swap = arr[i];
            arr[i] = arr[largest];
            arr[largest] = swap;

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
        while ((left < right) && flag > 0)
        {
            flag = 0;
            for (int i = left; i<right; i++)  //двигаемся слева направо
            {
                if (mass[i]>mass[i + 1]) // если следующий элемент меньше текущего,
                {             // меняем их местами
                    int t = mass[i];
                    mass[i] = mass[i + 1];
                    mass[i + 1] = t;
                    flag = 1;      // перемещения в этом цикле были
                }
            }
            right--; // сдвигаем правую границу на предыдущий элемент
            for (int i = right; i>left; i--)  //двигаемся справа налево
            {
                if (mass[i - 1]>mass[i]) // если предыдущий элемент больше текущего,
                {            // меняем их местами
                    int t = mass[i];
                    mass[i] = mass[i - 1];
                    mass[i - 1] = t;
                    flag = 1;    // перемещения в этом цикле были
                }
            }
            left++; // сдвигаем левую границу на следующий элемент
        }
    }
    //-------------------------------Вставки Sort-------------------------------------------

    public static void insertionSort(int[]array){
        for (int left = 0; left < array.length; left++) {
            // Вытаскиваем значение элемента
            int value = array[left];
            // Перемещаемся по элементам, которые перед вытащенным элементом
            int i = left - 1;
            for (; i >= 0; i--) {
                // Если вытащили значение меньшее — передвигаем больший элемент дальше
                if (value < array[i]) {
                    array[i + 1] = array[i];
                } else {
                    // Если вытащенный элемент больше — останавливаемся
                    break;
                }
            }
            // В освободившееся место вставляем вытащенное значение
            array[i + 1] = value;
        }
    }
//-------------------------------Выбор Sort-------------------------------------------

    public static void selectionSort(int [] array){
        for (int left = 0; left < array.length; left++) {
            int minInd = left;
            for (int i = left; i < array.length; i++) {
                if (array[i] < array[minInd]) {
                    minInd = i;
                }
            }
            int b=array[left];
            array[left]=array[minInd];
            array[minInd]=b;
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



//    public static void quickSortForThreeParts(int[] source, int leftBorder, int rightBorder){
//        int pivot1Num=(leftBorder + rightBorder) / 3;
//        int pivot2Num=pivot1Num*2;
//        quickSort(source,leftBorder,pivot2Num);
//        quickSort(source,pivot1Num,rightBorder);
//    }
//    public static void quickSort(int[] source, int leftBorder, int rightBorder) {
//        int leftMarker = leftBorder;
//        int rightMarker = rightBorder;
//        int pivot = source[(leftMarker + rightMarker) / 2];
//        do {
//            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
//            while (source[leftMarker] < pivot) {
//                leftMarker++;
//            }
//            // Двигаем правый маркер, пока элемент больше, чем pivot
//            while (source[rightMarker] > pivot) {
//                rightMarker--;
//            }
//            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
//            if (leftMarker <= rightMarker) {
//                // Левый маркер будет меньше правого только если мы должны выполнить swap
//                if (leftMarker < rightMarker) {
//                    int tmp = source[leftMarker];
//                    source[leftMarker] = source[rightMarker];
//                    source[rightMarker] = tmp;
//                }
//                // Сдвигаем маркеры, чтобы получить новые границы
//                leftMarker++;
//                rightMarker--;
//            }
//        } while (leftMarker <= rightMarker);
//
//        // Выполняем рекурсивно для частей
//        if (leftMarker < rightBorder) {
////            quickSort(source, leftMarker, rightBorder);
//            quickSortForThreeParts(source,leftMarker,rightBorder);
//        }
//
//        if (leftBorder < rightMarker) {
////            quickSort(source, leftBorder, rightMarker);
//            quickSortForThreeParts(source,leftBorder,rightMarker);
//        }
//    }
}
