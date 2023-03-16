package PrivateAccountSystem;


import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class AccountOperation {
    public static void main(String[] args) {
        ArrayList<User> list = new ArrayList<>();
        loop:while (true){
            System.out.println("----------------Welcome to account menu----------------");
            System.out.println("|                      1:Log in                       |");
            System.out.println("|                      2:Register                     |");
            System.out.println("|                      3:Forget password              |");
            System.out.println("|                      4:Exit                         |");
            System.out.println("|     Please type in the number of your operation.    |");
            System.out.println("-------------------------------------------------------");
            Scanner sc = new Scanner(System.in);
            String choose = sc.next();
            switch (choose){
                case "1"-> login(list);
                case "2"-> register(list);
                case "3"-> modify(list);
                case "4"-> {
                    System.out.println("Thanks for using!");
                    break loop;
                }
                default -> System.out.println("Error!");
            }
        }
    }

    public static void login(ArrayList<User> list) {
        System.out.println("Please type your username.");
        Scanner sc =new Scanner(System.in);
        String userName = sc.next();
        int index = loginUseNameGetIndex(list,userName);
        if (index>=0) {
            String strGenerator = verificationCodeRandom();
            System.out.println();
            System.out.println("Please type verification code.");
            String strTyper = sc.next();
            if (strTyper.equals(strGenerator)) {
                for (int i = 0; i < 3; i++) {
                    System.out.println("Please type your password.");
                    String loginPwd = sc.next();
                    if (userNamePwdSame(list,userName,loginPwd)) {
                        System.out.println("Log in successfully.");
                        break;
                    }
                    else {
                        System.out.println("Error!Wrong username or password. "+(2-i)+" chances left.");
                    }
                }
            }else {
                System.out.println("Error!Wrong verification code.");
            }
        }else {
            System.out.println("Error!No such user registered.");
        }
    }

    public static int loginUseNameGetIndex(ArrayList<User> list,String userName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getUserName().equals(userName)) {
                return i;
            }
        }
        return -1;
    }

    public static boolean loginPwdSame(ArrayList<User> list, String loginPwd,int index) {
        if (loginPwd == list.get(index).getPwd() ) {
            return true;
        }else {
            return false;
        }
    }

    public static void register(ArrayList<User> list) {
        System.out.println("Please type the username that you want to register.");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();
        if(userNameCheck(list,userName)){
            System.out.println("Please type the password that you want to register.");
            String pwd1 = sc.next();
            System.out.println("Please type the password that you want to register again.");
            String pwd2 = sc.next();
            if (pwdCheck(pwd1,pwd2)){
                System.out.println("Please type your ID.");
                String id = sc.next();
                if (idCheck(id)){
                    System.out.println("Please type your phone number.");
                    String phone = sc.next();
                    if (phoneCheck(phone)) {
                        User user = new User();
                        user.setUserName(userName);
                        user.setPwd(pwd1);
                        user.setId(id);
                        user.setPhoneNum(phone);
                        list.add(user);
                    }
                }
            }
        }
    }

    public static boolean userNamePwdSame(ArrayList<User> list, String userName, String loginPwd) {
        if ((userName.equals(list.get(loginUseNameGetIndex(list,userName)).getUserName()))&&loginPwd.equals(list.get(loginUseNameGetIndex(list,userName)).getPwd())) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean userNameCheck(ArrayList<User> list,String userName) {
        if (userNameLength(userName)){
            if (userNameSingle(list, userName)){
                if (userNameLetter(userName)>0) {
                    return true;
                }else {
                    System.out.println("Error!Must contain letters.");
                    return false;
                }
            }else {
                System.out.println("Error!Username already existed.");
                return false;
            }
        }else {
            System.out.println("Error!Username's length is valid.");
            return false;
        }
    }

    public static boolean userNameLength(String userName) {
        int len = userName.length();
        if (len>=3&&len<=15){
            return true;
        }else {
            return false;
        }
    }

    public static boolean userNameSingle(ArrayList<User> list, String userName) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(userName)){
                return false;
            }
        }
        return true;
    }

    public static int userNameLetter(String userName) {
        int countLetter = 0;
        int countNum = 0;
        for (int i = 0; i < userName.length(); i++) {
            if (userName.charAt(i)>='a'&&userName.charAt(i)<='z'){
                countLetter++;
            }else if (userName.charAt(i)>='A'&&userName.charAt(i)<='Z'){
                countLetter++;
            }else if (userName.charAt(i)>='0'&&userName.charAt(i)<='9'){
                countNum++;
            }else {
                return 0;
            }
        }
        return countLetter;
    }

    public static boolean pwdCheck(String pwd1,String pwd2){
        if (pwd2.equals(pwd1)) {
            return true;
        }else {
            System.out.println("Error!Different from the previous one.");
            return false;
        }
    }

    public static boolean idCheck(String id) {
        if (idLength(id)) {
            if (idNo0(id)) {
                if(idNum(id)){
                    if (idLast(id)) {
                        return true;
                    }else {
                        System.out.println("Error!The last 1 of ID only could be letter'x' or letter'X' or numbers.");
                        return false;
                    }
                }else {
                    System.out.println("Error!The first 17 of ID must be numbers.");
                    return false;
                }
            }else {
                System.out.println("Error!The first of ID could not be number 0.");
                return false;
            }
        }else {
            System.out.println("Error!Wrong ID length.");
            return false;
        }
    }

    public static boolean idLength(String id) {
        if (id.length() == 18) {
            return true;
        }
        else {
            return false;
        }
    }

    public static boolean idNo0(String id) {
        if (id.charAt(0) != 0) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean idNum(String id) {
        for (int i = 0; i < id.length()-1; i++) {
            if(id.charAt(i)<'0'||id.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    public static boolean idLast(String id) {
        if (id.charAt(id.length()-1)>='0'||id.charAt(id.length()-1)<='9'||id.charAt(id.length()-1)=='X'||id.charAt(id.length()-1)=='x') {
            return true;
        }else {
            return false;
        }
    }

    public static boolean phoneCheck(String phone) {
        if (phoneLength(phone)) {
            if (phoneNo0(phone)) {
                if (phoneNum(phone)){
                    return true;
                }else {
                    System.out.println("Error!The phone could only contains numbers.");
                    return false;
                }
            }else {
                System.out.println("Error!The first number of phone could not be number 0.");
                return false;
            }
        }else {
            System.out.println("Error!Phone must contain 11 numbers.");
            return false;
        }
    }

    public static boolean phoneLength(String phone) {
        if (phone.length() == 11) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean phoneNo0(String phone ) {
        if (phone.charAt(0) != 0) {
            return true;
        }else {
            return false;
        }
    }

    public static boolean phoneNum(String phone) {
        for (int i = 0; i < phone.length(); i++) {
            if (phone.charAt(i)<'0'||phone.charAt(i)>'9'){
                return false;
            }
        }
        return true;
    }

    public static String verificationCodeRandom() {
        char[] code = new char[5];
        Random r = new Random();
        for (int i = 0; i < code.length-1; i++) {
            double upperOrLower = r.nextDouble(1);
            if (upperOrLower < 0.5) {
                char upperLetter = (char) (r.nextInt(26)+'A');
                code[i] = upperLetter;
            }else {
                char lowerLetter = (char) (r.nextInt(26)+'a');
                code[i] = lowerLetter;
            }
        }
        code[code.length-1] = (char) (r.nextInt(10)+'0');
        int index = r.nextInt(4);
        char temp = code[index];
        code[index] = code[code.length-1];
        code[code.length-1] = temp;
        for (int i = 0; i < code.length; i++) {
            System.out.print(code[i]);
        }
        String str = new String(code);
        return str;
    }

    public static void modify(ArrayList<User> list) {
        System.out.println("Please type in your username.");
        Scanner sc = new Scanner(System.in);
        String userName = sc.next();
        int index = loginUseNameGetIndex(list,userName);
        if (loginUseNameGetIndex(list,userName)>=0) {
            System.out.println("Please type in your ID.");
            String id = sc.next();
            System.out.println("Please type in your phone.");
            String phone = sc.next();
            if ((phone.equals(list.get(index).getPhoneNum()))&&(id.equals(list.get(index).getId()))) {
                System.out.println("Please type the new password.");
                String pwd = sc.next();
                list.get(index).setPwd(pwd);
                System.out.println("Modify successfully.");
            }else {
                System.out.println("Error!Information doesn't match.");
            }
        }else {
            System.out.println("No such user registered.");
        }
    }
}
