package edu.arep.math;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Esta clase contiene pruebas unitarias para los métodos de la clase MathService.
 */
class MathServiceTest {

    /**
     * Prueba para el método linearSearch cuando el valor está presente en la lista.
     */
    @Test
    void testLinearSearchValuePresent() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "3";
        String expectedOutput = "{\"operation\":\"linearSearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"3\",\"output\":\"2\"}";
        assertEquals(expectedOutput, MathService.linearSearch(lista, value));
    }

    /**
     * Prueba para el método linearSearch cuando el valor no está presente en la lista.
     */
    @Test
    void testLinearSearchValueNotPresent() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "6";
        String expectedOutput = "{\"operation\":\"linearSearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"6\",\"output\":\"-1\"}";
        assertEquals(expectedOutput, MathService.linearSearch(lista, value));
    }

    /**
     * Prueba para el método linearSearch cuando el valor es el primer elemento de la lista.
     */
    @Test
    void testLinearSearchValueFirstElement() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "1";
        String expectedOutput = "{\"operation\":\"linearSearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"1\",\"output\":\"0\"}";
        assertEquals(expectedOutput, MathService.linearSearch(lista, value));
    }

    /**
     * Prueba para el método linearSearch cuando el valor es el último elemento de la lista.
     */
    @Test
    void testLinearSearchValueLastElement() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "5";
        String expectedOutput = "{\"operation\":\"linearSearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"5\",\"output\":\"4\"}";
        assertEquals(expectedOutput, MathService.linearSearch(lista, value));
    }

    /**
     * Prueba para el método binarySearch cuando el valor está presente en la lista.
     */
    @Test
    void testBinarySearchValuePresent() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "3";
        String expectedOutput = "{\"operation\":\"binarySearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"3\",\"output\":\"2\"}";
        assertEquals(expectedOutput, MathService.binarySearch(lista, value, 0, lista.length));
    }

    /**
     * Prueba para el método binarySearch cuando el valor no está presente en la lista.
     */
    @Test
    void testBinarySearchValueNotPresent() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "6";
        String expectedOutput = "{\"operation\":\"binarySearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"6\",\"output\":\"-1\"}";
        assertEquals(expectedOutput, MathService.binarySearch(lista, value, 0, lista.length - 1));
    }
    /**
     * Prueba para el método binarySearch cuando el valor es el primer elemento de la lista.
     */
    @Test
    void testBinarySearchValueFirstElement() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "1";
        String expectedOutput = "{\"operation\":\"binarySearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"1\",\"output\":\"0\"}";
        assertEquals(expectedOutput, MathService.binarySearch(lista, value, 0, lista.length - 1));
    }

    /**
     * Prueba para el método binarySearch cuando el valor es el último elemento de la lista.
     */
    @Test
    void testBinarySearchValueLastElement() {
        String[] lista = {"1", "2", "3", "4", "5"};
        String value = "5";
        String expectedOutput = "{\"operation\":\"binarySearch\",\"inputlist\":\"1,2,3,4,5\", \"value\":\"5\",\"output\":\"4\"}";
        assertEquals(expectedOutput, MathService.binarySearch(lista, value, 0, lista.length - 1));
    }
}
