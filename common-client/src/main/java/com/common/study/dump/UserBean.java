package com.common.study.dump;

public class UserBean {


    private byte[] memory = new byte[1024*1000];
    private int id;

    private int age;

    private String name;

    private boolean adult;

    private Boolean married;

    public byte[] getMemory() {
        return memory;
    }

    public void setMemory(byte[] memory) {
        this.memory = memory;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAdult() {
        return adult;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public Boolean isMarried() {
        return married;
    }

    public void setMarried(Boolean married) {
        this.married = married;
    }

}
