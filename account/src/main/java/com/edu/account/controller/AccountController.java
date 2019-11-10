package com.edu.account.controller;

import com.edu.account.domain.Address;
import com.edu.account.domain.Payment;
import com.edu.account.repository.AddressRepository;
import com.edu.account.repository.PaymentRepository;
import com.edu.account.vo.ResultVO;
import com.edu.account.domain.User;
import com.edu.account.repository.UserRepository;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;
//import com.netflix.zuul.ZuulFilter;
//import com.netflix.zuul.context.RequestContext;

@RestController
public class AccountController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    AddressRepository addressRepository;
    @Autowired
    PaymentRepository paymentRepository;

    @Value("${router.auth.url}")
    private String authUrl;

    @Value("${api.key}")
    private String apiKey;

    private Gson gson = new Gson();

    @Autowired
    RestTemplate restTemplate;

    @RequestMapping("/")
    public String test(){
//        RequestContext context = RequestContext.getCurrentContext();
//
//        context.getHeader("uid");
        return "test";
    }
    @RequestMapping("/getAccount/{id}")
    public User getAccount(@PathVariable long id){
        User user = userRepository.findUserById(id);
        return user;
    }

    @PostMapping("/addUser")
    public User addUser( @RequestBody User user){
        user = userRepository.save(user);
        return user;
    }
    @PostMapping("/addAddressByUser/{id}")
    public Address addAddress( @PathVariable long id,@RequestBody Address address){
        address = addressRepository.save(address);
        User user = userRepository.findUserById(id);
        user.setShippingAddress(address);
        userRepository.save(user);
        return address;
    }
    @PostMapping("/addPaymentByUser/{id}")
    public Payment addPayment( @PathVariable long id,@RequestBody Payment payment){
        payment = paymentRepository.save(payment);
        User user = userRepository.findUserById(id);
        user.setPayment(payment);
        userRepository.save(user);
        return payment;
    }

    @PostMapping("/login")
    public ResultVO<String> validateUser(@RequestBody User user){
        User result = userRepository.findUserByEmailAndPassword(user.getEmail(),user.getPassword());
        ResultVO<String> resultVO =  new ResultVO<String>();

        if(result==null){
            resultVO.setMsg("email or password error!");
            resultVO.setCode(-1);
        }else{
            resultVO.setCode(0);

            ResponseEntity<String> responseEntity = request(authUrl+"/create_token",gson.toJson(result));
            resultVO.setData(responseEntity.getBody());
        }
        return resultVO;
    }
    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable String id){
        User user = userRepository.findById(Long.valueOf(id)).get();
        return user;
    }

    private ResponseEntity<String> request(String authUrl, String data) {
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        headers.add("api-key", apiKey);
        //HttpEntity
        HttpEntity<String> formEntity = new HttpEntity<>(data, headers);
        return restTemplate.postForEntity(authUrl, formEntity, String.class);
    }
}
