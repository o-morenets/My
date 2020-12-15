
package stackOverflow.toBinaryString;

public class ToBinaryString {

    public static void main(String[] args) {
        byte aByte = 3;
        String byteAsString = Integer.toBinaryString(aByte & 0xff | 0x100).substring(1);
        System.out.println(byteAsString);

        byte[] data = {127,126,125};

        for (byte datum : data) {
            String bitsString = Integer.toBinaryString(datum & 0xff | 0x100).substring(1);
            System.out.println(bitsString); //Напечатайте, или сделайте с битами что-то ещё
        }
    }
}
