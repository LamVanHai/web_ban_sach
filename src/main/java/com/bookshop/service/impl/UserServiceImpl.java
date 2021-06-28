package com.bookshop.service.impl;

import com.bookshop.constant.Message;
import com.bookshop.constant.MyConstants;
import com.bookshop.dto.request.UserRequest;
import com.bookshop.dto.response.UserResponse;
import com.bookshop.entity.User;
import com.bookshop.entity.User_group;
import com.bookshop.mapper.UserMapper;
import com.bookshop.repository.*;
import com.bookshop.service.RoleService;
import com.bookshop.service.UserService;
import com.bookshop.util.CheckUtil;
import com.bookshop.util.RandomPassword;
import com.bookshop.util.ReflectionUtil;
import com.bookshop.util.SendMail;
import net.bytebuddy.utility.RandomString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private User_GroupRepository user_groupRepository;

    @Autowired
    private CheckUtil checkUtil;

    @Autowired
    private SendMail sendMail;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private DistrictRepository districtRepository;

    @Autowired
    private WardRepository wardRepository;

    @Override
    @Transactional
    public String save(UserRequest userRequest, HttpSession session) {
        User user = new User();
        if (userRequest != null) {
            if(userRequest.getId()==null){
                if (userRequest.getPhone_number() == null && userRequest.getEmail() != null && userRequest.getName() == null) {
                    user = userRepository.findByEmail(userRequest.getEmail());
                    Pattern p=Pattern.compile(MyConstants.EMAIL_PATTERN);
                    if(!p.matcher(userRequest.getEmail()).find()){
                        return Message.format_incorrect;//Định dạng gmail k đúng
                    }
                    if (user == null) {
                        return Message.gmail_not_exist;//không tìm thấy email
                    }
                    RandomPassword randomPassword = new RandomPassword();
                    String password = ReflectionUtil.enCryptPassword(randomPassword.getPassWord());
                    user.setPassword(password);
                    String success= sendMail.sendMailForGotPassword(user.getEmail(), "Mat khau moi cua ban la: " + randomPassword.getPassWord() + "\n Vui long thay doi mat khau de tai khoan cua ban duoc bao mat hon, Thankyou!", "BookStore gui toi ban mat khau moi.");
                    if(success==null){
                        return Message.error_system;
                    }
                }
                else {
                    if(checkUtil.checkEmail(userRequest.getEmail())){
                        return Message.email_exist;
                    }
                    else {
                        try {
                            ReflectionUtil.mapper(userRequest, user);
                            String password = ReflectionUtil.enCryptPassword(userRequest.getPassword());
                            user.setPassword(password);
                            String code= RandomString.make(64);
                            user.setVerification_Code(code);
                            if(user.getPhone_number()!=null){
                                user.setEnable(1);
                            }else {
                                user.setEnable(0);
                            }
                            if(userRequest.getAddress()==""){
                                user.setAddress(null);
                            }
                            if(userRequest.getPhone_number()==""){
                                user.setPhone_number(null);
                            }
                            user.setUser_group(user_groupRepository.findOne((long) 1));
                            if(userRequest.getRole()!=null){
                                if(userRequest.getRole().equals("user")){
                                    user.setRoles_user(roleService.findById((long) 2));
                                }else {
                                    user.setRoles_user(roleService.findById((long) 1));
                                }
                            }else {
                                user.setRoles_user(roleService.findById((long) 2));
                            }

                            sendMail.emailVerify(user,userRequest.getSiteURL());
                        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException | MessagingException | UnsupportedEncodingException e) {
                            e.printStackTrace();
                            return Message.error_system;
                        }
                    }

                }
            }
            else {
                user=userRepository.findOne(userRequest.getId());
                if(userRequest.getPassword()!=null&&userRequest.getConfirm_password()!=null&&userRequest.getCheckPassword()==null){
                    if(!sendMail.changePassword(userRequest.getPassword(),passwordEncoder,user.getPassword())){
                        return Message.incorrect_password;//mật khẩu không đúng
                    }
                }
                try {
                    ReflectionUtil.mapper(userRequest, user);
                } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                if (userRequest.getNew_password() != null&&userRequest.getNew_password()!="") {
                    String password = ReflectionUtil.enCryptPassword(userRequest.getNew_password());
                    user.setPassword(password);
                }
                User_group user_group = user_groupRepository.findOne((long) 1);
                if(userRequest.getRole()!=null){
                    if(userRequest.getRole().equals("user")){
                        user.setRoles_user(roleService.findById((long) 2));
                    }else {
                        user.setRoles_user(roleService.findById((long) 1));
                    }
                }
                user.setUser_group(user_group);
            }
        }
        session.setAttribute("nameSession",user.getName());
        session.setAttribute("emailSession",user.getEmail());
        User user1=userRepository.save(user);
        if(user1==null){
            return Message.error_system;
        }
        return Message.success;
    }

    @Override
    public List<UserResponse> findAll(Pageable pageable) {
        List<User> users = userRepository.findAll(pageable).getContent();
        List<UserResponse> userResponses = users.stream().map(UserMapper::mapToResponse).collect(Collectors.toList());

        return userResponses;
    }

    @Override
    public List<UserResponse> findByKeyWord(String keyWord, Pageable pageable) {
        List<User> users = userRepository.findByKeyWord(keyWord,pageable);
        List<UserResponse> userResponses = users.stream().map(UserMapper::mapToResponse).collect(Collectors.toList());
        return userResponses;
    }

    @Override
    public int getItemKeyWord(String keyWord) {
        List<User> users=userRepository.countByKeyWord(keyWord);
        return users.size();
    }

    @Override
    public int getTotalItem() {
        return (int) userRepository.count();
    }

    @Override
    public User findOneByPhone(String phone) {
        User user = userRepository.findOneByPhone_number(phone);
        return user;
    }

    @Override
    public UserResponse findById(long id) {
        User user = userRepository.findOne(id);
        UserResponse response = UserMapper.mapToResponse(user);
        if(user!=null){
//            response.setProvince_id(user.getProvince1().getId());
//            response.setWard_id(user.getWard().getId());
//            response.setDistrict_id(user.getDistrict1().getId());
        }
        return response;
    }

    @Override
    @Transactional
    public User saveUser(UserRequest userRequest, HttpServletResponse response, String path) throws IOException {
        User user=null;
        if(userRequest.getId()!=null){
            user=userRepository.findOne(userRequest.getId());
            try {
                ReflectionUtil.mapper(userRequest, user);
            } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
                e.printStackTrace();
            }
        }
        User user1=userRepository.save(user);
        if(user1!=null){
            if(path==MyConstants.thanh_toan){
                response.sendRedirect("/gio-hang/xac-nhan-thanh-toan/xac-nhan-don-hang");
            }
            else {
                response.sendRedirect("/nguoi-dung/thong-tin-chi-tiet?success");
            }
        }
        return user1;

    }

    @Override
    @Transactional
    public User updateEnable(String code) {
        User user=userRepository.findOneByVerification_Code(code);
        user.setEnable(1);
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public User setEnable(UserRequest userRequest) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        User user=userRepository.findOne(userRequest.getId());
        if (userRequest.getEnable()==1){
            userRequest.setEnable(0);
        }else {
            userRequest.setEnable(1);
        }
        ReflectionUtil.mapper(userRequest,user);
        return userRepository.save(user);
    }

    @Override
    public void deleteById(long[] ids) {

        for (int i = 0; i < ids.length; i++) {
            userRepository.delete(ids[i]);
        }

    }
}
