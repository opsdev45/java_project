import java.sql.SQLOutput;

public class IntListTwo {
    IntNodeTwo _head, _tail;

    public IntListTwo() {
        _head = null;
        _tail = null;
    }
    public void addToEnd (int num){
        IntNodeTwo new_tail = new IntNodeTwo(num);
        IntNodeTwo new_head = null;
        IntNodeTwo newTail1 = new_tail;
        if (_head == null && _tail == null) {
            new_head=new_tail;
            this._head = new_tail;

//            System.out.println(_head + " " + _head.getNum());
        }else{
            new_tail.setPrev(_tail);
            _tail.setNext(new_tail);
            System.out.print(_head.getNum() + "  ");
            System.out.println(_tail.getNum());

        }
        _tail = new_tail;
        _head = new_head;
    }
    public String toString(){

        if (_head==null)
            return null;
        String listStr = "{";
        while (_head != null){
            listStr += _head.getNum();
            if (_head.getNext() != null)
                listStr += ", ";
            _head = _head.getNext();
        }
        return listStr + "}";
    }
    public String toStringReverse(){

        return toStringReverse(_tail,"{");
    }
    private String toStringReverse(IntNodeTwo tail,String str){

        if (tail.getPrev()==null){
            str += tail.getNum() + "}";
            return str;
        }
        return toStringReverse(tail.getPrev(),str += tail.getNum() + ", ");
    }
    public boolean isWay(){
        if (_head == null) {
            System.out.println(_head);
//            return false;
        }
//        System.out.println(_head.getNum());
        return isWay(_head);
    }
    private boolean isWay(IntNodeTwo current){

        if (current == _tail)
            return true;
        if (current == null || current.getNum() < 0)
            return false;

        current.setNum(-1);

        return isWay(move(current,"right",current.getNum())) || isWay(move(current,"left",current.getNum()));

    }
    private IntNodeTwo move(IntNodeTwo current,String direction,int step){

        if (current==null)
            return null;
        if (step==0)
            return current;
        if (direction.equals("right") )
            return move(current.getNext(),"right",step -1);
        if (direction.equals("left"))
            return move(current.getPrev(),"left",step -1);
        return current;
    }
}
class TesterMmn14_GadiVilner {
    public static void main(String[] args) {
        IntListTwo list = new IntListTwo();
        list.addToEnd(5);
        list.addToEnd(4);
        list.addToEnd(6);
        list.addToEnd(2);
        list.addToEnd(9);
        list.addToEnd(7);
        list.addToEnd(8);
        System.out.print(list.toString() + " reversed = " + list.toStringReverse() + " - ");
        System.out.println(list.toStringReverse().equals("{8, 7, 9, 2, 6, 4, 5}"));
        IntListTwo list2 = new IntListTwo();
        list2.addToEnd(2);
        list2.addToEnd(4);
        list2.addToEnd(1);
        list2.addToEnd(6);
        list2.addToEnd(4);
        list2.addToEnd(2);
        list2.addToEnd(4);
        list2.addToEnd(3);
        list2.addToEnd(5);
        System.out.println(list2.toString());

        System.out.println(list2.isWay());
    }
    }




