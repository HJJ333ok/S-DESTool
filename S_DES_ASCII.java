import java.util.Scanner;

public class S_DES_ASCII {
    private static String key;
    private static String key1;
    private static String key2;

    public static int[] IP = new int[] { 2, 6, 3, 1, 4, 8, 5, 7 };
    public static int[] EP = new int[] { 4, 1, 2, 3, 2, 3, 4, 1 };
    public static int[] P10 = new int[] { 3, 5, 2, 7, 4, 10, 1, 9, 8, 6 };
    public static int[] P8 = new int[] { 6, 3, 7, 4, 8, 5, 10, 9 };
    public static int[] P4 = new int[] { 2, 4, 3, 1 };
    public static int[] IP_1 = new int[] { 4, 1, 3, 5, 7, 2, 8, 6 };
    public static String[][] S1_box = new String[][] {
            { "01", "00", "11", "10" }, { "11", "10", "01", "00" },
            { "00", "10", "01", "11" }, { "11", "01", "00", "10" } };
    public static String[][] S2_box = new String[][] {
            { "00", "01", "10", "11" }, { "10", "00", "01", "11" },
            { "11", "10", "01", "00" }, { "10", "01", "00", "11" } };

    public static void getkey() {
        Scanner sc = new Scanner(System.in);
        System.out.println("-----请输入10位二进制密钥-----");
        key = sc.nextLine();
        // 生成子密钥
        key1 = key.substring(0, 8);
        key2 = key.substring(2, 10);
    }

    public static String substitute(String input, int[] table) {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            output.append(input.charAt(table[i] - 1));
        }
        return output.toString();
    }

    public static String xor(String a, String b) {
        StringBuilder result = new StringBuilder();
        for (int i = 0; i < a.length(); i++) {
            result.append(a.charAt(i) ^ b.charAt(i));
        }
        return result.toString();
    }

    public static String searchSbox(String input, int boxNumber) {
        int row = Integer.parseInt(input.substring(0, 1) + input.substring(3, 4), 2);
        int col = Integer.parseInt(input.substring(1, 3), 2);
        return boxNumber == 1 ? S1_box[row][col] : S2_box[row][col];
    }

    public static String encrypt(String plaintext) {
        // 加密操作
        String ciphertext = "";
        for (int i = 0; i < plaintext.length(); i++) {
            char c = plaintext.charAt(i);
            String binary = Integer.toBinaryString(c);
            while (binary.length() < 8) {
                binary = "0" + binary;
            }
            String L = binary.substring(0, 4);
            String R = binary.substring(4, 8);
            String temp = R;
            R = substitute(R, EP);
            R = xor(R, key1);
            String S1_output = searchSbox(R.substring(0, 4), 1);
            String S2_output = searchSbox(R.substring(4, 8), 2);
            R = substitute(S1_output + S2_output, P4);
            R = xor(R, L);
            L = temp;
            ciphertext += L + R;
        }
        return ciphertext;
    }

    public static String decrypt(String ciphertext) {
        // 解密操作
        String plaintext = "";
        for (int i = 0; i < ciphertext.length(); i++) {
            String block = ciphertext.substring(i, i + 8);
            String L = block.substring(0, 4);
            String R = block.substring(4, 8);
            String temp = R;
            R = substitute(R, EP);
            R = xor(R, key2);
            String S1_output = searchSbox(R.substring(0, 4), 1);
            String S2_output = searchSbox(R.substring(4, 8), 2);
            R = substitute(S1_output + S2_output, P4);
            R = xor(R, L);
            L = temp;
            plaintext += L + R;
        }
        return plaintext;
    }

    public static void main(String[] args) {
        getkey();
        Scanner sc = new Scanner(System.in);
        System.out.println("-----请输入要加密的信息(ASCII编码字符串)-----");
        String plaintext = sc.nextLine();
        String ciphertext = encrypt(plaintext);
        System.out.println("加密后的结果：" + ciphertext);
        String decryptedText = decrypt(ciphertext);
        System.out.println("解密后的结果：" + decryptedText);
    }
}