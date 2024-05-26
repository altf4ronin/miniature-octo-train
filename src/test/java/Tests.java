package org.example;
import org.junit.jupiter.api.*;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
class Tests {
    private Main mainProcessor;
    // Метод, вызываемый перед каждым тестом, который создает объект Main с тестовыми данными
    @BeforeEach
    void setUp() throws IOException {
        Path filePath = Paths.get("src/main/resources/test_numbers.txt");
        List<String> lines = List.of("1 4 2 3"); // Тестовые данные
        Files.write(filePath, lines); // Записываем тестовые данные в файл
        mainProcessor = new Main(filePath.toString()); // Создаем объект Main с путем к файлу
    }
    // Тест для метода findMin
    @Test
    void testFindMin() {
        assertEquals(new BigInteger("1"), mainProcessor.findMin()); // Проверяем, что минимальное число 1
    }
    // Тест для метода findMax
    @Test
    void testFindMax() {
        assertEquals(new BigInteger("4"), mainProcessor.findMax()); // Проверяем, что максимальное число 4
    }
    // Тест для метода getSum
    @Test
    void testGetSum() {
        assertEquals(new BigInteger("10"), mainProcessor.getSum()); // Проверяем, что сумма чисел равна 10
    }
    // Тест для метода getMult
    @Test
    void testGetMult() {
        assertEquals(new BigInteger("24"), mainProcessor.getMult()); // Проверяем, что произведение чисел равно 24
    }
    // Тест, проверяющий выброс исключения при отсутствии файла
    @Test
    void testException() {
        assertThrows(IOException.class, () -> {
            new Main("non_existent_file.txt"); // Проверка, что выбрасывается исключение IOException
        });
    }
    // Тест на время выполнения метода getMult
    @Test
    @Timeout(5)
    void testTimeout() throws IOException {
        int size = 50000; // Количество чисел для теста
        String filePath = "src/main/resources/time_test.txt";
        generateLargeNumberFile(filePath, size); // Генерация большого файла
        Main processor = new Main(filePath);
        assertTimeoutPreemptively(Duration.ofSeconds(5), () -> {
            processor.getMult(); // Проверяем, что метод выполняется не более 5 секунд
        });
    }
    // Метод для генерации файла с числами от 1 до size
    private void generateLargeNumberFile(String filePath, int size) throws IOException {
        List<String> lines = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < size; i++) {
            sb.append(i + 1).append(" "); // Генерируем числа от 1 до size
        }
        lines.add(sb.toString().trim());
        Files.write(Paths.get(filePath), lines); // Записываем строки в файл
    }
    // Тест для метода getSum, проверяющий производительность
    @Test
    void testPerformanceGetSum() throws IOException {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000}; // Массив размеров для тестирования
        long[] times = new long[sizes.length]; // Массив для хранения времени выполнения
        for (int i = 0; i < sizes.length; i++) {
            String filePath = "src/main/resources/time_test_" + sizes[i] + ".txt";
            generateLargeNumberFile(filePath, sizes[i]); // Генерация файла с заданным количеством чисел
            Main processor = new Main(filePath);

            long startTime = System.nanoTime(); // Записываем начальное время
            processor.getSum(); // Вызываем метод getSum
            long endTime = System.nanoTime(); // Записываем конечное время
            times[i] = endTime - startTime; // Сохраняем время выполнения
        }
        Path resultFile = Paths.get("src/main/resources/time_results_sum.txt");
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < sizes.length; i++) {
            resultBuilder.append(sizes[i]).append(" ").append(times[i]).append("\n"); // Форматируем результаты
        }
        Files.write(resultFile, resultBuilder.toString().getBytes()); // Записываем результаты в файл
    }
    // Тест для метода findMin, проверяющий производительность
    @Test
    void testPerformanceFindMin() throws IOException {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000}; // Массив размеров для тестирования
        long[] times = new long[sizes.length]; // Массив для хранения времени выполнения

        for (int i = 0; i < sizes.length; i++) {
            String filePath = "src/main/resources/time_test_" + sizes[i] + ".txt";
            generateLargeNumberFile(filePath, sizes[i]); // Генерация файла с заданным количеством чисел
            Main processor = new Main(filePath);

            long startTime = System.nanoTime(); // Записываем начальное время
            processor.findMin(); // Вызываем метод findMin
            long endTime = System.nanoTime(); // Записываем конечное время
            times[i] = endTime - startTime; // Сохраняем время выполнения
        }
        Path resultFile = Paths.get("src/main/resources/time_results_min.txt");
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < sizes.length; i++) {
            resultBuilder.append(sizes[i]).append(" ").append(times[i]).append("\n"); // Форматируем результаты
        }
        Files.write(resultFile, resultBuilder.toString().getBytes());
    }
    // Тест для метода findMax, проверяющий производительность
    @Test
    void testPerformanceFindMax() throws IOException {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000}; // Массив размеров для тестирования
        long[] times = new long[sizes.length]; // Массив для хранения времени выполнения
        for (int i = 0; i < sizes.length; i++) {
            String filePath = "src/main/resources/time_test_" + sizes[i] + ".txt";
            generateLargeNumberFile(filePath, sizes[i]); // Генерация файла с заданным количеством чисел
            Main processor = new Main(filePath);

            long startTime = System.nanoTime(); // Записываем начальное время
            processor.findMax(); // Вызываем метод findMax
            long endTime = System.nanoTime(); // Записываем конечное время
            times[i] = endTime - startTime; // Сохраняем время выполнения
        }
        Path resultFile = Paths.get("src/main/resources/time_results_max.txt");
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < sizes.length; i++) {
            resultBuilder.append(sizes[i]).append(" ").append(times[i]).append("\n"); // Форматируем результаты
        }
        Files.write(resultFile, resultBuilder.toString().getBytes()); // Записываем результаты в файл
    }
    // Тест для метода getMult, проверяющий производительность
    @Test
    void testPerformanceGetMult() throws IOException {
        int[] sizes = {1000, 10000, 100000, 500000, 1000000}; // Массив размеров для тестирования
        long[] times = new long[sizes.length]; // Массив для хранения времени выполнения
        for (int i = 0; i < sizes.length; i++) {
            String filePath = "src/main/resources/time_test_" + sizes[i] + ".txt";
            generateLargeNumberFile(filePath, sizes[i]); // Генерация файла с заданным количеством чисел
            Main processor = new Main(filePath);
            long startTime = System.nanoTime(); // Записываем начальное время
            processor.getMult(); // Вызываем метод getMult
            long endTime = System.nanoTime(); // Записываем конечное время
            times[i] = endTime - startTime; // Сохраняем время выполнения
        }
        Path resultFile = Paths.get("src/main/resources/time_results_mult.txt");
        StringBuilder resultBuilder = new StringBuilder();
        for (int i = 0; i < sizes.length; i++) {
            resultBuilder.append(sizes[i]).append(" ").append(times[i]).append("\n"); // Форматируем результаты
        }
        Files.write(resultFile, resultBuilder.toString().getBytes()); // Записываем результаты в файл
    }
}
