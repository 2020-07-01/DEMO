package dataStructure.skiplist;

import java.util.Random;

/**
 * @ClassName : SkipList
 * @Author : yq
 * @Date: 2021-04-06
 * @Description :
 */
public class SkipList {

    //头节点，入口
    SkipNode headNode;

    //层数
    int hightLevel;

    //随机
    Random random;


    SkipList() {
        random = new Random();
        headNode = new SkipNode(Integer.MIN_VALUE, null);
        hightLevel = 0;
    }

    public void add(SkipNode node) {
        int key = node.key;

        SkipNode findNode = search(key);
        //如果此节点存在，则更新值
        if (findNode != null) {
            findNode.value = node.value;
            return;
        }
        //不存在新增

    }

    /**
     * 查找节点
     *
     * @param key
     * @return
     */
    public SkipNode search(int key) {
        SkipNode team = headNode;
        while (team != null) {
            if (team.key == key) {
                return team;
            } else if (team.right == null) {
                //右侧无值，向下
                team = team.down;
            } else if (team.right.key > key) {
                //下降
                team = team.down;
            } else {
                //右侧的值比当前值小
                team = team.right;
            }
        }
        return null;
    }
}


/**
 * 节点
 *
 * @param <T>
 */
class SkipNode<T> {

    int key;
    T value;
    SkipNode right, down; //向右，向下

    public SkipNode(int ket, T value) {
        this.key = ket;
        this.value = value;
    }
}
