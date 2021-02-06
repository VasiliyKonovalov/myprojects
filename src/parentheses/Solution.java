package parentheses;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

public class Solution {
    public static void main(String[] args) throws IOException {
        String x = "public static void main(String[] args) {}(dff)";   // строка которую проверяем
        Parentheses(x);
        //comment
    }

    public static boolean Parentheses(String text) {                // метод проверяющий правильность закрытия скобок

        String string = text;
        StringBuilder builder = new StringBuilder(string);
        Stack<String> stack = new Stack<>();                       // скобки грузим в стэк
        stack.push("s");                                      // добавим один элемент в стэк изначально, иначе
        String Patern1 = new String("(");                   // если первой будет закрывающая скобка, её не с
        String Patern2 = new String("[");                   // чем будет сравнить
        String Patern3 = new String("{");
        String str1;
        String str2 = null;
        boolean bool = true;                                        // метод вернёт true/false
        int j = 0;                                                  // Прибавляем и отнимаем скобки в стэке
        char c1;

        for (int i = 0; i < builder.length(); i++) {                // крутимся в цикле(посимвольный проход строки)
            c1 = builder.charAt(i);
            str1 = String.valueOf(c1);
            if (str1.contains("(")) {                               // + в стэк
                stack.push(str1);
                j++;
            }
            if (str1.contains("[")) {                               // + в стэк
                stack.push(str1);
                j++;
            }
            if (str1.contains("{")) {                               // + в стэк
                stack.push(str1);
                j++;
            }
            if (str1.contains(")")) {                               // проверим патерн
                j--;
                str2 = stack.pop();
                System.out.println("(" + (str2.equals(Patern1)));
                if (str2.equals(Patern1)) {
                    bool = true;
                } else {
                    bool = false;
                    break;
                }
            }
            if (str1.contains("]")) {                               // проверим патерн
                j--;
                str2 = stack.pop();
                System.out.println("[" + str2.equals(Patern2));
                if (str2.equals(Patern2)) {
                    bool = true;
                } else {
                    bool = false;
                    break;
                }
            }
            if (str1.contains("}")) {                                // проверим патерн
                j--;
                str2 = stack.pop();
                System.out.println("{" + str2.equals(Patern3));
                if (str2.equals(Patern3)) {
                    bool = true;
                } else {
                    bool = false;
                    break;
                }
            }
        }
        if (j != 0) {                                                // проверяем есть ли открытая скобка
            bool = false;                                            // без соответствующего закрытия
        }
        System.out.println("Общий итог " + bool);
        return bool;

    }
}
