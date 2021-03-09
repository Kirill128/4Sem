package edu.grsu.alg.lab2;

import java.util.Arrays;
import java.util.Random;

public class Application {
    public static void main(String[] args) {
        int [] array=generateIntArray(102);
//        quickSortForThreeParts(array,0,array.length-1);
//        selectionSort(array);
//        insertionSort(array);
        Arrays.stream(array).forEach(System.out::println);
    }
    public static void quickSortForThreeParts(int[] source, int leftBorder, int rightBorder){
        int pivot1Num=(leftBorder + rightBorder) / 3;
        int pivot2Num=pivot1Num*2;
        quickSort(source,leftBorder,pivot2Num);
        quickSort(source,pivot1Num,rightBorder);
    }
    public static void quickSort(int[] source, int leftBorder, int rightBorder) {
        int leftMarker = leftBorder;
        int rightMarker = rightBorder;
        int pivot = source[(leftMarker + rightMarker) / 2];
        do {
            // Двигаем левый маркер слева направо пока элемент меньше, чем pivot
            while (source[leftMarker] < pivot) {
                leftMarker++;
            }
            // Двигаем правый маркер, пока элемент больше, чем pivot
            while (source[rightMarker] > pivot) {
                rightMarker--;
            }
            // Проверим, не нужно обменять местами элементы, на которые указывают маркеры
            if (leftMarker <= rightMarker) {
                // Левый маркер будет меньше правого только если мы должны выполнить swap
                if (leftMarker < rightMarker) {
                    int tmp = source[leftMarker];
                    source[leftMarker] = source[rightMarker];
                    source[rightMarker] = tmp;
                }
                // Сдвигаем маркеры, чтобы получить новые границы
                leftMarker++;
                rightMarker--;
            }
        } while (leftMarker <= rightMarker);

        // Выполняем рекурсивно для частей
        if (leftMarker < rightBorder) {
//            quickSort(source, leftMarker, rightBorder);
            quickSortForThreeParts(source,leftMarker,rightBorder);
        }

        if (leftBorder < rightMarker) {
//            quickSort(source, leftBorder, rightMarker);
            quickSortForThreeParts(source,leftBorder,rightMarker);
        }
    }
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
    public static void shakerSort(){

    }
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

}
