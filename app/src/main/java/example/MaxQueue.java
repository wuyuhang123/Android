package example;

import java.util.LinkedList;

class MaxQueue {

    public LinkedList<Integer> list = new LinkedList<>();
    public LinkedList<Integer> res = new LinkedList<>();
    public MaxQueue() {

    }

    public int max_value() {
        if (list.size() != 0){
            return list.getFirst() ;
        }else {
            return -1;
        }
    }

    public void push_back(int value) {
        res.add(value);
        while (!list.isEmpty() && value > list.getLast()){
            list.removeLast();
        }
        list.add(value);
    }

    public int pop_front() {
        if (res.size() == 0){
            return -1;
        }else {
            int s = res.removeFirst();
            if (s == list.getFirst()){
                list.removeFirst();
            }
            return s;
        }
    }
}
