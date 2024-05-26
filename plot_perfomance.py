import matplotlib.pyplot as plt
import matplotlib.ticker as ticker
# Функция для построения графика производительности
def plot_performance(file_name, title, output):
    sizes = []
    times = []
    # Чтение данных из файла
    with open(file_name) as file:
        for line in file:
            size, time = map(int, line.split())  # Разделение строки на размер и время
            sizes.append(size)
            times.append(time)
    # Создание графика
    plt.figure()
    plt.plot(sizes, times, marker='o')  # Построение графика с маркерами
    plt.title(title)  # Заголовок графика
    plt.xlabel('Number of Elements')  # Подпись оси X
    plt.ylabel('Time (nanoseconds)')  # Подпись оси Y
    plt.grid(True)  # Добавление сетки
    # Настройка меток оси X для отображения чисел с разделителями
    ax = plt.gca()
    ax.xaxis.set_major_formatter(ticker.FuncFormatter(lambda x, pos: '{:,.0f}'.format(x)))
    plt.savefig(output)  # Сохранение графика в файл
    plt.show()  # Отображение графика
# Построение графиков производительности для различных методов
plot_performance("src/main/resources/time_results_sum.txt", "Performance of getSum() Method", "src/main/resources/test_graph_sum.png")
plot_performance("src/main/resources/time_results_min.txt", "Performance of findMin() Method", "src/main/resources/test_graph_min.png")
plot_performance("src/main/resources/time_results_max.txt", "Performance of findMax() Method", "src/main/resources/test_graph_max.png")
plot_performance("src/main/resources/time_results_mult.txt", "Performance of getMult() Method", "src/main/resources/test_graph_mult.png")
