package com.bookshop.validate;//package com.bookshop.validate;
//
//import com.daoxuanson.shop.demo.exception.ValidateException;
//import com.daoxuanson.shop.demo.model.request.user.UserSaveRequest;
//import org.apache.commons.lang3.StringUtils;
//
//public class UserValidate {
//    public static UserSaveRequest validate(UserSaveRequest userSaveRequest) {
//        return Validator.of(userSaveRequest, () -> new ValidateException("Objet not null"))
//                .validate(UserSaveRequest::getUserName,UserValidate::validUserName ,() -> new ValidateException("userName isvalid"))
//                .validate(UserSaveRequest::getPassword,UserValidate::validPassword ,() -> new ValidateException("password isvalid"))
//                .validate(UserSaveRequest::getAddress,UserValidate::validAddress ,() -> new ValidateException("address isvalid"))
//                .get();
//    }
//
//    private static boolean validUserName(String userName) {
//        return StringUtils.isBlank(userName);
//    }
//
//    private static boolean validPassword(String password) {
//        return StringUtils.isBlank(password);
//    }
//
//    private static boolean validAddress(String address) {
//        return StringUtils.isBlank(address);
//    }
//}
