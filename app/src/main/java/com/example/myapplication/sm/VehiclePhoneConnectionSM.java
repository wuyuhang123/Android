package com.example.myapplication.sm;

import android.os.Message;

/**
 * @Author wuyuhang
 * @Date 2023/7/5 10:33
 * @Describe 车和手机连接状态机
 */
public class VehiclePhoneConnectionSM extends StateMachine{

    public static final String TAG = "VehiclePhoneConnectionStateMachine";

    protected VehiclePhoneConnectionSM(String name) {
        super(name);

    }

    /**
     * 连接状态
     */
    class ConnectedState extends State {

        @Override
        public void enter() {
            super.enter();
        }

        @Override
        public void exit() {
            super.exit();
        }

        @Override
        public boolean processMessage(Message msg) {
            return super.processMessage(msg);
        }
    }

    /**
     * 断开状态
     */
    class DisconnectedState extends State {
        @Override
        public void enter() {
            super.enter();
        }

        @Override
        public void exit() {
            super.exit();
        }

        @Override
        public boolean processMessage(Message msg) {
            return super.processMessage(msg);
        }

    }

}
