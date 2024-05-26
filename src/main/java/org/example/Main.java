package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigInteger;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
public class Main {
    private List<BigInteger> numbers;
    // Считываем числа из файла и сохраняем в список
    public Main(String filePath) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            numbers = Stream.of(br.readLine().split(" ")) // Читаем строку и делим по пробелам
                    .map(BigInteger::new) // Преобразуем в BigInteger
                    .collect(Collectors.toList()); // Сохраняем в список
        }
    }
    // Находим минимальное число
    public BigInteger findMin() {
        return numbers.stream().min(BigInteger::compareTo).orElse(BigInteger.ZERO); // Возвращаем минимум или 0
    }
    // Находим максимальное число
    public BigInteger findMax() {
        return numbers.stream().max(BigInteger::compareTo).orElse(BigInteger.ZERO); // Возвращаем максимум или 0
    }
    // Считаем сумму всех чисел
    public BigInteger getSum() {
        return numbers.stream().reduce(BigInteger.ZERO, BigInteger::add); // Возвращаем сумму или 0
    }
    // Считаем произведение всех чисел
    public BigInteger getMult() {
        return numbers.parallelStream() // Используем параллельный стрим
                .reduce(BigInteger.ONE, BigInteger::multiply); // Возвращаем произведение или 1
    }
}
