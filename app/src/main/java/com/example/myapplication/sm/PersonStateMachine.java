package com.example.myapplication.sm;

/**
 * @Author wuyuhang
 * @Date 2023/7/5 10:27
 * @Describe
 */
public class PersonStateMachine extends StateMachine{

    public static final String TAG = "PersonStateMachine";

    protected PersonStateMachine(String name) {
        super(name);
    }

    public static PersonStateMachine makePersonStateMachine() {
        PersonStateMachine stateMachine = new PersonStateMachine(TAG);
        stateMachine.start();
        return stateMachine;
    }
}
