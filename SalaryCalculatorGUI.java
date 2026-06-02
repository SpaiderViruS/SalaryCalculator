import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;

public class SalaryCalculatorGUI extends JFrame {

    // ЗП
    private JTextField salaryField;
    // Премия
    private JTextField bonusField;
    // Районный коэфицент
    private JTextField coefficientField;
    // Кол - во отработанных днец
    private JTextField daysField;
    // Фактически отработанных дней
    private JTextField workedDaysField;
    // К выплате
    private JLabel resultLabel;

    public SalaryCalculatorGUI() {
        setTitle("Зарплатный калькулятор");
        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(7, 2, 10, 10));

        panel.add(new JLabel("Оклад:"));
        salaryField = new JTextField();
        panel.add(salaryField);

        panel.add(new JLabel("Премия (%):"));
        bonusField = new JTextField();
        panel.add(bonusField);

        panel.add(new JLabel("Районный коэффициент (%):"));
        coefficientField = new JTextField();
        panel.add(coefficientField);

        panel.add(new JLabel("Рабочих дней:"));
        daysField = new JTextField();
        panel.add(daysField);

        panel.add(new JLabel("Отработано дней:"));
        workedDaysField = new JTextField();
        panel.add(workedDaysField);

        JButton calculateButton = new JButton("Рассчитать");
        panel.add(calculateButton);

        resultLabel = new JLabel("Результат: ");
        panel.add(resultLabel);

        calculateButton.addActionListener(this::calculateSalary);

        add(panel);
    }

    private void calculateSalary(ActionEvent e) {
        try {
            double salary = Double.parseDouble(salaryField.getText());
            double bonusPercent = Double.parseDouble(bonusField.getText());
            double coefficientPercent = Double.parseDouble(coefficientField.getText());
            int totalDays = Integer.parseInt(daysField.getText());
            int workedDays = Integer.parseInt(workedDaysField.getText());

            double baseSalary = salary / totalDays * workedDays;
            double bonus = baseSalary * bonusPercent / 100;
            double coefficient = baseSalary * coefficientPercent / 100;

            double total = baseSalary + bonus + coefficient;
            // НДФЛ - 13%
            double tax = total * 0.13;
            double finalSalary = total - tax;

            resultLabel.setText("К выплате: " + String.format("%.2f", finalSalary) + " руб.");

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "Введите корректные данные!");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new SalaryCalculatorGUI().setVisible(true);
        });
    }
}