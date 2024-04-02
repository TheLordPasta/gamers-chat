package com.example.gamers_chat.models;

public class User {
//     <EditText
//             android:id="@+id/registerInputUsername"
//             android:layout_width="match_parent"
//             android:layout_height="wrap_content"
//             android:ems="10"
//             android:hint="Username"
//             android:inputType="textEmailAddress" />
//     
//         <EditText
//             android:id="@+id/registerInputEmail"
//             android:layout_width="match_parent"
//             android:layout_height="wrap_content"
//             android:ems="10"
//             android:hint="E-mail"
//             android:inputType="textEmailAddress"
//             android:textSize="16sp" />
//     
//         <EditText
//             android:id="@+id/registerInputPassword"
//             android:layout_width="match_parent"
//             android:layout_height="wrap_content"
//             android:ems="10"
//             android:hint="Password"
//             android:inputType="textPassword" />
//     
//         <EditText
//             android:id="@+id/registerInputNickname"
//             android:layout_width="match_parent"
//             android:layout_height="wrap_content"
//             android:ems="10"
//             android:hint="Nickname"
//             android:inputType="textEmailAddress" />
            
    String email;
    String password;
    String nickName;
    String username;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickName() {
        return nickName;
    }
    
    public void setNickName(String value) {
            nickName = value;
    }

    public String getName() {
        return username;
    }



    public Integer finalAmount=0;

    public User(String email, String password, String nickName, String username) {
        this.email = email;
        this.password = password;
        this.nickName = nickName;
        this.username = username;

    }


   public User(){
      // this.email = "bob@gmail.com";
      // this.password = "1234";


      // this.name = "bob";
   }
}