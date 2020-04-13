import java.time.ZonedDateTime;

/**
 * @author jaclon
 * @date 2020/4/12
 */
public class T2 {

    public static void main(String[] args) {
        // 默认时区
        ZonedDateTime now = ZonedDateTime.now();
        System.out.println(now);
    }
}
