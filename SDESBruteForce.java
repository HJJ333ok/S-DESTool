import java.util.*;
import java.time.Duration;
import java.time.Instant;

public class SDESBruteForce {

    public static void main(String[] args) {
        // 明文和密文对
        String plaintext = "10101010";
        String ciphertext = "11101011";

        Instant start = Instant.now();
        List<String> keys = bruteForce(plaintext, ciphertext);
        Instant finish = Instant.now();

        if (!keys.isEmpty()) {
            System.out.println("The keys are: ");
            for (String key : keys) {
                System.out.println(key);
            }
            long timeElapsed = Duration.between(start, finish).toMillis();  //in millis
            System.out.println("Time taken: " + timeElapsed + " milliseconds");
        } else {
            System.out.println("No keys found");
        }
    }

    public static List<String> bruteForce(String plaintext, String ciphertext) {
        List<String> keys = new ArrayList<>();
        // 假设密钥是10位的
        for (int i = 0; i < Math.pow(2, 10); i++) {
            String key = String.format("%10s", Integer.toBinaryString(i)).replace(' ', '0');
            String result = SDES.encrypt(plaintext, key);
            if (result.equals(ciphertext)) {
                keys.add(key);
            }
        }
        return keys;
    }
}