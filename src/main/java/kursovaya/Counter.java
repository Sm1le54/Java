package kursovaya;

import org.objectweb.asm.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import javafx.scene.control.Label;


/**
 * Класс Counter используется для анализа class-файлов.
 *
 * <p>Класс получает на вход путь до class-файла, считывает его байт-код
 * и выводит статистическую информацию, включая:
 * <ul>
 *     <li>Количество опкодов циклов</li>
 *     <li>Количество условных переходов</li>
 *     <li>Количество объявлений переменных</li>
 * </ul>
 *
 *  @author Prozorov Vladislav
 *  @version 1.0
 */
public class Counter {
    private static int loopCount;
    private static int conditionalCount;
    private static int variableCount;



    /**
     * Конструктор класса {@code Counter}.
     *
     * <p>Инициализирует счетчики: количество циклов, условных переходов
     * и объявлений переменных.
     */
    public Counter() {
        loopCount = 0;
        conditionalCount = 0;
        variableCount = 0;
    }

    /**
     * Выполняет анализ файла и отображает результаты.
     *
     * <p>Метод считывает байт-код из указанного файла, анализирует его
     * и выводит результаты анализа в переданный {@link Label}.
     *
     * @param file входной class-файл для анализа
     * @param resultsLabel элемент {@link Label} для отображения результатов анализа
     * @throws IOException если файл не найден или произошла ошибка ввода-вывода
     */
    public static void display(File file, Label resultsLabel) throws IOException {

        loopCount = 0;
        conditionalCount = 0;
        variableCount = 0;


        analyze(file);
        String results = String.format("Объявления переменных: %d\nУсловных переходов: %d\nКоличество опкодов циклов: %d",
                getVariableCount(), getConditionalCount(), getLoopCount());
        resultsLabel.setText(results);
    }

    /**
     * Выполняет анализ байт-кода class-файла.
     *
     * <p>Считывает байт-код из указанного файла и анализирует его с использованием
     *
     * @param file входной class-файл
     * @throws IOException если файл не найден или произошла ошибка ввода-вывода
     */
    public static void analyze(File file) throws IOException {

        try (FileInputStream fis = new FileInputStream(file)) {
            ClassReader reader = new ClassReader(fis);
            reader.accept(new ClassVisitor(Opcodes.ASM9) {


                /**
                 * Вызывается для каждого метода в class-файле.
                 *
                 * @param access модификаторы доступа метода
                 * @param name имя метода
                 * @param desc дескриптор метода (тип аргументов и возвращаемого значения)
                 * @param signature дженерик-сигнатура метода (если есть)
                 * @param exceptions список исключений, которые метод может бросать
                 * @return экземпляр {@link MethodVisitor} для дальнейшего анализа методов
                 */
                @Override
                public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                    return new MethodVisitor(Opcodes.ASM9) {

                        /**
                         * Вызывается для каждой инструкции, связанной с локальной переменной.
                         *
                         * @param opcode код операции (opcode) инструкции
                         * @param var индекс локальной переменной
                         */
                        @Override
                        public void visitVarInsn(int opcode, int var) {

                            if (opcode == Opcodes.ILOAD || opcode == Opcodes.LLOAD || opcode == Opcodes.FLOAD || opcode == Opcodes.DLOAD ||
                                    opcode == Opcodes.ISTORE || opcode == Opcodes.LSTORE || opcode == Opcodes.FSTORE || opcode == Opcodes.DSTORE ||
                                    opcode == Opcodes.ASTORE) {
                                variableCount++;
                            }
                        }


                        /**
                         * Вызывается для каждой инструкции перехода.
                         *
                         * @param opcode код операции инструкции перехода
                         * @param label метка (Label) в байт-коде, на которую происходит переход
                         */
                        public void visitJumpInsn(int opcode, org.objectweb.asm.Label label) {

                            super.visitJumpInsn(opcode, label);

                            if (opcode == Opcodes.GOTO) {
                                loopCount++;
                            }
                            if (opcode == Opcodes.IFEQ || opcode == Opcodes.IFNE || opcode == Opcodes.IFLT ||
                                    opcode == Opcodes.IFGE || opcode == Opcodes.IFGT || opcode == Opcodes.IFLE ||
                                    opcode == Opcodes.IF_ICMPEQ || opcode == Opcodes.IF_ICMPNE ||
                                    opcode == Opcodes.IF_ICMPLT || opcode == Opcodes.IF_ICMPGE ||
                                    opcode == Opcodes.IF_ICMPGT || opcode == Opcodes.IF_ICMPLE ||
                                    opcode == Opcodes.IF_ACMPEQ || opcode == Opcodes.IF_ACMPNE ||
                                    opcode == Opcodes.IFNULL || opcode == Opcodes.IFNONNULL) {
                                conditionalCount++;
                            }
                        }
                    };
                }
            }, 0);
        }
    }


    /**
     * Возвращает количество опкодов циклов.
     *
     * @return количество опкодов циклов
     */
    public static int getLoopCount() {
        return loopCount;
    }

    /**
     * Устанавливает количество опкодов циклов (используется для тестирования).
     *
     * @param number новое значение количества опкодов циклов
     */
    public void setLoopCount(int number) {
        loopCount = number;
    }


    /**
     * Возвращает количество условных опкодов.
     *
     * @return количество условных опкодов
     */
    public static int getConditionalCount() {
        return conditionalCount;
    }

    /**
     * Устанавливает количество условных опкодов (используется для тестирования).
     *
     * @param number новое значение количества условных опкодов
     */
    public void setConditionalCount(int number) {
        conditionalCount = number;
    }


    /**
     * Возвращает количество объявлений переменных.
     *
     * @return количество объявлений переменных
     */
    public static int getVariableCount() {
        return variableCount;
    }

    /**
     * Устанавливает количество объявлений переменных (используется для тестирования).
     *
     * @param number новое значение количества объявлений переменных
     */
    public void setVariableCount(int number) {
        variableCount = number;
    }
}

