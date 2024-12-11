package kursovaya;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Тестовый класс для проверки корректности работы класса {@link Counter}.
 *
 * @author Prozorov Vladislav
 * @version 1.0
 */
public class CounterTest {
    /**
     * Экземпляр класса {@link Counter}, используемый для тестирования.
     */
    private Counter counter;


    /**
     * Метод, вызываемый перед каждым тестом для инициализации экземпляра класса {@link Counter}.
     */
    @BeforeEach
    public void setUp() {
        counter = new Counter();
    }

    /**
     * Тест для проверки default-значения {@link Counter#getLoopCount()}.
     *
     * @throws AssertionError если default-значение не равно 0
     */
    @Test
    public void testGetLoopCount_DefaultValue() {
        assertEquals(0, Counter.getLoopCount());
    }

    /**
     * Тест для проверки default-значения {@link Counter#getConditionalCount()}.
     *
     * @throws AssertionError если default-значение не равно 0
     */
    @Test
    public void testGetConditionalCount_DefaultValue() {
        assertEquals(0, Counter.getConditionalCount());
    }

    /**
     * Тест для проверки default-значения {@link Counter#getVariableCount()}.
     *
     * @throws AssertionError если default-значение не равно 0
     */
    @Test
    public void testGetVariableCount_DefaultValue() {
        assertEquals(0, Counter.getVariableCount());
    }

    /**
     * Тест для проверки корректности работы метода {@link Counter#setLoopCount(int)}.
     *
     * @throws AssertionError если значение не было установлено корректно
     */
    @Test
    public void testSetLoopCount() {
        counter.setLoopCount(10);
        assertEquals(10, Counter.getLoopCount());
    }

    /**
     * Тест для проверки корректности работы метода {@link Counter#setConditionalCount(int)}.
     *
     * @throws AssertionError если значение не было установлено корректно
     */
    @Test
    public void testSetConditionalCount() {
        counter.setConditionalCount(10);
        assertEquals(10, Counter.getConditionalCount());
    }

    /**
     * Тест для проверки корректности работы метода {@link Counter#setVariableCount(int)}.
     *
     * @throws AssertionError если значение не было установлено корректно
     */
    @Test
    public void testSetVariableCount() {
        counter.setVariableCount(10);
        assertEquals(10, Counter.getVariableCount());
    }

    /**
     * Тест для проверки корректности работы метода с null-файлом.
     *
     * @throws AssertionError если анализ файла не бросил исключение
     */
    @Test
    public void testAnalyze_NullFile() {
        assertThrows(NullPointerException.class, () -> Counter.analyze(null));
    }





}