/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package peClase;

/**
 *
 * @author dani__000
 */
public class Algoritmo {
    private int partition(short arr[], int left, int right)
    {
      int i = left, j = right;
      short tmp;
      short pivot = arr[(left + right) / 2];
     
      while (i <= j) {
            while (arr[i] < pivot)
                  i++;
            while (arr[j] > pivot)
                  j--;
            if (i <= j) {
                  tmp = arr[i];
                  arr[i] = arr[j];
                  arr[j] = tmp;
                  i++;
                  j--;
            }
      }
      return i;
    }
 
    public void quickSort(short arr[], int left, int right) {
      int index = partition(arr, left, right);
      if (left < index - 1)
            quickSort(arr, left, index - 1);
      if (index < right)
            quickSort(arr, index, right);
    }
}
