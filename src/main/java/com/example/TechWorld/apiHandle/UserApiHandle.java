package com.example.TechWorld.apiHandle;


import com.example.TechWorld.configuration.JwtUtils;
import com.example.TechWorld.dto.request.LoginRequest;
import com.example.TechWorld.dto.response.JwtResponese;
import com.example.TechWorld.dto.response.MessageResponse;
import com.example.TechWorld.dto.response.SignUpRequest;
import com.example.TechWorld.model.Cart;
import com.example.TechWorld.model.Role;
import com.example.TechWorld.model.User;
import com.example.TechWorld.repository.CartRepository;
import com.example.TechWorld.repository.RoleRepository;
import com.example.TechWorld.repository.UserRepository;
import com.example.TechWorld.service.SendMailService;
import com.example.TechWorld.service.implement.UserDetailsImpl;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/auth")
@Slf4j
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserApiHandle {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    CartRepository cartRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    SendMailService sendMailService;

    @Autowired
    private RestTemplate restTemplate;

    private static final String URL_UPLOAD = "https://api.cloudinary.com/v1_1/martfury/image/upload";


    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Validated @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponese(jwt, userDetails.getId(), userDetails.getName(),
                userDetails.getEmail(), userDetails.getPassword(), userDetails.getPhone(), userDetails.getAddress(),
                userDetails.getGender(), userDetails.getStatus(), userDetails.getImage(), userDetails.getRegisterDate(),
                roles));

    }

    @GetMapping("email/{email}")
    public ResponseEntity<User> getOneByEmail(@PathVariable("email") String email) {
        if (userRepository.existsByEmail(email)) {
            return ResponseEntity.ok(userRepository.findByEmail(email).get());
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Validated @RequestBody SignUpRequest signupRequest) {

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is already taken!"));
        }

        if (userRepository.existsByEmail(signupRequest.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Error: Email is alreadv in use!"));
        }



        // create new user account
        User user = new User(signupRequest.getName(), signupRequest.getEmail(),
                passwordEncoder.encode(signupRequest.getPassword()), signupRequest.getPhone(),
                signupRequest.getAddress(), signupRequest.getGender(), signupRequest.getStatus(),
                signupRequest.getImage(), signupRequest.getRegisterDate(),
                jwtUtils.doGenerateToken(signupRequest.getEmail()));

        Set<Role> roles = new HashSet<>();
        roles.add(new Role(1, null));

        user.setRoles(roles);
        userRepository.save(user);
        Cart c = new Cart(0L, 0.0, user.getAddress(), user.getPhoneNumber(), user);
        cartRepository.save(c);
        return ResponseEntity.ok(new MessageResponse("Đăng kí thành công"));

    }

    @GetMapping("/logout")
    public ResponseEntity<Void> logout() {
        return ResponseEntity.ok().build();
    }

    @PostMapping(value = "/upload-file", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        String url = "https://api.cloudinary.com/v1_1/martfury/image/upload";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);
        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", file.getResource());
        body.add("upload_preset", "user-image");
        body.add("cloud_name", "martfury");
        HttpEntity<MultiValueMap<String, Object>> requestEntity = new HttpEntity<>(body, headers);
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        return ResponseEntity.ok(response.getBody());
    }

    @PostMapping("send-mail-forgot-password-token")
    public ResponseEntity<String> sendToken(@RequestBody String email) {

        if (!userRepository.existsByEmail(email)) {
            return ResponseEntity.notFound().build();
        }
        User user = userRepository.findByEmail(email).get();
        String token = user.getToken();
        sendMaiToken(email, token, "Reset mật khẩu");
        return ResponseEntity.ok().build();

    }

    public void sendMaiToken(String email, String token, String title) {
        String body = "\r\n" + "    <h2>Hãy nhấp vào link để thay đổi mật khẩu của bạn</h2>\r\n"
                + "    <a href=\"http://localhost:8080/forgot-password/" + token + "\">Đổi mật khẩu</a>";
        sendMailService.queue(email, title, body);
    }




}
