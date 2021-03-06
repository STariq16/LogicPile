package stariq.algorithms.sort;

// Sort array based on the size of the strings.
// If strings have the same size, sort in lexicographical order.
public class SizeSort {

    public static void main(String[] args) {
        String[] array = new String[] {"your", "hello", "dry", "cry", "fry"};
        bubbleSizeSort(array);
        for(String s : array) {
            System.out.print(s + " ");
        }

        System.out.println();

        String[] arr = new String[] {"your", "fry", "hello", "yes", "to", "cry", "dry", "so"};
        mergeSizeSort(arr);
        for(String s : arr) {
            System.out.print(s + " ");
        }
    }

    public static void bubbleSizeSort(String[] arr) {
        for(int i = 0; i < arr.length; i++) {
            for(int j = 0; j < arr.length - i - 1; j++) {
                if(arr[j].length() > arr[j + 1].length()) {
                    String temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
                if(arr[j].length() == arr[j+1].length()) {
                    if(arr[j].compareTo(arr[j+1]) > 0) {
                        String temp = arr[j];
                        arr[j] = arr[j + 1];
                        arr[j + 1] = temp;
                    }
                }
            }
        }
    }

    public static void mergeSizeSort(String[] arr) {
        if(arr.length < 2) {
            return;
        }
        int mid = arr.length/2;
        String[] left = new String[mid];
        String[] right = new String[arr.length-mid];
        for(int i = 0; i < arr.length; i++) {
            if(i < mid) {
                left[i] = arr[i];
            } else {
                right[i - mid] = arr[i];
            }
        }
        mergeSizeSort(left);
        mergeSizeSort(right);
        merge(arr, left, right);
    }

    public static void merge(String[] arr, String left[], String right[]) {
        int i = 0;
        int j = 0;
        int k = 0;
        while(i < left.length && j < right.length) {
            if(left[i].length() < right[j].length()) {
                arr[k++] = left[i++];
            } else if (right[j].length() < left[i].length()){
                arr[k++] = right[j++];
            } else {
                if(left[i].compareTo(right[j]) < 0) {
                    arr[k++] = left[i++];
                } else {
                    arr[k++] = right[j++];
                }
            }
        }
        while(i < left.length) {
            arr[k++] = left[i++];
        }
        while(j < right.length) {
            arr[k++] = right[j++];
        }
    }
}
