import matplotlib.pyplot as plt

with open("measurement.txt") as f:
    number_of_elements = []
    prepare_duration = []
    execute_duration = []
    number_of_tests = 19

    for i in range(number_of_tests):
        number_of_elements.append(int(f.readline().split(":")[1]))

        prepare_duration.append(
            [int(f.readline().split(":")[1]), int(f.readline().split(":")[1]), int(f.readline().split(":")[1])]
        )

        f.readline()
        f.readline()

        execute_duration.append(
            [int(f.readline().split(":")[1]), int(f.readline().split(":")[1]), int(f.readline().split(":")[1])]
        )

        f.readline()

    print(number_of_elements)
    print(prepare_duration)
    print(execute_duration)

    fig, ax = plt.subplots()

    ax.plot(number_of_elements, [x[0] for x in execute_duration], label="brute")
    ax.plot(number_of_elements, [x[1] for x in execute_duration], label="map")
    ax.plot(number_of_elements, [x[2] for x in execute_duration], label="tree")

    ax.legend(loc='upper left', frameon=True)
    ax.grid(visible=True)

    plt.title("Время работы алгоритмов")
    plt.xlabel("Число прямоугольников и точек")
    plt.ylabel("Время, нс")
    plt.yscale(value="log")

    plt.show()
