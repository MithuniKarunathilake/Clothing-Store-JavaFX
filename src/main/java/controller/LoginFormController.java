package controller;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Random;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class LoginFormController {

    @FXML
    private JFXPasswordField txtPassword;

    @FXML
    private JFXTextField txtUsername;

    @FXML
    private JFXTextField otpField;

    private String generatedOTP;

    @FXML
    void btnLoginOnAction(ActionEvent event) {
        String email = txtUsername.getText();
        String password = txtPassword.getText();

        if (email.equals("oliviarudd3000@gmail.com") && password.equals("Ngmsk@2003")) {
            try {
                Stage stage = new Stage();
                stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/dashboard_form.fxml"))));
                stage.show();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Invalid email or password.");
        }
    }

    @FXML
    void handleForgotPassword(ActionEvent event) {
        generatedOTP = generateOTP();
        sendOTPEmail("oliviarudd3000@gmail.com", generatedOTP);
        otpField.setVisible(true);
    }

    @FXML
    void handleVerifyOTP(ActionEvent event) {
        String enteredOTP = otpField.getText();
        if (enteredOTP.equals(generatedOTP)) {
            System.out.println("OTP verified successfully.");
            // Optionally, allow the user to reset their password here
        } else {
            System.out.println("Invalid OTP. Please try again.");
        }
    }

    private String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000); // 6-digit OTP
        return String.valueOf(otp);
    }

    private void sendOTPEmail(String to, String otp) {
        String from = "your_email@example.com";
        String host = "smtp.example.com";

        Properties properties = System.getProperties();
        properties.setProperty("mail.smtp.host", host);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication("your_email@example.com", "your_email_password");
            }
        });

        try {
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
            message.setSubject("OTP for Password Recovery");
            message.setText("Your OTP is: " + otp);

            Transport.send(message);
            System.out.println("OTP sent successfully.");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}
