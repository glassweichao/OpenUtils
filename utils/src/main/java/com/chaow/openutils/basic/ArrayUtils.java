package com.chaow.openutils.basic;

import java.lang.reflect.Array;
import java.util.Arrays;

/**
 * @author : Char
 * @date : 2019/9/1
 * github  : https://github.com/glassweichao/OpenUtils
 * desc    : 数组工具类
 */
public final class ArrayUtils {

    private ArrayUtils() {
    }

    /**
     * 创建数组
     *
     * @param array 数组内容
     * @param <T>   类型
     * @return T类型数组
     */
    @SafeVarargs
    public static <T> T[] arrayOf(T... array) {
        return array;
    }


    /**
     * 创建long类型数组
     *
     * @param array 数组
     * @return long类型数组
     */
    public static long[] longArrayOf(long... array) {
        return array;
    }

    /**
     * 创建int类型数组
     *
     * @param array 数组
     * @return int类型数组
     */
    public static int[] intArrayOf(int... array) {
        return array;
    }

    /**
     * 创建short类型数组
     *
     * @param array 数组
     * @return short类型数组
     */
    public static short[] shortArrayOf(short... array) {
        return array;
    }

    /**
     * 创建long类型数组
     *
     * @param array 数组
     * @return long类型数组
     */
    public static char[] charArrayOf(char... array) {
        return array;
    }

    /**
     * 创建byte类型数组
     *
     * @param array 数组
     * @return byte类型数组
     */
    public static byte[] byteArrayOf(byte... array) {
        return array;
    }

    /**
     * 创建double类型数组
     *
     * @param array 数组
     * @return double类型数组
     */
    public static double[] doubleArrayOf(double... array) {
        return array;
    }

    /**
     * 创建float类型数组
     *
     * @param array 数组
     * @return float类型数组
     */
    public static float[] floatArrayOf(float... array) {
        return array;
    }

    /**
     * 创建boolean类型数组
     *
     * @param array 数组
     * @return boolean类型数组
     */
    public static boolean[] booleanArrayOf(boolean... array) {
        return array;
    }

    /**
     * Return the array is empty.
     *
     * @param array The array.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean isEmpty(Object array) {
        return getLength(array) == 0;
    }

    /**
     * Return the size of array.
     *
     * @param array The array.
     * @return the size of array
     */
    public static int getLength(Object array) {
        if (array == null) {
            return 0;
        }
        return Array.getLength(array);
    }

    /**
     * Get the value of the specified index of the array.
     *
     * @param array The array.
     * @param index The index into the array.
     * @return the value of the specified index of the array
     */
    public static Object get(Object array, int index) {
        return get(array, index, null);
    }

    /**
     * Get the value of the specified index of the array.
     *
     * @param array        The array.
     * @param index        The index into the array.
     * @param defaultValue The default value.
     * @return the value of the specified index of the array
     */
    public static Object get(Object array, int index, Object defaultValue) {
        if (array == null) {
            return defaultValue;
        }
        try {
            return Array.get(array, index);
        } catch (Exception e) {
            e.printStackTrace();
            return defaultValue;
        }
    }

    /**
     * Set the value of the specified index of the array.
     *
     * @param array The array.
     * @param index The index into the array.
     * @param value The new value of the indexed component.
     */
    public static void set(Object array, int index, Object value) {
        if (array == null) {
            return;
        }
        Array.set(array, index, value);
    }

    /**
     * Return whether the two arrays are equals.
     *
     * @param a  One array.
     * @param a2 The other array.
     * @return {@code true}: yes<br>{@code false}: no
     */
    public static boolean equals(Object[] a, Object[] a2) {
        return Arrays.deepEquals(a, a2);
    }

    /**
     * 逆转数组
     *
     * @param array 给定数组
     * @param <T>   数组类型
     */
    public static <T> void reverse(T[] array) {
        if (array == null) {
            return;
        }
        int i = 0;
        int j = array.length - 1;
        T tmp;
        while (j > i) {
            tmp = array[j];
            array[j] = array[i];
            array[i] = tmp;
            j--;
            i++;
        }
    }

    /**
     * <p>Copies the specified array and handling
     * <code>null</code>.</p>
     *
     * <p>The objects in the array are not cloned, thus there is no special
     * handling for multi-dimensional arrays.</p>
     *
     * <p>This method returns <code>null</code> for a <code>null</code> input array.</p>
     *
     * @param array the array to shallow clone, may be <code>null</code>
     * @return the cloned array, <code>null</code> if <code>null</code> input
     */
    public static <T> T[] copy(T[] array) {
        if (array == null) {
            return null;
        }
        return subArray(array, 0, array.length);
    }

    private static Object realCopy(Object array) {
        if (array == null) {
            return null;
        }
        return realSubArray(array, 0, getLength(array));
    }

    public static <T> T[] subArray(T[] array, int startIndexInclusive, int endIndexExclusive) {
        return (T[]) realSubArray(array, startIndexInclusive, endIndexExclusive);
    }

    private static Object realSubArray(Object array, int startIndexInclusive, int endIndexExclusive) {
        if (array == null) {
            return null;
        }
        if (startIndexInclusive < 0) {
            startIndexInclusive = 0;
        }
        int length = getLength(array);
        if (endIndexExclusive > length) {
            endIndexExclusive = length;
        }
        int newSize = endIndexExclusive - startIndexInclusive;
        Class type = array.getClass().getComponentType();
        if (newSize <= 0) {
            return Array.newInstance(type, 0);
        }
        Object subArray = Array.newInstance(type, newSize);
        System.arraycopy(array, startIndexInclusive, subArray, 0, newSize);
        return subArray;
    }

    /**
     * <p>Copies the given array and adds the given element at the end of the new array.</p>
     *
     * <p>The new array contains the same elements of the input
     * array plus the given element in the last position. The component type of
     * the new array is the same as that of the input array.</p>
     *
     * <p>If the input array is <code>null</code>, a new one element array is returned
     * whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.realAdd(null, null)      = [null]
     * ArrayUtils.realAdd(null, "a")       = ["a"]
     * ArrayUtils.realAdd(["a"], null)     = ["a", null]
     * ArrayUtils.realAdd(["a"], "b")      = ["a", "b"]
     * ArrayUtils.realAdd(["a", "b"], "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param array   the array to "realAdd" the element to, may be <code>null</code>
     * @param element the object to realAdd
     * @return A new array containing the existing elements plus the new element
     */
    public static <T> T[] add(T[] array, T element) {
        Class type = array != null ? array.getClass() : (element != null ? element.getClass() : Object.class);
        return (T[]) realAddOne(array, element, type);
    }

    private static Object realAddOne(Object array, Object element, Class newArrayComponentType) {
        Object newArray;
        int arrayLength = 0;
        if (array != null) {
            arrayLength = getLength(array);
            newArray = Array.newInstance(array.getClass().getComponentType(), arrayLength + 1);
            System.arraycopy(array, 0, newArray, 0, arrayLength);
        } else {
            newArray = Array.newInstance(newArrayComponentType, 1);
        }
        Array.set(newArray, arrayLength, element);
        return newArray;
    }

    /**
     * <p>Adds all the elements of the given arrays into a new array.</p>
     * <p>The new array contains all of the element of <code>array1</code> followed
     * by all of the elements <code>array2</code>. When an array is returned, it is always
     * a new array.</p>
     *
     * <pre>
     * ArrayUtils.add(null, null)     = null
     * ArrayUtils.add(array1, null)   = copy of array1
     * ArrayUtils.add(null, array2)   = copy of array2
     * ArrayUtils.add([], [])         = []
     * ArrayUtils.add([null], [null]) = [null, null]
     * ArrayUtils.add(["a", "b", "c"], ["1", "2", "3"]) = ["a", "b", "c", "1", "2", "3"]
     * </pre>
     *
     * @param array1 the first array whose elements are added to the new array, may be <code>null</code>
     * @param array2 the second array whose elements are added to the new array, may be <code>null</code>
     * @return The new array, <code>null</code> if <code>null</code> array inputs.
     * The type of the new array is the type of the first array.
     */
    public static <T> T[] add(T[] array1, T[] array2) {
        return (T[]) realAddArr(array1, array2);
    }

    private static Object realAddArr(Object array1, Object array2) {
        if (array1 == null && array2 == null) {
            return null;
        }
        if (array1 == null) {
            return realCopy(array2);
        }
        if (array2 == null) {
            return realCopy(array1);
        }
        int len1 = getLength(array1);
        int len2 = getLength(array2);
        Object joinedArray = Array.newInstance(array1.getClass().getComponentType(), len1 + len2);
        System.arraycopy(array1, 0, joinedArray, 0, len1);
        System.arraycopy(array2, 0, joinedArray, len1, len2);
        return joinedArray;
    }

    /**
     * <p>Inserts the specified element at the specified position in the array.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).</p>
     *
     * <p>This method returns a new array with the same elements of the input
     * array plus the given element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.</p>
     *
     * <p>If the input array is <code>null</code>, a new one element array is returned
     * whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0, null)      = [null]
     * ArrayUtils.add(null, 0, "a")       = ["a"]
     * ArrayUtils.add(["a"], 1, null)     = ["a", null]
     * ArrayUtils.add(["a"], 1, "b")      = ["a", "b"]
     * ArrayUtils.add(["a", "b"], 3, "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param array1 the array to realAdd the element to, may be <code>null</code>
     * @param index  the position of the new object
     * @param array2 the array to realAdd
     * @return A new array containing the existing elements and the new element
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index > array.length).
     */
    public static <T> T[] add(T[] array1, int index, T[] array2) {
        Class clss;
        if (array1 != null) {
            clss = array1.getClass().getComponentType();
        } else if (array2 != null) {
            clss = array2.getClass().getComponentType();
        } else {
            return (T[]) new Object[]{null};
        }
        return (T[]) realAddArr(array1, index, array2, clss);
    }

    private static Object realAddArr(Object array1, int index, Object array2, Class clss) {
        if (array1 == null && array2 == null) {
            return null;
        }
        int len1 = getLength(array1);
        int len2 = getLength(array2);
        if (len1 == 0) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", array1 Length: 0");
            }
            return realCopy(array2);
        }
        if (len2 == 0) {
            return realCopy(array1);
        }
        if (index > len1 || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", array1 Length: " + len1);
        }
        Object joinedArray = Array.newInstance(array1.getClass().getComponentType(), len1 + len2);
        if (index == len1) {
            System.arraycopy(array1, 0, joinedArray, 0, len1);
            System.arraycopy(array2, 0, joinedArray, len1, len2);
        } else if (index == 0) {
            System.arraycopy(array2, 0, joinedArray, 0, len2);
            System.arraycopy(array1, 0, joinedArray, len2, len1);
        } else {
            System.arraycopy(array1, 0, joinedArray, 0, index);
            System.arraycopy(array2, 0, joinedArray, index, len2);
            System.arraycopy(array1, index, joinedArray, index + len2, len1 - index);
        }
        return joinedArray;
    }

    /**
     * <p>Inserts the specified element at the specified position in the array.
     * Shifts the element currently at that position (if any) and any subsequent
     * elements to the right (adds one to their indices).</p>
     *
     * <p>This method returns a new array with the same elements of the input
     * array plus the given element on the specified position. The component
     * type of the returned array is always the same as that of the input
     * array.</p>
     *
     * <p>If the input array is <code>null</code>, a new one element array is returned
     * whose component type is the same as the element.</p>
     *
     * <pre>
     * ArrayUtils.add(null, 0, null)      = [null]
     * ArrayUtils.add(null, 0, "a")       = ["a"]
     * ArrayUtils.add(["a"], 1, null)     = ["a", null]
     * ArrayUtils.add(["a"], 1, "b")      = ["a", "b"]
     * ArrayUtils.add(["a", "b"], 3, "c") = ["a", "b", "c"]
     * </pre>
     *
     * @param array   the array to realAdd the element to, may be <code>null</code>
     * @param index   the position of the new object
     * @param element the object to realAdd
     * @return A new array containing the existing elements and the new element
     * @throws IndexOutOfBoundsException if the index is out of range
     *                                   (index < 0 || index > array.length).
     */
    public static <T> T[] add(T[] array, int index, T element) {
        Class clss;
        if (array != null) {
            clss = array.getClass().getComponentType();
        } else if (element != null) {
            clss = element.getClass();
        } else {
            return (T[]) new Object[]{null};
        }
        return (T[]) realAdd(array, index, element, clss);
    }


    private static Object realAdd(Object array, int index, Object element, Class clss) {
        if (array == null) {
            if (index != 0) {
                throw new IndexOutOfBoundsException("Index: " + index + ", Length: 0");
            }
            Object joinedArray = Array.newInstance(clss, 1);
            Array.set(joinedArray, 0, element);
            return joinedArray;
        }
        int length = Array.getLength(array);
        if (index > length || index < 0) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Length: " + length);
        }
        Object result = Array.newInstance(clss, length + 1);
        System.arraycopy(array, 0, result, 0, index);
        Array.set(result, index, element);
        if (index < length) {
            System.arraycopy(array, index, result, index + 1, length - index);
        }
        return result;
    }


}
