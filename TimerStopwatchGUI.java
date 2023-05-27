import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TimerStopwatchGUI extends JFrame {
    private JLabel timeLabel;
    private Timer timer;
    private long startTime;
    private long elapsedTime;
    private boolean isTimerRunning;

    public TimerStopwatchGUI() {
        setTitle("Timer and Stopwatch");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 200);
        setLocationRelativeTo(null);

        timeLabel = new JLabel("00:00:00");
        timeLabel.setFont(new Font("Arial", Font.BOLD, 40));
        timeLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(timeLabel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        JButton startButton = new JButton("Start");
        JButton stopButton = new JButton("Stop");
        JButton resetButton = new JButton("Reset");
        JButton addTimerButton = new JButton("Add Timer");
        buttonPanel.add(startButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(resetButton);
        buttonPanel.add(addTimerButton);
        add(buttonPanel, BorderLayout.SOUTH);

        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                startTimer();
            }
        });

        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                stopTimer();
            }
        });

        resetButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                resetTimer();
            }
        });

        addTimerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addTimer();
            }
        });
    }

    private void startTimer() {
        if (!isTimerRunning) {
            startTime = System.currentTimeMillis() - elapsedTime;
            timer = new Timer(1000, new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    elapsedTime = System.currentTimeMillis() - startTime;
                    updateTimerLabel(elapsedTime);
                }
            });
            timer.start();
            isTimerRunning = true;
        }
    }

    private void stopTimer() {
        if (isTimerRunning) {
            timer.stop();
            elapsedTime = System.currentTimeMillis() - startTime;
            updateTimerLabel(elapsedTime);
            isTimerRunning = false;
        }
    }

    private void resetTimer() {
        if (!isTimerRunning) {
            elapsedTime = 0;
            updateTimerLabel(elapsedTime);
        }
    }

    private void addTimer() {
        if (!isTimerRunning) {
            int inputSeconds = Integer.parseInt(JOptionPane.showInputDialog(this, "Enter the duration in seconds:"));
            long addedTime = inputSeconds * 1000;
            elapsedTime += addedTime;
            updateTimerLabel(elapsedTime);
        }
    }

    private void updateTimerLabel(long elapsedTime) {
        int hours = (int) (elapsedTime / 3600000);
        int minutes = (int) ((elapsedTime % 3600000) / 60000);
        int seconds = (int) ((elapsedTime % 60000) / 1000);
        String timeText = String.format("%02d:%02d:%02d", hours, minutes, seconds);
        timeLabel.setText(timeText);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                TimerStopwatchGUI gui = new TimerStopwatchGUI();
                gui.setVisible(true);
            }
        });
    }
}