package com.cxy.favourite.domain;

import net.sf.oval.constraint.Email;
import net.sf.oval.constraint.MatchPattern;
import net.sf.oval.constraint.NotNull;

import javax.persistence.*;
import java.io.Serializable;

/**
 * 用户  jpa + oval
 */
@Entity
public class User extends Entitys implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Mysql为@GenerationType(strategy = GenerationType.IDENTITY)主键
    private Long id;
    @Column(nullable = false,unique = true)
    @NotNull(message = "昵称不能为空，请您填写昵称")
    private String userName;//=nickName
    @Column(nullable = false)
    private String salt;
    @Column(nullable = false)
    @NotNull(message = "密码不能为空，请您填写密码")
    @MatchPattern(pattern = "^[0-9_a-zA-Z]{6,20}$", message = "用户名或密码有误，请您重新填写")
    private String password;
    @Column(nullable = false,unique = true)
    @Email(message = "邮箱格式错误，请您填写正确的邮箱")
    private String email;
    @Column(nullable = true)
    private String myPicture;//头像
    @Column(length = 65535,nullable = true,columnDefinition = "Text")
    private String introduction;
    @Column(nullable = false)//TODO  之后改成false
    private Long createdTime;
    @Column(nullable = false)//TODO  之后改成false
    private Long lastModifyTime;//最后修改时间
    @Column(nullable = true)
    private String outDate;//作废时间
    @Column(nullable = true)
    private String validataCode;//验证码
    @Column(nullable = true)
    private String backgroundPicture;
    /**性别：1男2女3未知**/
    @Column(nullable = true)
    private Integer sex;
    /**年龄**/
    @Column(nullable = true)
    private Integer age;

    public User() {
        super();
    }

    public User(String userName,String password, String salt, String email) {
        super();
        this.userName = userName;
        this.password = password;
        this.salt = salt;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMyPicture() {
        return myPicture;
    }

    public void setMyPicture(String myPicture) {
        this.myPicture = myPicture;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public Long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Long createdTime) {
        this.createdTime = createdTime;
    }

    public Long getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Long lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getOutDate() {
        return outDate;
    }

    public void setOutDate(String outDate) {
        this.outDate = outDate;
    }

    public String getValidataCode() {
        return validataCode;
    }

    public void setValidataCode(String validataCode) {
        this.validataCode = validataCode;
    }

    public String getBackgroundPicture() {
        return backgroundPicture;
    }

    public void setBackgroundPicture(String backgroundPicture) {
        this.backgroundPicture = backgroundPicture;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }
}
