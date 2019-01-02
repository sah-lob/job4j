package ru.job4j.department;

import java.util.*;


public class Department {

    /**
     * Сортировка массива по возрастанию.
     * @param mas массив.
     */
    public String[] ascendingSort(String[] mas) {
        return masToSet(arrayToMas(addElement(masToArray(mas)))).toArray(new String[0]);
    }

    /**
     * Сортировка массива по убыванию
     * @param mas массив.
     */
    public String[] descendingSort(String[] mas) {
        ArrayList<ArrayList<String>> lists = addElement(masToArray(mas));
        lists.sort(new Comparator<ArrayList<String>>() {
            @Override
            public int compare(ArrayList<String> o1, ArrayList<String> o2) {
                int result = 0;
                for (int i = 0; i < Math.min(o1.size(), o2.size()); i++) {
                    result = o1.get(i).compareTo(o2.get(i));
                    result = -result;
                    if (result != 0) {
                        break;
                    }
                }
                if (result == 0) {
                    result = Integer.compare(o1.size(), o2.size());
                }
                return result;
            }
        });
        return arrayToMas(lists);
    }

    /**
     * Метод добавляет недостающие элементы в список.
     * @param lists список с элементами.
     */
    public ArrayList<ArrayList<String>> addElement(ArrayList<ArrayList<String>> lists) {
        ArrayList<String> newList;
        ArrayList<ArrayList<String>> listToAdd = new ArrayList<>();
        for (ArrayList<String> ll : lists) {
            newList = new ArrayList<>();
            if (ll.size() > 1) {
                for (int i = 0; i < ll.size() - 1; i++) {
                    newList.add(ll.get(i));
                }
                int nim = 0;
                for (ArrayList<String> qw : lists) {
                    if (!qw.equals(newList)) {
                        nim++;
                    }
                }
                if (nim == lists.size()) {
                    listToAdd.add(newList);
                    break;
                }
            }
        }
        lists.addAll(listToAdd);
        if (listToAdd.size() != 0) {
            lists = addElement(lists);
        }
        return lists;
    }

    /**
     * Преобразоывавает массив в список со значениями, представленными ввиде списка.
     * @param mas массив, который надо преобразовать.
     */
    public ArrayList<ArrayList<String>> masToArray(String[] mas) {
        ArrayList<ArrayList<String>> lists = new ArrayList<>();
        for (String s : mas) {
            lists.add(new ArrayList<>(Arrays.asList(s.split("\\\\"))));
        }
        return lists;
    }
    public TreeSet<String> masToSet(String[] mas) {
        TreeSet<String> set = new TreeSet<>();
        for (var n: mas) {
            set.add(n);
        }
        return set;
    }
    /**
     * Преобразовывает список в массив.
     * @param lists Список со значениями, представленными ввиде списка.
     */
    public String[] arrayToMas(ArrayList<ArrayList<String>> lists) {
        String[] mas = new String[lists.size()];
        for (int i = 0; i < lists.size(); i++) {
            String str = "";
            for (String s : lists.get(i)) {
                if (str.equals("")) {
                    str = s;
                } else {
                    str = str + "\\" + s;
                }
            }
            mas[i] = str;
        }
        return mas;
    }
}
