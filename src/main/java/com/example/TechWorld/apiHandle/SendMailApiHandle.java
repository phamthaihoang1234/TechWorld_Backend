package com.example.TechWorld.apiHandle;


import com.example.TechWorld.repository.UserRepository;
import com.example.TechWorld.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("api/send-mail")
public class SendMailApiHandle {



    @Autowired
    UserRepository userRepository;

    @Autowired
    SendMailService sendMailService;

    @PostMapping("/otp")
    public ResponseEntity<Integer> sendOpt(@RequestBody String email) {
        int random_otp = (int) Math.floor(Math.random() * (999999 - 100000 + 1) + 100000);
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        sendMailOtp(email, random_otp, "Xác nhận tài khoản!");
        return ResponseEntity.ok(random_otp);
    }

    // sendmail
    public void sendMailOtp(String email, int Otp, String title) {
        String body = "<div>\r\n" + "        <h3>Mã OTP của bạn là: <span style=\"color:red; font-weight: bold;\">"
                + Otp + "</span></h3>\r\n" + "    </div>";
        System.out.println("sendMailOtp" + email + Otp + title);
        sendMailService.queue(email, title, body);
    }
}
