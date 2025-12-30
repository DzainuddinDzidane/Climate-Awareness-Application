import javax.swing.*;

public class CARBON_RESULT_GEN {
    public static String lastResult = "No Data";

    CARBON_RESULT_GEN(double ebill, double gbill, double oil, double car, double f1, double f2, boolean y1, boolean y2) {
        ebill *= 105;
        gbill *= 105;
        oil *= 105;
        car *= 0.79;
        f1 *= 1100;
        f2 *= 4400;
        double sum = ebill + gbill + oil + car + f1 + f2;
        if (!y1) {
            sum += 184;
        }
        if (!y2) {
            sum += 166;
        }
        String result = String.format("%.2f", sum);
        lastResult = result;
        JOptionPane.showMessageDialog(null, "Your Carbon Footprint is:\n" + result, "Your Carbon FootPrint", JOptionPane.PLAIN_MESSAGE);
    }
}
