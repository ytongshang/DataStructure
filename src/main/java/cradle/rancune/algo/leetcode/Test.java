package cradle.rancune.algo.leetcode;

import java.nio.ByteBuffer;
import java.util.Arrays;

/**
 * Created by Rancune@126.com on 2020/5/13.
 */
public class Test {
    public static void main(String[] args) {
        StringBuilder builder = new StringBuilder();
        builder.append("07")
                .append("00")
                .append("07E4")
                .append("05")
                .append("0D")
                .append("0F")
                .append("1E")
                .append("32");
        byte[] bytes = stringToBytes(builder.toString());
        System.out.println(Arrays.toString(bytes));
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.getShort());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());
        System.out.println(buffer.get());

        ByteBuffer buf = ByteBuffer.allocate(9);
        buf.put((byte) 7);
        buf.put((byte) 0);
        buf.putShort((short) 2020);
        buf.put((byte) 5);
        buf.put((byte) 13);
        buf.put((byte) 15);
        buf.put((byte) 30);
        buf.put((byte) 50);
        byte[] b = new byte[9];
        buf.flip();
        buf.get(b, 0, buf.limit());
        System.out.println(Arrays.toString(b));

        byte[] year = fromShort((short) 2020);
        System.out.println(Arrays.toString(year));

        test();
    }

    public static void test() {
        byte[] bytes = {7, 0, 7, -28, 5, 13, 15, 30, 50};
        ByteBuffer buffer = ByteBuffer.wrap(bytes);
        buffer.position(2);
        int year = buffer.getShort();
        int month = buffer.get();
        int day = buffer.get();
        int hour = buffer.get();
        int minute = buffer.get();
        int second = buffer.get();
    }

    public static String bytesToHexString(byte[] bytes) {
        StringBuilder result = new StringBuilder();
        for (byte aByte : bytes) {
            String hexString = Integer.toHexString(aByte & 0xFF);
            if (hexString.length() == 1) {
                hexString = '0' + hexString;
            }
            result.append(hexString.toUpperCase());
        }
        return result.toString();
    }

    public static byte[] stringToBytes(String text) {
        int len = text.length();
        byte[] bytes = new byte[(len + 1) / 2];
        for (int i = 0; i < len; i += 2) {
            int size = Math.min(2, len - i);
            String sub = text.substring(i, i + size);
            bytes[i / 2] = (byte) Integer.parseInt(sub, 16);
        }
        return bytes;
    }

    public static byte[] fromShort(short n) {
        return new byte[]{(byte) (n >>> 8), (byte) n};
    }


}
