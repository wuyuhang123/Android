package com.example.myapplication.sm;

/**
 * @Author wuyuhang
 * @Date 2023/7/5 10:36
 * @Describe 寻车状态机
 */
public class SearchVehicleSM extends StateMachine{

    public static final String TAG = "SearchVehicleSM";

    protected SearchVehicleSM(String name) {
        super(name);
    }

    /**
     * 停车状态
     */
    class ParkingState extends State {

    }

    /**
     * 行驶状态
     */
    class DrivingState extends State {

    }

    /**
     * 寻车状态
     */
    class SearchingState extends State {

    }

    /**
     * 寻车成功状态
     */
    class SearchSuccessState extends State {

    }

}
