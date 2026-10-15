package com.company.stream;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamTester {

    public static void main(String[] args) {
        StreamTester tester = new StreamTester();

        List<Integer> unsortedList = Arrays.asList(5, 3, 1, 3, 2, 5, 4);
        System.out.println(unsortedList);
        System.out.println(tester.distinctSortedDescending(unsortedList));
        printSeparator();

        List<Integer> nums = new ArrayList<>(Arrays.asList(5, 1, 3, 7, 2, 18, 11));
        System.out.println(nums);
        System.out.println(tester.squaresOfOddNumbers(nums));
        printSeparator();

        List<Integer> numbers = Arrays.asList(9, 8, 2, 6, 1);
        System.out.println(numbers);
        System.out.println(tester.skipAndLimit(numbers, 1, 2));
        printSeparator();

        List<Integer> originalList = Arrays.asList(9, 8, 2, 6, 1);
        System.out.println(originalList);
        tester.secondHighest(originalList).ifPresent(System.out::println);
        printSeparator();

        List<Integer> numbersList = Arrays.asList(1, 2, 3, 4, 5);
        System.out.println(tester.partitionByEven(numbersList));
        printSeparator();

        List<String> words = Arrays.asList("Pushkar", "Java Techie", "Happy Birthday to you", "Welcome to the jungle");
        tester.longestWord(words).ifPresent(System.out::println);
        printSeparator();

        List<Employee> employeeList = getEmployeeList();

        tester.firstEmployeeWithSalaryAbove(employeeList, 500000).ifPresent(System.out::println);
        printSeparator();

        System.out.println(tester.topEarners(employeeList, 2));
        printSeparator();

        System.out.println(tester.sortBySalaryThenName(employeeList));
        printSeparator();

        List<Integer> freq = Arrays.asList(9, 1, 2, 1, 4, 2, 1);
        System.out.println(tester.frequencies(freq));
        printSeparator();

        System.out.println(tester.countByDepartment(employeeList));
        printSeparator();

        System.out.println(tester.totalAmountByCategory(getTransactionList()));
        printSeparator();

        System.out.println(tester.averageSalaryByDepartment(employeeList));
        printSeparator();

        System.out.println(tester.highestPaidByDepartment(employeeList));
        printSeparator();

        System.out.println(tester.joinNames(employeeList));
        printSeparator();

        List<String> trafficList = Arrays.asList("Red", "Orange", "Green", "Black");
        List<String> rainbowList = Arrays.asList("Violet", "Indigo", "Blue", "Green", "Yellow", "Orange", "Red");
        System.out.println(tester.commonElements(trafficList, rainbowList));
        printSeparator();

        List<List<Integer>> listOfList = Arrays.asList(
                Arrays.asList(1, 3, 7, 11),
                Arrays.asList(2, 4, 6, 8),
                Arrays.asList(5, 10, 11, 13),
                List.of(1)
        );
        System.out.println(tester.flattenDistinctSorted(listOfList));
        printSeparator();

        tester.printNamesInParallel(employeeList);
        printSeparator();

        System.out.println(tester.collectInParallel(employeeList));
        printSeparator();

        System.out.println(tester.totalSalary(employeeList));
        printSeparator();
    }

    private static void printSeparator() {
        System.out.println("--------------");
    }

    private static List<Transaction> getTransactionList() {
        return Arrays.asList(
                new Transaction("Food", 100.0),
                new Transaction("Shopping", 900.0),
                new Transaction("Entertainment", 100.0),
                new Transaction("Entertainment", 400.0),
                new Transaction("Travel", 900.0),
                new Transaction("Travel", 200.0),
                new Transaction("Food", 200.0));
    }

    private static List<Employee> getEmployeeList() {
        Employee pushkar = new Employee(9, "Pushkar", "HR", 10000);
        Employee ramesh = new Employee(8, "Ramesh", "Sales", 20000);
        Employee pooja = new Employee(1, "Pooja", "Marketing", 60000);
        Employee vinod = new Employee(2, "Vinod", "IT", 70000);
        Employee ajay = new Employee(10, "Ajay", "IT", 60000);

        List<Employee> employeeList = new LinkedList<>();
        employeeList.add(pushkar);
        employeeList.add(ramesh);
        employeeList.add(pooja);
        employeeList.add(vinod);
        employeeList.add(ajay);
        return employeeList;
    }

    // Removes duplicates and sorts in descending order: [5, 3, 1, 3, 2, 5, 4] -> [5, 4, 3, 2, 1]
    public List<Integer> distinctSortedDescending(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    // Keeps odd numbers and squares them, preserving order: [5, 1, 3, 7, 2, 18, 11] -> [25, 1, 9, 49, 121]
    public List<Integer> squaresOfOddNumbers(List<Integer> numbers) {
        return numbers.stream()
                .filter(n -> n % 2 != 0)
                .map(n -> n * n)
                .toList();
    }

    // Skips the first n elements, then takes the next maxSize: ([9, 8, 2, 6, 1], 1, 2) -> [8, 2]
    public List<Integer> skipAndLimit(List<Integer> numbers, long n, long maxSize) {
        return numbers.stream()
                .skip(n)
                .limit(maxSize)
                .toList();
    }

    // Second largest distinct value: [9, 8, 2, 6, 1] -> 8; empty if fewer than two distinct values
    public Optional<Integer> secondHighest(List<Integer> numbers) {
        return numbers.stream()
                .distinct()
                .sorted(Comparator.reverseOrder())
                .skip(1)
                .findFirst();
    }

    // Splits into even (true) and odd (false): [1, 2, 3, 4, 5] -> {false=[1, 3, 5], true=[2, 4]}
    public Map<Boolean, List<Integer>> partitionByEven(List<Integer> numbers) {
        return numbers.stream()
                .collect(Collectors.partitioningBy(n -> n % 2 == 0));
    }

    // Longest string; on a tie, the last one wins (Stream.max behaviour)
    public Optional<String> longestWord(List<String> words) {
        return words.stream()
                .max(Comparator.comparing(String::length));
    }

    public Optional<Employee> firstEmployeeWithSalaryAbove(List<Employee> employees, double salary) {
        return employees.stream()
                .filter(e -> e.getSalary() > salary)
                .findFirst();
    }

    // The n highest paid employees, highest first
    public List<Employee> topEarners(List<Employee> employees, int n) {
        return employees.stream()
                .sorted(Comparator.comparingDouble(Employee::getSalary).reversed())
                .limit(n)
                .toList();
    }

    public List<Employee> sortBySalaryThenName(List<Employee> employees) {
        return employees.stream()
                .sorted(Comparator.comparing(Employee::getSalary).thenComparing(Employee::getEname))
                .toList();
    }

    // How often each value occurs: [9, 1, 2, 1, 4, 2, 1] -> {1=3, 2=2, 4=1, 9=1}
    public <T> Map<T, Long> frequencies(List<T> items) {
        return items.stream()
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
    }

    public Map<String, Long> countByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.counting()));
    }

    public Map<String, Double> totalAmountByCategory(List<Transaction> transactions) {
        return transactions.stream()
                .collect(Collectors.groupingBy(Transaction::getCategory, Collectors.summingDouble(Transaction::getAmount)));
    }

    public Map<String, Double> averageSalaryByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.averagingDouble(Employee::getSalary)));
    }

    public Map<String, Optional<Employee>> highestPaidByDepartment(List<Employee> employees) {
        return employees.stream()
                .collect(Collectors.groupingBy(Employee::getDepartment, Collectors.maxBy(Comparator.comparing(Employee::getSalary))));
    }

    // Employee names as a comma-separated string: "Pushkar,Ramesh,..."
    public String joinNames(List<Employee> employees) {
        return employees.stream()
                .map(Employee::getEname)
                .collect(Collectors.joining(","));
    }

    // Elements of first that also appear in second, in first's order
    public List<String> commonElements(List<String> first, List<String> second) {
        return first.stream()
                .filter(second::contains)
                .toList();
    }

    // Flattens nested lists, removes duplicates and sorts ascending
    public List<Integer> flattenDistinctSorted(List<List<Integer>> listOfLists) {
        return listOfLists.stream()
                .flatMap(Collection::stream)
                .distinct()
                .sorted()
                .toList();
    }

    // Print order is not guaranteed with a parallel stream
    public void printNamesInParallel(List<Employee> employees) {
        employees.parallelStream()
                .map(Employee::getEname)
                .forEach(System.out::println);
    }

    // forEach from several threads needs a thread-safe list; element order is not guaranteed
    public List<Employee> collectInParallel(List<Employee> employees) {
        List<Employee> result = Collections.synchronizedList(new ArrayList<>());

        employees.parallelStream()
                .forEach(result::add);

        return result;
    }

    public double totalSalary(List<Employee> employees) {
        return employees.parallelStream()
                .map(Employee::getSalary)
                .reduce(0.0, Double::sum);
    }
}
